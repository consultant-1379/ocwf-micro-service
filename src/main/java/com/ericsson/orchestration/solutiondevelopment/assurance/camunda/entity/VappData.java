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

public class VappData {

    private String id;
    private String sourceId;
    private String managementSystemId;

    private VnfmData vnfm;

    /**
     * @return the vnfm
     */
    public VnfmData getVnfm() {
        return vnfm;
    }

    /**
     * @param vnfm
     *            the vnfm to set
     */
    public void setVnfm(final VnfmData vnfm) {
        this.vnfm = vnfm;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @return the sourceId
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId
     *            the sourceId to set
     */
    public void setSourceId(final String sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * @return the managementSystemId
     */
    public String getManagementSystemId() {
        return managementSystemId;
    }

    /**
     * @param managementSystemId
     *            the managementSystemId to set
     */
    public void setManagementSystemId(final String managementSystemId) {
        this.managementSystemId = managementSystemId;
    }

}
