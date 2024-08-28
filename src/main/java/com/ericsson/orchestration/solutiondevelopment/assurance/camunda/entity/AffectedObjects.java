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

public class AffectedObjects {

    private String assetType;
    private String assetName;
    private String assetId;
    private String vdcName;
    private String vdcId;

    /**
     * @return the assetType
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * @param assetType
     *            the assetType to set
     */
    public void setAssetType(final String assetType) {
        this.assetType = assetType;
    }

    /**
     * @return the assetName
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * @param assetName
     *            the assetName to set
     */
    public void setAssetName(final String assetName) {
        this.assetName = assetName;
    }

    /**
     * @return the assetId
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * @param assetId
     *            the assetId to set
     */
    public void setAssetId(final String assetId) {
        this.assetId = assetId;
    }

    /**
     * @return the vdcName
     */
    public String getVdcName() {
        return vdcName;
    }

    /**
     * @param vdcName
     *            the vdcName to set
     */
    public void setVdcName(final String vdcName) {
        this.vdcName = vdcName;
    }

    /**
     * @return the vdcId
     */
    public String getVdcId() {
        return vdcId;
    }

    /**
     * @param vdcId
     *            the vdcId to set
     */
    public void setVdcId(final String vdcId) {
        this.vdcId = vdcId;
    }

}
