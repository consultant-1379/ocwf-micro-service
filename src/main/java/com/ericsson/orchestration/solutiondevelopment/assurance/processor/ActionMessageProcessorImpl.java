/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2015
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.orchestration.solutiondevelopment.assurance.processor;

import java.util.*;
import java.util.logging.Logger;

import org.camunda.bpm.engine.RuntimeService;

import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.config.ReadYmlProperties;
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.ActionMessage;
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.AffectedObjects;
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.worker.CamundaWorker;

public class ActionMessageProcessorImpl implements ActionMessageProcessorLocal {

    public static final Logger logger = Logger.getLogger(ActionMessageProcessorImpl.class.getName());

    @Override
    public void processAction(final RuntimeService runtimeService, final ActionMessage actionMessage, final ReadYmlProperties readYmlProperties) {

        if (actionMessage != null && actionMessage.getFaultAssetType().equalsIgnoreCase("vm")) {

            if (actionMessage.getAction().equalsIgnoreCase("start") || actionMessage.getAction().equalsIgnoreCase("stop")
                    || actionMessage.getAction().equalsIgnoreCase("recreate")) {
                logger.info("###########start process with id::" + readYmlProperties.getBaseBpmnFileName());

                final Map<String, Object> vars = new HashMap<String, Object>();
                vars.put("vm_id", actionMessage.getFaultAssetId());
                vars.put("command", actionMessage.getAction().toLowerCase());
                vars.put("base_url", readYmlProperties.getEcmUrl() + "/ecm_service");
                vars.put("service", "vms");
                vars.put("tenant_id", readYmlProperties.getTenantId());
                vars.put("auth_header", readYmlProperties.getAuthHeader());

                //logger.info("### sleep interval::" + readYmlProperties.getSleepInterval());

                vars.put("sleepInterval", readYmlProperties.getSleepInterval());

                try {

                    /*
                     * if (processEngine != null) {
                     *
                     * logger.info("process engine is not null"); final WorkerThread workerThread = new WorkerThread(processEngine, vars,
                     * Utils.starStoptVmProcessId);
                     *
                     * final Thread thread = threadFactory.newThread(workerThread); thread.start(); } else { logger.info("process engine is null"); }
                     */

                    //workerProcessorLocal.runWorkFlow(vars, Utils.starStoptVmProcessId);

                    final Thread workerThread = new CamundaWorker(runtimeService, vars, readYmlProperties.getBaseBpmnFileName());
                    workerThread.start();
                    logger.info("running workflow...");

                } catch (final Exception ex) {
                	logger.info(ex.toString());
                    logger.info(ex.getMessage());
                }

                logger.info("process end action message");

            } else if (actionMessage.getAction().equalsIgnoreCase("restart")) {
                logger.info("########### restart process with id::" + readYmlProperties.getRestartBpmnFileName());

                final Map<String, Object> vars = new HashMap<String, Object>();
                vars.put("vm_id", actionMessage.getFaultAssetId());
                vars.put("firstCommand", "stop");
                vars.put("secondCommand", "start");
                vars.put("base_url", readYmlProperties.getEcmUrl() + "/ecm_service");
                vars.put("service", "vms");
                vars.put("tenant_id", readYmlProperties.getTenantId());
                vars.put("auth_header", readYmlProperties.getAuthHeader());

                logger.info("### sleep interval action message::" + readYmlProperties.getSleepInterval());

                vars.put("sleepInterval", readYmlProperties.getSleepInterval());

                try {

                    /*
                     * if (processEngine != null) {
                     *
                     * logger.info("process engine is not null"); final WorkerThread workerThread = new WorkerThread(processEngine, vars,
                     * Utils.restartVmProcessId);
                     *
                     * final Thread thread = threadFactory.newThread(workerThread); thread.start(); } else { logger.info("process engine is null"); }
                     */
                    // workerProcessorLocal.runWorkFlow(vars, Utils.restartVmProcessId);

                    final Thread workerThread = new CamundaWorker(runtimeService, vars, readYmlProperties.getRestartBpmnFileName());
                    workerThread.start();
                    logger.info("running workflow...");

                } catch (final Exception ex) {
                	logger.info(ex.toString());
                    logger.info(ex.getMessage());
                }

                logger.info("process end action message");

            }

        } else if (actionMessage != null && actionMessage.getFaultAssetType().equalsIgnoreCase("host")) { //host UC

            if (actionMessage.getAction().equalsIgnoreCase("recreatevms")) {

                final List<AffectedObjects> affectedObjects = actionMessage.getAffectedObjects();

                if (affectedObjects != null && affectedObjects.size() > 0) {

                    for (int i = 0; i < affectedObjects.size(); i++) {

                        logger.info("########### recreate VMs process with id::" + readYmlProperties.getBaseBpmnFileName());

                        final Map<String, Object> vars = new HashMap<String, Object>();
                        vars.put("vm_id", actionMessage.getAffectedObjects().get(i).getAssetId());
                        vars.put("command", "recreate");
                        vars.put("base_url", readYmlProperties.getEcmUrl() + "/ecm_service");
                        vars.put("service", "vms");
                        vars.put("tenant_id", readYmlProperties.getTenantId());
                        vars.put("auth_header", readYmlProperties.getAuthHeader());

                        //logger.info("### sleep interval::" + readYmlProperties.getSleepInterval());

                        vars.put("sleepInterval", readYmlProperties.getSleepInterval());

                        try {

                            /*
                             * if (processEngine != null) {
                             *
                             * logger.info("process engine is not null"); final WorkerThread workerThread = new WorkerThread(processEngine, vars,
                             * Utils.starStoptVmProcessId);
                             *
                             * if (threadFactory != null) { final Thread thread = threadFactory.newThread(workerThread); thread.start(); } else {
                             * logger.info("threadfactory is null"); } } else { logger.info("process engine is null"); }
                             */

                            //workerProcessorLocal.runWorkFlow(vars, Utils.starStoptVmProcessId);

                            final Thread workerThread = new CamundaWorker(runtimeService, vars, readYmlProperties.getBaseBpmnFileName());
                            workerThread.start();
                            logger.info("workflow started...");

                        } catch (final Exception ex) {
                        	logger.info(ex.toString());
                            logger.info(ex.getMessage());
                        }

                        logger.info("process end action message");
                    }
                }
            }

        } else if (actionMessage != null && actionMessage.getFaultAssetType().equalsIgnoreCase("vnf")) {

            if (actionMessage.getAction().equalsIgnoreCase("scaleout")) {

                logger.info("Starting scale out.....");

                logger.info("###########start process with id::" + readYmlProperties.getScaleBpmnFileName());

                final Map<String, Object> vars = new HashMap<String, Object>();
                vars.put("vapp_id", actionMessage.getFaultAssetId());
                vars.put("command", "SCALE_OUT");
                vars.put("base_url", readYmlProperties.getEcmUrl() + "/ecm_service");
                vars.put("service", "vapps");
                vars.put("tenant_id", readYmlProperties.getTenantId());
                vars.put("auth_header", readYmlProperties.getAuthHeader());
                vars.put("enm_url", readYmlProperties.getEnmURl());
                vars.put("enm_username", readYmlProperties.getEnmUsername());
                vars.put("enm_password", readYmlProperties.getEnmPassword());
                //vars.put("aspect_id", actionMessage.getAspectId());
                //vars.put("no_of_steps", actionMessage.getNoOfSteps());

                vars.put("aspect_id", "GPB");
                vars.put("no_of_steps", "1");
                //logger.info("### sleep interval::" + readYmlProperties.getSleepInterval());

                vars.put("sleepInterval", readYmlProperties.getSleepInterval());

                try {

                    final Thread workerThread = new CamundaWorker(runtimeService, vars, readYmlProperties.getScaleBpmnFileName());
                    workerThread.start();
                    logger.info("running workflow...");

                } catch (final Exception ex) {
                	logger.info(ex.toString());
                    logger.info(ex.getMessage());
                }

                logger.info("process end action message");
            }

            if (actionMessage.getAction().equalsIgnoreCase("scalein")) {

                logger.info("###########start process with id::" + readYmlProperties.getScaleBpmnFileName());

                final Map<String, Object> vars = new HashMap<String, Object>();
                vars.put("vapp_id", actionMessage.getFaultAssetId());
                vars.put("command", "SCALE_IN");
                vars.put("base_url", readYmlProperties.getEcmUrl() + "/ecm_service");
                vars.put("service", "vapps");
                vars.put("tenant_id", readYmlProperties.getTenantId());
                vars.put("auth_header", readYmlProperties.getAuthHeader());
                vars.put("enm_url", readYmlProperties.getEnmURl());
                vars.put("enm_username", readYmlProperties.getEnmUsername());
                vars.put("enm_password", readYmlProperties.getEnmPassword());
                //vars.put("aspect_id", actionMessage.getAspectId());
                //vars.put("no_of_steps", actionMessage.getNoOfSteps());

                vars.put("aspect_id", "GPB");
                vars.put("no_of_steps", "1");

                //logger.info("### sleep interval::" + readYmlProperties.getSleepInterval());

                vars.put("sleepInterval", readYmlProperties.getSleepInterval());

                try {

                    final Thread workerThread = new CamundaWorker(runtimeService, vars, readYmlProperties.getScaleBpmnFileName());
                    workerThread.start();
                    logger.info("running workflow...");

                } catch (final Exception ex) {
                	logger.info(ex.toString());
                    logger.info(ex.getMessage());
                }

                logger.info("process end action message");
            }

            if (actionMessage.getAction().equalsIgnoreCase("heal")) {

                logger.info("###########start process with id::" + readYmlProperties.getHealBpmnFileName());

                final Map<String, Object> vars = new HashMap<String, Object>();
                vars.put("vapp_id", actionMessage.getFaultAssetId());
                vars.put("command", "heal");
                vars.put("base_url", readYmlProperties.getEcmUrl() + "/ecm_service");
                vars.put("service", "vapps");
                vars.put("tenant_id", readYmlProperties.getTenantId());
                vars.put("auth_header", readYmlProperties.getAuthHeader());
                vars.put("enm_url", readYmlProperties.getEnmURl());
                vars.put("enm_username", readYmlProperties.getEnmUsername());
                vars.put("enm_password", readYmlProperties.getEnmPassword());

                vars.put("heal_cause", actionMessage.getAdditionalText());
                //logger.info("### sleep interval::" + readYmlProperties.getSleepInterval());

                vars.put("sleepInterval", readYmlProperties.getSleepInterval());

                try {

                    final Thread workerThread = new CamundaWorker(runtimeService, vars, readYmlProperties.getHealBpmnFileName());
                    workerThread.start();
                    logger.info("running workflow...");

                } catch (final Exception ex) {
                	logger.info(ex.toString());
                    logger.info(ex.getMessage());
                }

                logger.info("process end action message");
            }
        }

    }
}
