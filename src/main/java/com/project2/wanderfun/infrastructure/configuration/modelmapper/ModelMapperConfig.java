package com.project2.wanderfun.infrastructure.configuration.modelmapper;

import com.project2.wanderfun.domain.model.*;
import com.project2.wanderfun.infrastructure.persistence.entity.*;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setPropertyCondition(context -> context.getSource() != null)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(AlbumImageEntity.class, AlbumImage.class)
                .addMapping(
                        src -> src.getAlbum().getId(),
                        AlbumImage::setAlbumId
                );

        modelMapper.createTypeMap(FavouritePlaceEntity.class, FavouritePlace.class)
                .addMapping(
                        src -> src.getUser().getId(),
                        FavouritePlace::setUserId
                );

        modelMapper.createTypeMap(FeedbackEntity.class, Feedback.class)
                .addMapping(
                        src -> src.getPlace().getId(),
                        Feedback::setPlaceId
                );

        modelMapper.createTypeMap(FeedbackImageEntity.class, FeedbackImage.class)
                .addMapping(
                        src -> src.getFeedback().getId(),
                        FeedbackImage::setFeedbackId
                );


        modelMapper.createTypeMap(PlaceImageEntity.class, PlaceImage.class)
                .addMapping(
                        src -> src.getPlace().getId(),
                        PlaceImage::setPlaceId
                );

        modelMapper.createTypeMap(SectionEntity.class, Section.class)
                .addMapping(
                        src -> src.getPlace().getId(),
                        Section::setPlaceId
                );

        modelMapper.createTypeMap(TripEntity.class, Trip.class)
                .addMapping(
                        src -> src.getUser().getId(),
                        Trip::setUserId
                );

        modelMapper.createTypeMap(TripPlaceEntity.class, TripPlace.class)
                .addMapping(
                        src -> src.getTrip().getId(),
                        TripPlace::setTripId
                );

        return modelMapper;
    }
}