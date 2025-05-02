package com.wanderfun.infrastructurelayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.wanderfun")
@EnableJpaAuditing
public class WanderfunApplication {

	public static void main(String[] args) {
		SpringApplication.run(WanderfunApplication.class, args);
	}

}
