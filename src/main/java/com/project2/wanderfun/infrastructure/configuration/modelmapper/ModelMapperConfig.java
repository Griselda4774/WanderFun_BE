package com.project2.wanderfun.infrastructure.configuration.modelmapper;

import com.project2.wanderfun.application.dto.album.AlbumCreateDto;
import com.project2.wanderfun.application.dto.feedback.FeedbackCreateDto;
import com.project2.wanderfun.application.dto.place.PlaceCreateDto;
import com.project2.wanderfun.application.dto.trip.TripCreateDto;
import com.project2.wanderfun.domain.model.Album;
import com.project2.wanderfun.domain.model.Feedback;
import com.project2.wanderfun.domain.model.Place;
import com.project2.wanderfun.domain.model.Trip;
import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setPropertyCondition(context -> context.getSource() != null);

        return modelMapper;
    }
}