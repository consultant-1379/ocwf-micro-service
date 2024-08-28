/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2018
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
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.GetVappOperationResponse;
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.WebApiVmResponse;
import com.google.gson.Gson;

public class CheckVnfStatus implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(CheckVnfStatus.class.getName());

    @Override
    public void execute(final DelegateExecution execution) throws Exception {
        // TODO Auto-generated method stub

        LOGGER.info("***********task executing");

        final String vappId = (String) execution.getVariable("vapp_id");

        final String command = (String) execution.getVariable("command");

        final String baseUrl = (String) execution.getVariable("base_url");

        final String service = (String) execution.getVariable("service");

        final String tenantId = (String) execution.getVariable("tenant_id");

        final String authHeader = (String) execution.getVariable("auth_header");

        LOGGER.info("****** vapp system is::" + vappId);

        LOGGER.info("****** auth header::" + authHeader);

        LOGGER.info("****** tenant id::" + tenantId);

        LOGGER.info("****** sleep interval vm operation task::" + execution.getVariable("sleepInterval"));

        final WebApiVmResponse webApiVmResponse = Utils.sendGet("https://" + baseUrl + "/" + service + "/" + vappId, tenantId, authHeader);

        if (webApiVmResponse.isError()) {

            execution.setVariable("getVappError", true);
        } else {

            final Gson gson = new Gson();

            try {
                final GetVappOperationResponse getVappOperationResponse = gson.fromJson(webApiVmResponse.getResponseBody(),
                        GetVappOperationResponse.class);

                if (getVappOperationResponse.getStatus().getReqStatus() != null
                        && getVappOperationResponse.getStatus().getReqStatus().equalsIgnoreCase("success")) {

                    //execution.setVariable("error", false);
                    //execution.setVariable("orderId", vmOperationResponseEntity.getData().getOrder().getId());
                    //execution.setVariable("service", "orders");
                    if (getVappOperationResponse.getData().getVapp().getVnfm() != null) {
                        LOGGER.info("This vnf is managed by vnf lcm");

                        LOGGER.info("external system id is::" + getVappOperationResponse.getData().getVapp().getManagementSystemId());
                        LOGGER.info("source id::" + getVappOperationResponse.getData().getVapp().getSourceId());

                        execution.setVariable("extSystemId", getVappOperationResponse.getData().getVapp().getManagementSystemId());

                        execution.setVariable("getVappError", false);

                    } else {
                        LOGGER.info("This vnf is not managed by vnf lcm");
                    }
                } else {
                    LOGGER.info("Error is:: request is not successful");

                    execution.setVariable("getVappError", true);
                }

            } catch (final Exception ex) {

                LOGGER.info("Error is::" + ex.toString());

                execution.setVariable("getVappError", true);
            }

        }

        LOGGER.info("********task end");
    }

}
