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

public class OrderData {

    private OrderDetail order;

    /**
     * @return the order
     */
    public OrderDetail getOrder() {
        return order;
    }

    /**
     * @param order
     *            the order to set
     */
    public void setOrder(final OrderDetail order) {
        this.order = order;
    }

}
