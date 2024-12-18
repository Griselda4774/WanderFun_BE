package com.project2.wanderfun.infrastructure.configuration.modelmapper;

import com.project2.wanderfun.application.dto.album.AlbumCreateDto;
import com.project2.wanderfun.application.dto.feedback.FeedbackCreateDto;
import com.project2.wanderfun.application.dto.place.PlaceCreateDto;
import com.project2.wanderfun.application.dto.trip.TripCreateDto;
import com.project2.wanderfun.domain.model.Album;
import com.project2.wanderfun.domain.model.Feedback;
import com.project2.wanderfun.domain.model.Place;
import com.project2.wanderfun.domain.model.Trip;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

//        modelMapper.addMappings(new PropertyMap<Album, Album>() {
//            @Override
//            protected void configure() {
//                for (int i = 0; i < source.getAlbumImages().size(); i++) {
//
//                    map(source.getAlbumImages().get(i), destination.getAlbumImages().get(i));
//                }
//            }
//        });
//
//        modelMapper.addMappings(new PropertyMap<Feedback, Feedback>() {
//            @Override
//            protected void configure() {
//                map(source.getFeedbackImages(), destination.getFeedbackImages());
//            }
//        });
//
//        modelMapper.addMappings(new PropertyMap<Place, Place>() {
//            @Override
//            protected void configure() {
//                map(source.getDescription(), destination.getDescription());
//                map(source.getPlaceImages(), destination.getPlaceImages());
//            }
//        });
//
//        modelMapper.addMappings(new PropertyMap<Trip, Trip>() {
//            @Override
//            protected void configure() {
//                map(source.getTripPlaces(), destination.getTripPlaces());
//            }
//        });

        return modelMapper;
    }
}