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

public class SleepTask implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(SleepTask.class.getName());

    @Override
    public void execute(final DelegateExecution execution) {

        LOGGER.info("****** sleep interval sleep task::" + execution.getVariable("sleepInterval"));

        final String sleepInterval = (String) execution.getVariable("sleepInterval");

        try {
            Thread.sleep(Utils.getLongFromString(sleepInterval));
        } catch (final InterruptedException e) {
            LOGGER.info("####sleep failed::" + e.getMessage());
            Thread.currentThread().interrupt();
        }

    }

}
