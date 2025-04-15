package com.wanderfun.infrastructurelayer.configuration.modelmapper;

import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
//    private static final String CUSTOM_DATE_FORMAT = "MMM dd, yyyy hh:mm:ss a";

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
//                .setSkipNullEnabled(true)
//                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setPropertyCondition(context -> context.getSource() != null)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}