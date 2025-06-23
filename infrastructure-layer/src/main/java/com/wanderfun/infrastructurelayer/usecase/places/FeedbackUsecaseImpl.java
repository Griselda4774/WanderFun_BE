package com.wanderfun.infrastructurelayer.usecase.places;

import com.wanderfun.applicationlayer.dto.places.FeedbackCreateDto;
import com.wanderfun.applicationlayer.dto.places.FeedbackDto;
import com.wanderfun.applicationlayer.exception.NotHavePermissionException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.place.FeedbackService;
import com.wanderfun.applicationlayer.service.users.UserService;
import com.wanderfun.applicationlayer.usecase.places.FeedbackUsecase;
import com.wanderfun.applicationlayer.util.JwtUtil;
import com.wanderfun.domainlayer.model.places.Feedback;
import com.wanderfun.domainlayer.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FeedbackUsecaseImpl implements FeedbackUsecase {
    private final FeedbackService feedbackService;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public FeedbackUsecaseImpl(FeedbackService feedbackService, ObjectMapper objectMapper, UserService userService, JwtUtil jwtUtil) {
        this.feedbackService = feedbackService;
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public List<FeedbackDto> findAllByPlaceId(Long placeId) {
        return objectMapper.mapList(feedbackService.findAllByPlaceId(placeId), FeedbackDto.class);
    }

    @Override
    public FeedbackDto create(String accessToken, Long placeId, FeedbackCreateDto feedbackCreateDto) {
        Feedback feedback = objectMapper.map(feedbackCreateDto, Feedback.class);
        feedback.setPlaceId(placeId);
        feedback.setUser(new User());
        feedback.getUser().setId(userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId());

        Feedback savedFeedback = feedbackService.create(feedback);
        Feedback retrievedFeedback = feedbackService.findById(savedFeedback.getId());

        return objectMapper.map(retrievedFeedback, FeedbackDto.class);
    }

    @Override
    public FeedbackDto updateById(String accessToken, Long id, FeedbackCreateDto feedbackCreateDto) {
        Feedback currentFeedback = feedbackService.findById(id);
        Feedback feedback = objectMapper.map(feedbackCreateDto, Feedback.class);
        if (!Objects.equals(currentFeedback.getUser().getId(), jwtUtil.getIdFromToken(accessToken))) {
            throw new NotHavePermissionException("You don't have permission to update this feedback");
        }
        feedback.setUser(new User());
        feedback.getUser().setId(userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId());

        Feedback savedFeedback = feedbackService.updateById(id, feedback);
        Feedback retrievedFeedback = feedbackService.findById(savedFeedback.getId());

        return objectMapper.map(retrievedFeedback, FeedbackDto.class);
    }

    @Override
    public boolean deleteById(String accessToken, Long id) {
        Feedback currentFeedback = feedbackService.findById(id);
        if (Objects.equals(currentFeedback.getUser().getId(), userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId())) {
            feedbackService.deleteById(id);
        }
        return true;
    }
}
