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
package com.ericsson.orchestration.solutiondevelopment.assurance.camunda;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.config.ReadYmlProperties;
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.kafka.CamundaKafkaConsumer;

@SpringBootApplication
@EnableProcessApplication
public class CamundaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamundaApplication.class.getName());

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ReadYmlProperties readYmlProperties;

    public static void main(final String... args) {
        LOGGER.info("********starting........");
        SpringApplication.run(CamundaApplication.class, args);
    }

    @EventListener
    private void processPostDeploy(final PostDeployEvent event) {

        LOGGER.info("*************calling process post deploy....");
        final Thread consumerThread = new CamundaKafkaConsumer(runtimeService, readYmlProperties);
        consumerThread.setDaemon(true);
        consumerThread.start();
        LOGGER.info("*************calling process post deploy end....");
    }
}
