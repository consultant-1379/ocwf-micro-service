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

import java.util.List;

public class ActionMessage {

    private String name;
    private String nameSpace;
    private String version;
    private String source;
    private String target;
    private String actionDateTime;
    private String action;
    private String faultAssetType;
    private String faultAssetName;
    private String faultAssetId;
    private String vdcName;
    private String vdcId;
    private String additionalText;

    private String aspectId;
    private String noOfSteps;
    private List<AffectedObjects> affectedObjects;

    /**
     * @return the aspectId
     */
    public String getAspectId() {
        return aspectId;
    }

    /**
     * @param aspectId
     *            the aspectId to set
     */
    public void setAspectId(final String aspectId) {
        this.aspectId = aspectId;
    }

    /**
     * @return the noOfSteps
     */
    public String getNoOfSteps() {
        return noOfSteps;
    }

    /**
     * @param noOfSteps
     *            the noOfSteps to set
     */
    public void setNoOfSteps(final String noOfSteps) {
        this.noOfSteps = noOfSteps;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the nameSpace
     */
    public String getNameSpace() {
        return nameSpace;
    }

    /**
     * @param nameSpace
     *            the nameSpace to set
     */
    public void setNameSpace(final String nameSpace) {
        this.nameSpace = nameSpace;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource(final String source) {
        this.source = source;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     *            the target to set
     */
    public void setTarget(final String target) {
        this.target = target;
    }

    /**
     * @return the actionDateTime
     */
    public String getActionDateTime() {
        return actionDateTime;
    }

    /**
     * @param actionDateTime
     *            the actionDateTime to set
     */
    public void setActionDateTime(final String actionDateTime) {
        this.actionDateTime = actionDateTime;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(final String action) {
        this.action = action;
    }

    /**
     * @return the faultAssetType
     */
    public String getFaultAssetType() {
        return faultAssetType;
    }

    /**
     * @param faultAssetType
     *            the faultAssetType to set
     */
    public void setFaultAssetType(final String faultAssetType) {
        this.faultAssetType = faultAssetType;
    }

    /**
     * @return the faultAssetName
     */
    public String getFaultAssetName() {
        return faultAssetName;
    }

    /**
     * @param faultAssetName
     *            the faultAssetName to set
     */
    public void setFaultAssetName(final String faultAssetName) {
        this.faultAssetName = faultAssetName;
    }

    /**
     * @return the faultAssetId
     */
    public String getFaultAssetId() {
        return faultAssetId;
    }

    /**
     * @param faultAssetId
     *            the faultAssetId to set
     */
    public void setFaultAssetId(final String faultAssetId) {
        this.faultAssetId = faultAssetId;
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

    /**
     * @return the affectedObjects
     */
    public List<AffectedObjects> getAffectedObjects() {
        return affectedObjects;
    }

    /**
     * @param affectedObjects
     *            the affectedObjects to set
     */
    public void setAffectedObjects(final List<AffectedObjects> affectedObjects) {
        this.affectedObjects = affectedObjects;
    }

    /**
     * @return the additionalText
     */
    public String getAdditionalText() {
        return additionalText;
    }

    /**
     * @param additionalText
     *            the additionalText to set
     */
    public void setAdditionalText(final String additionalText) {
        this.additionalText = additionalText;
    }

}
