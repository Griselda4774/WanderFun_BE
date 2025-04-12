package com.wanderfun.infrastructurelayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wanderfun")
public class WanderfunApplication {

	public static void main(String[] args) {
		SpringApplication.run(WanderfunApplication.class, args);
	}

}
