package com.hcl.hackathon;

import com.hcl.hackathon.entity.CibilScoreEntity;
import com.hcl.hackathon.repository.CibilScoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
/**
 * The this is the main SpringBootApplication.
 *
 */
@SpringBootApplication
public class CibilScoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CibilScoreApplication.class, args);

	}
		@Bean
		CommandLineRunner init (CibilScoreRepository cibilScoreRepository){
			return args -> {
				CibilScoreEntity cibilScoreDetailsEntity=new CibilScoreEntity();
				cibilScoreDetailsEntity.setCibilScore(860.0);
				cibilScoreDetailsEntity.setName("Johan");
				cibilScoreDetailsEntity.setPanCardNo("BIH9898998");
				CibilScoreEntity cibilScoreDetailsResponse=new CibilScoreEntity();
				cibilScoreDetailsResponse.setCibilScore(600.0);
				cibilScoreDetailsResponse.setName("Johan");
				cibilScoreDetailsResponse.setPanCardNo("ABD2332222");
				List<CibilScoreEntity> cibilScoreEntity = Arrays.asList(cibilScoreDetailsEntity,cibilScoreDetailsResponse);
				cibilScoreEntity.forEach(cibilScoreDetail -> cibilScoreRepository.save(cibilScoreDetail));
			};
		}
	}

