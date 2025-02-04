package com.kalayciburak.filterservice;

import com.kalayciburak.commonpackage.core.constant.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = Paths.ConfigurationBasePackage)
public class FilterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterServiceApplication.class, args);
    }

}
