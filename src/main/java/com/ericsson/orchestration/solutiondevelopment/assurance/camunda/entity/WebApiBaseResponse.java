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
package com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity;

public class WebApiBaseResponse {

    private String statusCode;
    private String message;
    private boolean isError;

    public WebApiBaseResponse() {

    }

    public WebApiBaseResponse(final String statusCode, final String message) {

        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode
     *            the statusCode to set
     */
    public void setStatusCode(final String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return the isError
     */
    public boolean isError() {
        return isError;
    }

    /**
     * @param isError
     *            the isError to set
     */
    public void setError(final boolean isError) {
        this.isError = isError;
    }

}
