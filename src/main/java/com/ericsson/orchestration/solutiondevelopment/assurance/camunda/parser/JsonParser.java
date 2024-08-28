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
package com.ericsson.orchestration.solutiondevelopment.assurance.camunda.parser;

import java.util.logging.Logger;

import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.ActionMessage;
import com.google.gson.Gson;

public class JsonParser {

    public static final Logger logger = Logger.getLogger(JsonParser.class.getName());

    public static ActionMessage parseActionMessage(final String json) {

        final Gson gson = new Gson();

        try {
            final ActionMessage actionMessage = gson.fromJson(json, ActionMessage.class);
            return actionMessage;
        } catch (final Exception ex) {

            logger.warning("Error is::" + ex.toString());
            return null;
        }

    }
}
