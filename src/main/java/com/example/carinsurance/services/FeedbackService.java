package com.example.carinsurance.services;

import com.example.carinsurance.exceptions.FeedbackException;
import com.example.carinsurance.models.Feedback;
import com.example.carinsurance.repositories.FeedbackRepository;
import com.example.carinsurance.repositories.FuelTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;


    public List<Feedback> listFeedbacks() {
        return feedbackRepository.findAll();
    }


    public void deleteFeedback(int id) {
        feedbackRepository.deleteById(id);
    }

    public Feedback getFeedbackById(int id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new FeedbackException("Отзыва с таким id не существует"));
    }

}
