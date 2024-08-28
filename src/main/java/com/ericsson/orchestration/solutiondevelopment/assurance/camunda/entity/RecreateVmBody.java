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

public class RecreateVmBody {

    private BootSource bootSource;

    /**
     * @return the bootSource
     */
    public BootSource getBootSource() {
        return bootSource;
    }

    /**
     * @param bootSource
     *            the bootSource to set
     */
    public void setBootSource(final BootSource bootSource) {
        this.bootSource = bootSource;
    }

}
