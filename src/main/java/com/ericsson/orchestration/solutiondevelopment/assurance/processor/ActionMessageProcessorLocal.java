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

import org.camunda.bpm.engine.RuntimeService;

import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.config.ReadYmlProperties;
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.ActionMessage;

public interface ActionMessageProcessorLocal {

    public void processAction(final RuntimeService runtimeService, final ActionMessage actionMessage, final ReadYmlProperties readYmlProperties);
}
