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
package com.ericsson.orchestration.solutiondevelopment.assurance.camunda.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ReadYmlProperties {

    @Value("${kafkaConfig.bootstrapServerIp}")
    private String kafkaUrl;

    @Value("${kafkaConfig.bootstrapServerPort}")
    private String kafkaPort;

    @Value("${kafkaConfig.topicName}")
    private String kafkaTopicName;

    @Value("${kafkaConfig.groupId}")
    private String topicGroupId;

    @Value("${ecmConfig.ecmUrl}")
    private String ecmUrl;

    @Value("${ecmConfig.tenantId}")
    private String tenantId;

    @Value("${ecmConfig.authHeader}")
    private String authHeader;

    @Value("${ecmConfig.vmImageName}")
    private String vmImageName;

    @Value("${bpmnConfig.baseBpmnFileName}")
    private String baseBpmnFileName;

    @Value("${bpmnConfig.restartBpmnFileName}")
    private String restartBpmnFileName;

    @Value("${bpmnConfig.scaleBpmnFileName}")
    private String scaleBpmnFileName;

    @Value("${bpmnConfig.healBpmnFileName}")
    private String healBpmnFileName;

    @Value("${interval.sleepInterval}")
    private String sleepInterval;

    @Value("${enmConfig.url}")
    private String enmURl;

    @Value("${enmConfig.username}")
    private String enmUsername;

    @Value("${enmConfig.password}")
    private String enmPassword;

    /**
     * @return the healBpmnFileName
     */
    public String getHealBpmnFileName() {
        return healBpmnFileName;
    }

    /**
     * @param healBpmnFileName
     *            the healBpmnFileName to set
     */
    public void setHealBpmnFileName(final String healBpmnFileName) {
        this.healBpmnFileName = healBpmnFileName;
    }

    /**
     * @return the enmURl
     */
    public String getEnmURl() {
        return enmURl;
    }

    /**
     * @param enmURl
     *            the enmURl to set
     */
    public void setEnmURl(final String enmURl) {
        this.enmURl = enmURl;
    }

    /**
     * @return the enmUsername
     */
    public String getEnmUsername() {
        return enmUsername;
    }

    /**
     * @param enmUsername
     *            the enmUsername to set
     */
    public void setEnmUsername(final String enmUsername) {
        this.enmUsername = enmUsername;
    }

    /**
     * @return the enmPassword
     */
    public String getEnmPassword() {
        return enmPassword;
    }

    /**
     * @param enmPassword
     *            the enmPassword to set
     */
    public void setEnmPassword(final String enmPassword) {
        this.enmPassword = enmPassword;
    }

    /**
     * @return the scaleBpmnFileName
     */
    public String getScaleBpmnFileName() {
        return scaleBpmnFileName;
    }

    /**
     * @param scaleBpmnFileName
     *            the scaleBpmnFileName to set
     */
    public void setScaleBpmnFileName(final String scaleBpmnFileName) {
        this.scaleBpmnFileName = scaleBpmnFileName;
    }

    /**
     * @return the sleepInterval
     */
    public String getSleepInterval() {
        return sleepInterval;
    }

    /**
     * @param sleepInterval
     *            the sleepInterval to set
     */
    public void setSleepInterval(final String sleepInterval) {
        this.sleepInterval = sleepInterval;
    }

    /**
     * @return the ecmUrl
     */
    public String getEcmUrl() {
        return ecmUrl;
    }

    /**
     * @param ecmUrl
     *            the ecmUrl to set
     */
    public void setEcmUrl(final String ecmUrl) {
        this.ecmUrl = ecmUrl;
    }

    /**
     * @return the tenantId
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId
     *            the tenantId to set
     */
    public void setTenantId(final String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * @return the authHeader
     */
    public String getAuthHeader() {
        return authHeader;
    }

    /**
     * @param authHeader
     *            the authHeader to set
     */
    public void setAuthHeader(final String authHeader) {
        this.authHeader = authHeader;
    }

    /**
     * @return the vmImageName
     */
    public String getVmImageName() {
        return vmImageName;
    }

    /**
     * @param vmImageName
     *            the vmImageName to set
     */
    public void setVmImageName(final String vmImageName) {
        this.vmImageName = vmImageName;
    }

    /**
     * @return the baseBpmnFileName
     */
    public String getBaseBpmnFileName() {
        return baseBpmnFileName;
    }

    /**
     * @param baseBpmnFileName
     *            the baseBpmnFileName to set
     */
    public void setBaseBpmnFileName(final String baseBpmnFileName) {
        this.baseBpmnFileName = baseBpmnFileName;
    }

    /**
     * @return the restartBpmnFileName
     */
    public String getRestartBpmnFileName() {
        return restartBpmnFileName;
    }

    /**
     * @param restartBpmnFileName
     *            the restartBpmnFileName to set
     */
    public void setRestartBpmnFileName(final String restartBpmnFileName) {
        this.restartBpmnFileName = restartBpmnFileName;
    }

    /**
     * @return the kafkaTopicName
     */
    public String getKafkaTopicName() {
        return kafkaTopicName;
    }

    /**
     * @param kafkaTopicName
     *            the kafkaTopicName to set
     */
    public void setKafkaTopicName(final String kafkaTopicName) {
        this.kafkaTopicName = kafkaTopicName;
    }

    /**
     * @return the topicGroupId
     */
    public String getTopicGroupId() {
        return topicGroupId;
    }

    /**
     * @param topicGroupId
     *            the topicGroupId to set
     */
    public void setTopicGroupId(final String topicGroupId) {
        this.topicGroupId = topicGroupId;
    }

    /**
     * @return the kafkaUrl
     */
    public String getKafkaUrl() {
        return kafkaUrl;
    }

    /**
     * @param kafkaUrl
     *            the kafkaUrl to set
     */
    public void setKafkaUrl(final String kafkaUrl) {
        this.kafkaUrl = kafkaUrl;
    }

    /**
     * @return the kafkaPort
     */
    public String getKafkaPort() {
        return kafkaPort;
    }

    /**
     * @param kafkaPort
     *            the kafkaPort to set
     */
    public void setKafkaPort(final String kafkaPort) {
        this.kafkaPort = kafkaPort;
    }

}
