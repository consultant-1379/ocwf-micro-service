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
package com.ericsson.orchestration.solutiondevelopment.assurance.camunda.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.*;
import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.config.ReadYmlProperties;
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.config.Utils;
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.entity.ActionMessage;
import com.ericsson.orchestration.solutiondevelopment.assurance.camunda.parser.JsonParser;
import com.ericsson.orchestration.solutiondevelopment.assurance.processor.ActionMessageProcessorImpl;
import com.ericsson.orchestration.solutiondevelopment.assurance.processor.ActionMessageProcessorLocal;

public class CamundaKafkaConsumer extends Thread {

    private final Logger LOGGER = LoggerFactory.getLogger(CamundaKafkaConsumer.class.getName());

    private RuntimeService runtimeService;

    private KafkaConsumer<String, String> kafkaConsumer;

    private boolean running = false;

    private ReadYmlProperties readYmlProperties;

    public CamundaKafkaConsumer(final RuntimeService runtimeService, final ReadYmlProperties readYmlProperties) {

        this.runtimeService = runtimeService;

        this.readYmlProperties = readYmlProperties;
    }

    //    @Override
    //    public void run() {

    //        LOGGER.info("**********reading properties.... ");

    //        LOGGER.info("kafka url::" + readYmlProperties.getKafkaUrl());

    //        LOGGER.info("kafka port::" + readYmlProperties.getKafkaPort());

    //   }

    @Override
    public void run() {

        final String topicName = readYmlProperties.getKafkaTopicName();
        final Properties props = new Properties();

        props.put("bootstrap.servers", readYmlProperties.getKafkaUrl() + ":" + readYmlProperties.getKafkaPort());
        //props.put("group.id", readYmlProperties.getTopicGroupId());
        props.put("group.id", "camunda-0987612345");
        props.put("enable.auto.commit", Utils.DEFAULT_ENABLE_AUTO_COMMIT);
        props.put("auto.commit.interval.ms", Utils.DEFAULT_AUTO_COMMIT_TIME);
        props.put("session.timeout.ms", Utils.DEFAULT_SESSION_TIMEOUT);
        props.put("key.deserializer", Utils.DEFAULT_KEY_DESERIALIZER);
        props.put("value.deserializer", Utils.DEFAULT_VALUE_DESERIALIZER);
        kafkaConsumer = new KafkaConsumer<String, String>(props);

        //Kafka Consumer subscribes list of topics here.
        kafkaConsumer.subscribe(Arrays.asList(topicName));

        //print the topic name
        LOGGER.info("Subscribed to topic " + topicName);

        while (this.isAlive() && !running) {
            try {
                final ConsumerRecords<String, String> records = kafkaConsumer.poll(Utils.DEFAULT_CONSUMER_POLL_TIME);
                for (final ConsumerRecord<String, String> record : records) {

                    LOGGER.info("######consumer received json message::" + record.value());

                    // eventReceiver.receiveEvent(record.value());

                    LOGGER.info("Message received from kafka: " + record.value());

                    LOGGER.info("thread name::" + Thread.currentThread().getName());

                    final ActionMessage actionMessage = JsonParser.parseActionMessage(record.value());

                    if (actionMessage != null) {

                        final ActionMessageProcessorLocal actionMessageProcessorLocal = new ActionMessageProcessorImpl();
                        actionMessageProcessorLocal.processAction(runtimeService, actionMessage, readYmlProperties);

                    } else {
                        LOGGER.info("action message is rejected");
                    }

                    LOGGER.info("process end message received" + Thread.currentThread().getName());
                }
            } catch (final Exception e) {
                LOGGER.info("error receiving events on thread {}", this.getName(), e);
            }
        }

        if (!this.isInterrupted()) {
            kafkaConsumer.close();
        }
    }

    public void stopConsumer() {
        running = true;

        while (this.isAlive()) {
            try {
                Thread.sleep(Utils.DEFAULT_CONSUMER_POLL_TIME);
            } catch (final InterruptedException e) {
                LOGGER.warn("Interrupted Exception" + e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
