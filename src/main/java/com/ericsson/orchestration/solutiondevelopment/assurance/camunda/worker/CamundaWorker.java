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
package com.ericsson.orchestration.solutiondevelopment.assurance.camunda.worker;

import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamundaWorker extends Thread {

    private final Logger LOGGER = LoggerFactory.getLogger(CamundaWorker.class.getName());

    private RuntimeService runtimeService;

    private Map<String, Object> vars;

    private String processId;

    public CamundaWorker(final RuntimeService runtimeService, final Map<String, Object> vars, final String processId) {

        this.runtimeService = runtimeService;
        this.vars = vars;
        this.processId = processId;
    }

    @Override
    public void run() {

        LOGGER.info("###########running process with id::" + processId);

        runtimeService.startProcessInstanceByKey(processId, vars);

        LOGGER.info("###########process end");
    }
}