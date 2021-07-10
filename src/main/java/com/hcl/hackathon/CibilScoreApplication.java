package com.hcl.hackathon;

import com.hcl.hackathon.entity.CibilScoreEntity;
import com.hcl.hackathon.repository.CibilScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

/**
 * The this is the main SpringBootApplication.
 */
@SpringBootApplication
public class CibilScoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CibilScoreApplication.class, args);

    }

}

