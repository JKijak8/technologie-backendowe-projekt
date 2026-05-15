package com.jkpbmz.technologiebackendoweprojekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class TechnologieBackendoweProjektApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnologieBackendoweProjektApplication.class, args);
    }

}
