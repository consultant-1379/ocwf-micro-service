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

public class GetVappOperationResponse {

    private OrderStatus status;

    private GetVappOperationData data;

    /**
     * @return the status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final OrderStatus status) {
        this.status = status;
    }

    /**
     * @return the data
     */
    public GetVappOperationData getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(final GetVappOperationData data) {
        this.data = data;
    }

}
