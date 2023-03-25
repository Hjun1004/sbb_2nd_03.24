package com.mysite.sbb_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SbbV2Application {

	public static void main(String[] args) {
		SpringApplication.run(SbbV2Application.class, args);
	}

}
