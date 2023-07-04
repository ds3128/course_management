package org.uy1.uemanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.uy1.uemanagement.entities.Course;

@SpringBootApplication
public class UeManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(UeManagementApplication.class, args);
    }

}
