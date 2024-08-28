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
package com.ericsson.orchestration.solutiondevelopment.assurance.camunda.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.config.Utils;
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.VmOperationResponseEntity;
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.WebApiVmResponse;
import com.google.gson.Gson;

public class VmOperationTask implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(VmOperationTask.class.getName());

    @Override
    public void execute(final DelegateExecution execution) throws Exception {

        LOGGER.info("***********task executing");

        final String vmId = (String) execution.getVariable("vm_id");

        final String command = (String) execution.getVariable("command");

        final String baseUrl = (String) execution.getVariable("base_url");

        final String service = (String) execution.getVariable("service");

        final String tenantId = (String) execution.getVariable("tenant_id");

        final String authHeader = (String) execution.getVariable("auth_header");

        LOGGER.info("****** param is::" + vmId);

        LOGGER.info("****** sleep interval vm operation task::" + execution.getVariable("sleepInterval"));

        String jsonBody = null;

        if (command != null && command.equalsIgnoreCase("recreate")) {
            /*
             * final BootSource bootSource = new BootSource(); bootSource.setImageName("");
             *
             * final RecreateVmBody recreateVmBody = new RecreateVmBody(); recreateVmBody.setBootSource(bootSource);
             *
             * final Gson gson = new Gson(); final String jsonBody = gson.toJson(recreateVmBody);
             */

            jsonBody = " \"bootSource\": {" + "\"imageName\":\" \"}";

            LOGGER.info("***********http request body:: " + jsonBody);

        }

        final WebApiVmResponse webApiVmResponse = Utils.sendPost("https://" + baseUrl + "/" + service + "/" + vmId + "/" + command, tenantId,
                authHeader, jsonBody);

        if (webApiVmResponse.isError()) {
            execution.setVariable("error", true);
        } else {

            final Gson gson = new Gson();

            try {
                final VmOperationResponseEntity vmOperationResponseEntity = gson.fromJson(webApiVmResponse.getResponseBody(),
                        VmOperationResponseEntity.class);

                if (vmOperationResponseEntity.getStatus().getReqStatus() != null
                        && vmOperationResponseEntity.getStatus().getReqStatus().equalsIgnoreCase("success")) {

                    execution.setVariable("error", false);
                    execution.setVariable("orderId", vmOperationResponseEntity.getData().getOrder().getId());
                    execution.setVariable("service", "orders");

                } else {
                    LOGGER.info("Error is:: request is not successful");

                    execution.setVariable("error", true);
                }

            } catch (final Exception ex) {

                LOGGER.info("Error is::" + ex.toString());

                execution.setVariable("error", true);
            }

        }

        LOGGER.info("********task end");

    }
}
