package com.wanderfun.infrastructurelayer;

import com.wanderfun.infrastructurelayer.configuration.dotenv.DotenvApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.wanderfun")
@EnableJpaAuditing
public class WanderfunApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WanderfunApplication.class);
		app.addInitializers(new DotenvApplicationContextInitializer());
		app.run(args);
	}

}
