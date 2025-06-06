package com.wanderfun.infrastructurelayer.configuration.modelmapper;

import com.wanderfun.applicationlayer.dto.posts.PostCreateDto;
import com.wanderfun.applicationlayer.dto.trips.TripPlaceCreateDto;
import com.wanderfun.domainlayer.model.auths.Account;
import com.wanderfun.domainlayer.model.auths.RefreshToken;
import com.wanderfun.domainlayer.model.images.Image;
import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.model.places.Section;
import com.wanderfun.domainlayer.model.posts.Comment;
import com.wanderfun.domainlayer.model.posts.Post;
import com.wanderfun.domainlayer.model.trips.Trip;
import com.wanderfun.domainlayer.model.trips.TripPlace;
import com.wanderfun.domainlayer.model.users.User;
import com.wanderfun.infrastructurelayer.persistence.entity.auths.AccountEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.auths.RefreshTokenEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.images.ImageEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.places.SectionEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.posts.CommentEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.trips.TripEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.users.UserEntity;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

        modelMapper.addConverter(new AbstractConverter<Collection<?>, List<?>>() {
            @Override
            protected List<?> convert(Collection<?> source) {
                return source == null ? null : new ArrayList<>(source);
            }
        });

        Converter<Long, Place> placeIdToPlaceConverter = ctx -> {
            Long placeId = ctx.getSource();
            if (placeId == null) {
                return null;
            }
            Place place = new Place();
            place.setId(placeId);
            return place;
        };

        Converter<Long, Trip> tripIdToTripConverter = ctx -> {
            Long tripId = ctx.getSource();
            if (tripId == null) {
                return null;
            }
            Trip trip = new Trip();
            trip.setId(tripId);
            return trip;
        };

        Converter<Long, AccountEntity> accountIdToAccountEntityConverter = ctx -> {
            Long accountId = ctx.getSource();
            if (accountId == null) {
                return null;
            }
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setId(accountId);
            return accountEntity;
        };

        // Trip
        modelMapper.typeMap(TripPlaceCreateDto.class, TripPlace.class)
                .addMappings(mapper -> mapper.using(placeIdToPlaceConverter)
                .map(TripPlaceCreateDto::getPlaceId, TripPlace::setPlace));

        modelMapper.typeMap(TripEntity.class, Trip.class)
                .addMapping(src -> src.getUser().getId(), Trip::setUserId);

        // RefreshToken
        modelMapper.typeMap(RefreshTokenEntity.class, RefreshToken.class)
                .addMapping(src -> src.getAccount().getId(), RefreshToken::setAccountId);

        // Post
        modelMapper.typeMap(PostCreateDto.class, Post.class)
                .addMappings(mapper -> {
                    mapper.using(placeIdToPlaceConverter)
                            .map(PostCreateDto::getPlaceId, Post::setPlace);
                    mapper.using(tripIdToTripConverter)
                            .map(PostCreateDto::getTripId, Post::setTrip);
                });

        // Comment
        modelMapper.typeMap(CommentEntity.class, Comment.class)
                .addMapping(src -> src.getPost().getId(), Comment::setPostId);

        // User
        modelMapper.typeMap(User.class, UserEntity.class)
                .addMappings(mapper ->
                    mapper.using(accountIdToAccountEntityConverter).map(User::getAccountId, UserEntity::setAccount));

        return modelMapper;
    }
}