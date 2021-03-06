package com.cloud.provider.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName FaceConfiguration
 * @Description TODO
 * @Author Administrator
 * @DATE 2018/11/6 13:18
 */
@ConfigurationProperties(value = "huawei.face")
public class FaceConfiguration {
    private String region;
    private String endPoint;
    private String accessKey;
    private String secretKey;
    private String serviceName;
    private String projectId;
    private String apiVersion;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }
}
