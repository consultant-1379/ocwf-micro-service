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
package com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity;

public class GetVappOperationData {

    private VappData vapp;

    /**
     * @return the vapp
     */
    public VappData getVapp() {
        return vapp;
    }

    /**
     * @param vapp
     *            the vapp to set
     */
    public void setVapp(final VappData vapp) {
        this.vapp = vapp;
    }

}
