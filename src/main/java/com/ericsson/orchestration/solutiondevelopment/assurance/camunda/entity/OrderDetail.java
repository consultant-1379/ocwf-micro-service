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

public class OrderDetail {

    private long creationDate;
    private long completionDate;
    private String userId;

    private String id;
    private String tenantName;
    private String orderReqStatus;

    /**
     * @return the creationDate
     */
    public long getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(final long creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the completionDate
     */
    public long getCompletionDate() {
        return completionDate;
    }

    /**
     * @param completionDate
     *            the completionDate to set
     */
    public void setCompletionDate(final long completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(final String userId) {
        this.userId = userId;
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
     * @return the tenantName
     */
    public String getTenantName() {
        return tenantName;
    }

    /**
     * @param tenantName
     *            the tenantName to set
     */
    public void setTenantName(final String tenantName) {
        this.tenantName = tenantName;
    }

    /**
     * @return the orderReqStatus
     */
    public String getOrderReqStatus() {
        return orderReqStatus;
    }

    /**
     * @param orderReqStatus
     *            the orderReqStatus to set
     */
    public void setOrderReqStatus(final String orderReqStatus) {
        this.orderReqStatus = orderReqStatus;
    }

}
