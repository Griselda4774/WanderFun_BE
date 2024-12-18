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

import java.util.ArrayList;
import java.util.List;


@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setPropertyCondition(Conditions.isNotNull())
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        modelMapper.addConverter(new AbstractConverter<List<Object>, List<Object>>() {
            @Override
            protected List<Object> convert(List<Object> sourceList) {
                if (sourceList == null) {
                    return new ArrayList<>();
                }

                List<Object> destinationList = new ArrayList<>();

                for (int i = 0; i < sourceList.size(); i++) {
                    Object sourceElement = sourceList.get(i);

                    if (i >= destinationList.size()) {
                        Object destinationElement = modelMapper.map(sourceElement, sourceElement.getClass());
                        destinationList.add(destinationElement);
                    } else {
                        Object existingDestElement = destinationList.get(i);
                        modelMapper.map(sourceElement, existingDestElement);
                    }
                }

                while (destinationList.size() > sourceList.size()) {
                    destinationList.remove(destinationList.size() - 1);
                }

                return destinationList;
            }
        });

        return modelMapper;
    }
}