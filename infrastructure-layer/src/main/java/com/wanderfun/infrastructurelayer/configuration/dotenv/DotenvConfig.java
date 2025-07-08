//package com.wanderfun.infrastructurelayer.configuration.dotenv;
//
//import io.github.cdimascio.dotenv.Dotenv;
//import jakarta.annotation.PostConstruct;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DotenvConfig {
//    @PostConstruct
//    public void loadEnv() {
//        Dotenv dotenv = Dotenv.configure().load();
//        System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
//        System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));
//        System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
//        System.setProperty("DATABASE_USERNAME", dotenv.get("DATABASE_USERNAME"));
//        System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));
//    }
//}