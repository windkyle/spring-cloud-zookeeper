package com.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author doalso
 *
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigCenterApplication {
    public static void main( String[] args ) {
        SpringApplication.run(ConfigCenterApplication.class, args);
    }
}
