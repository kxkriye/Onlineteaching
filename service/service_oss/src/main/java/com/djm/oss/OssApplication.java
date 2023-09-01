package com.djm.oss;

/**
 * @author djm
 * @create 2021-09-19 16:00
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.djm"})
@EnableDiscoveryClient  //nacos注册
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}