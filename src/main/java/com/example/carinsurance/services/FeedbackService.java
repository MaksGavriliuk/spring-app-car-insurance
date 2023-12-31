package com.example.carinsurance.services;

import com.example.carinsurance.dtos.FeedbackDTO;
import com.example.carinsurance.exceptions.FeedbackException;
import com.example.carinsurance.exceptions.UserAuthenticationException;
import com.example.carinsurance.models.Feedback;
import com.example.carinsurance.repositories.FeedbackRepository;
import com.example.carinsurance.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;


    public List<Feedback> listFeedbacks() {
        return feedbackRepository.findAll();
    }

    public void saveFeedback(FeedbackDTO feedbackDTO) {
        userRepository.findById(feedbackDTO.getUserId())
                .orElseThrow(() -> new UserAuthenticationException("Пользователя с таким id не существует"));
        Feedback feedback = mapFeedbackDTOToFeedback(feedbackDTO);
        feedbackRepository.save(feedback);
    }

    public void deleteFeedback(int id) {
        feedbackRepository.deleteById(id);
    }

    public void updateFeedback(int id, FeedbackDTO feedbackDTO) {

        userRepository.findById(feedbackDTO.getUserId())
                .orElseThrow(() -> new UserAuthenticationException("Пользователя с таким id не существует"));

        Feedback feedback = mapFeedbackDTOToFeedback(feedbackDTO);
        feedback.setId(id);
        feedbackRepository.save(feedback);

    }

    public Feedback getFeedbackById(int id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new FeedbackException("Отзыва с таким id не существует"));
    }

    public Feedback mapFeedbackDTOToFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setFeedback(feedbackDTO.getFeedback());
        feedback.setNumberOfStars(feedbackDTO.getNumberOfStars());
        feedback.setUser(userRepository.findById(feedbackDTO.getUserId()).orElse(null));
        return feedback;
    }

}
