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
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.WebApiEnmLoginResponse;

public class HealOperationTask implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(ScaleOperationTask.class.getName());

    @Override
    public void execute(final DelegateExecution execution) throws Exception {
        // TODO Auto-generated method stub

        LOGGER.info("***********task executing");

        final String vnfExtSystemId = (String) execution.getVariable("extSystemId");

        final String apiToken = (String) execution.getVariable("apiToken");

        final String enmUrl = (String) execution.getVariable("enm_url");

        final String command = (String) execution.getVariable("command");

        final String healCause = (String) execution.getVariable("heal_cause");

        final String jsonBody = "{\"cause\":\"" + healCause + "\"" + "}";

        LOGGER.info("Calling vanf lcm heal api.......");
        LOGGER.info("***********http request body:: " + jsonBody);

        final WebApiEnmLoginResponse webApiEnmLoginResponse = Utils
                .sendScaleVnfPost("https://" + enmUrl + "/vnflcm/v1/vnf_instances/" + vnfExtSystemId + "/" + command, apiToken, jsonBody, enmUrl);

        if (webApiEnmLoginResponse.isError()) {

            LOGGER.info("Error occured during calling heal api");
        } else {

            LOGGER.info(command + " request send to vnf lcm, please check order...");
        }
    }

}
