package com.example.carinsurance.controllers;

import com.example.carinsurance.dtos.FeedbackDTO;
import com.example.carinsurance.models.Feedback;
import com.example.carinsurance.models.Model;
import com.example.carinsurance.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;


    @GetMapping
    public List<Feedback> getFeedbacks() {
        return feedbackService.listFeedbacks();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.saveFeedback(feedbackDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Integer id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Feedback getFeedbackById(@PathVariable Integer id) {
        return feedbackService.getFeedbackById(id);
    }


}
