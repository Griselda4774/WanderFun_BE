package com.wanderfun.infrastructurelayer.configuration.modelmapper;

//import com.wanderfun.domainlayer.model.*;
//import com.wanderfun.domainlayer.model.places.Feedback;
//import com.wanderfun.domainlayer.model.places.Section;
//import com.wanderfun.domainlayer.model.trips.Trip;
//import com.wanderfun.domainlayer.model.trips.TripPlace;
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
                .setSkipNullEnabled(true)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setPropertyCondition(context -> context.getSource() != null)
                .setMatchingStrategy(MatchingStrategies.STRICT);

//        Converter<String, Date> stringToDateConverter = context -> {
//            SimpleDateFormat dateFormat = new SimpleDateFormat(CUSTOM_DATE_FORMAT, Locale.ENGLISH);
//            try {
//                return dateFormat.parse(context.getSource());
//            } catch (ParseException e) {
//                throw new RuntimeException("Failed to parse date: " + context.getSource(), e);
//            }
//        };
//
//        Converter<Date, String> dateToStringConverter = context -> {
//            SimpleDateFormat dateFormat = new SimpleDateFormat(CUSTOM_DATE_FORMAT, Locale.ENGLISH);
//            return dateFormat.format(context.getSource());
//        };
//
//        modelMapper.addConverter(stringToDateConverter);
//        modelMapper.addConverter(dateToStringConverter);



//        modelMapper.createTypeMap(AlbumImageEntity.class, AlbumImage.class)
//                .addMapping(
//                        src -> src.getAlbum().getId(),
//                        AlbumImage::setAlbumId
//                );
//
//        modelMapper.createTypeMap(AlbumEntity.class, Album.class)
//                .addMapping(
//                        src -> src.getUser().getId(),
//                        Album::setUserId
//                );
//
//        modelMapper.createTypeMap(FavouritePlaceEntity.class, FavouritePlace.class)
//                .addMapping(
//                        src -> src.getUser().getId(),
//                        FavouritePlace::setUserId
//                );
//
//        modelMapper.createTypeMap(CheckInEntity.class, CheckIn.class)
//                .addMapping(
//                        src -> src.getUser().getId(),
//                        CheckIn::setUserId
//                );
//
//        modelMapper.createTypeMap(FeedbackEntity.class, Feedback.class)
//                .addMapping(
//                        src -> src.getPlace().getId(),
//                        Feedback::setPlaceId
//                );
//
//        modelMapper.createTypeMap(FeedbackImageEntity.class, FeedbackImage.class)
//                .addMapping(
//                        src -> src.getFeedback().getId(),
//                        FeedbackImage::setFeedbackId
//                );
//
//
//        modelMapper.createTypeMap(PlaceImageEntity.class, PlaceImage.class)
//                .addMapping(
//                        src -> src.getPlace().getId(),
//                        PlaceImage::setPlaceId
//                );
//
//        modelMapper.createTypeMap(SectionEntity.class, Section.class)
//                .addMapping(
//                        src -> src.getPlace().getId(),
//                        Section::setPlaceId
//                );
//
//        modelMapper.createTypeMap(TripEntity.class, Trip.class)
//                .addMapping(
//                        src -> src.getUser().getId(),
//                        Trip::setUserId
//                );
//
//        modelMapper.createTypeMap(TripPlaceEntity.class, TripPlace.class)
//                .addMapping(
//                        src -> src.getTrip().getId(),
//                        TripPlace::setTripId
//                );

        return modelMapper;
    }
}