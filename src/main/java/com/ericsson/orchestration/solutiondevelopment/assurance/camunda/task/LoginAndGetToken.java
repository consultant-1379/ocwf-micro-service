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

public class LoginAndGetToken implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(LoginAndGetToken.class.getName());

    @Override
    public void execute(final DelegateExecution execution) throws Exception {
        // TODO Auto-generated method stub

        LOGGER.info("***********task executing");

        final String enmUrl = (String) execution.getVariable("enm_url");

        final String enmUsername = (String) execution.getVariable("enm_username");

        final String enmPassword = (String) execution.getVariable("enm_password");

        LOGGER.info("****** enm url is::" + enmUrl);

        //LOGGER.info("****** enm username is::" + enmUsername);

        //LOGGER.info("****** enm password is::" + enmPassword);

        final WebApiEnmLoginResponse webApiEnmLoginResponse = Utils.sendLoginEnmPost("https://" + enmUrl + "/login", enmUsername, enmPassword);

        if (webApiEnmLoginResponse.isError()) {

            execution.setVariable("loginEnmError", true);
        } else {

            if (webApiEnmLoginResponse.getToken() != null && webApiEnmLoginResponse.getToken().length() > 0) {

                execution.setVariable("loginEnmError", false);
                execution.setVariable("apiToken", webApiEnmLoginResponse.getToken());

                LOGGER.info("api token is::" + webApiEnmLoginResponse.getToken());

            } else {

                execution.setVariable("loginEnmError", true);
            }

        }

        LOGGER.info("********task end");
    }

}
