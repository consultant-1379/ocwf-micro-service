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
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.CheckOrderStatusResponseEntity;
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.WebApiVmResponse;
import com.google.gson.Gson;

public class CheckOrderStatusTask implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(CheckOrderStatusTask.class.getName());

    @Override
    public void execute(final DelegateExecution execution) {

        LOGGER.info("*********** executing check order status task");

        final String vmId = (String) execution.getVariable("vm_id");

        final String command = (String) execution.getVariable("command");

        final String baseUrl = (String) execution.getVariable("base_url");

        final String service = (String) execution.getVariable("service");

        final String tenantId = (String) execution.getVariable("tenant_id");

        final String authHeader = (String) execution.getVariable("auth_header");

        final String orderId = (String) execution.getVariable("orderId");

        LOGGER.info("****** order id is::" + orderId);

        LOGGER.info("****** sleep interval check order status task::" + execution.getVariable("sleepInterval"));

        final WebApiVmResponse webApiVmResponse = Utils.sendGet("https://" + baseUrl + "/" + service + "/" + orderId, tenantId, authHeader);

        if (webApiVmResponse.isError()) {
            execution.setVariable("finished", true);
            execution.setVariable("error", true);
        } else {

            final Gson gson = new Gson();

            try {
                final CheckOrderStatusResponseEntity checkOrderStatusResponseEntity = gson.fromJson(webApiVmResponse.getResponseBody(),
                        CheckOrderStatusResponseEntity.class);

                if (checkOrderStatusResponseEntity.getStatus().getReqStatus() != null
                        && checkOrderStatusResponseEntity.getStatus().getReqStatus().equalsIgnoreCase("success")) {

                    if (checkOrderStatusResponseEntity.getData().getOrder().getOrderReqStatus() != null
                            && checkOrderStatusResponseEntity.getData().getOrder().getOrderReqStatus().equalsIgnoreCase("err")) {

                        LOGGER.info("Order completed with error");

                        execution.setVariable("finished", true);
                        execution.setVariable("error", true);

                    } else if (checkOrderStatusResponseEntity.getData().getOrder().getOrderReqStatus() != null
                            && checkOrderStatusResponseEntity.getData().getOrder().getOrderReqStatus().equalsIgnoreCase("com")) {

                        LOGGER.info("Order completed successfully");

                        execution.setVariable("finished", true);
                        execution.setVariable("error", false);
                    } else {

                        LOGGER.info("Order in progress");

                        execution.setVariable("finished", false);
                    }

                } else {
                    LOGGER.info("Error is:: order inquery is not successful");

                    execution.setVariable("finished", true);
                    execution.setVariable("error", true);
                }

            } catch (final Exception ex) {

                LOGGER.info("Error is::" + ex.toString());

                execution.setVariable("finished", true);
                execution.setVariable("error", true);
            }

        }

        LOGGER.info("********task end");
    }
}
