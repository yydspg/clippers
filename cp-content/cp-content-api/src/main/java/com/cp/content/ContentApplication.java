package com.cp.content;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
//TODO 理解启动类
/**
 * content model start class
 * @author paul
 */
@Slf4j
@SpringBootApplication
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication app=new SpringApplication(ContentApplication.class);
        ConfigurableApplicationContext application=app.run(args);
        Environment env = application.getEnvironment();
        log.info("server start");
        try {
            log.info("\n----------------------------------------------------------\n\t" +
                            "Application '{}' is running! \n\tAccess URLs:\n\t" +
                            "Local: \t\thttp://localhost:{}/content\n\t" +
                            "swagger:\thttp://localhost:{}/content/doc.html\n\t"+
                            "External: \thttp://{}:{}/content\n\t"+
                            "Doc: \thttp://{}:{}/content/doc.html\n"+
                            "----------------------------------------------------------",
                    env.getProperty("spring.application.name"),
                    env.getProperty("server.port"),
                    env.getProperty("server.port"),
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"),
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"));
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
