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

public class OrderStatus {

    private String reqStatus;
    private String credentials;

    /**
     * @return the reqStatus
     */
    public String getReqStatus() {
        return reqStatus;
    }

    /**
     * @param reqStatus
     *            the reqStatus to set
     */
    public void setReqStatus(final String reqStatus) {
        this.reqStatus = reqStatus;
    }

    /**
     * @return the credentials
     */
    public String getCredentials() {
        return credentials;
    }

    /**
     * @param credentials
     *            the credentials to set
     */
    public void setCredentials(final String credentials) {
        this.credentials = credentials;
    }

}
