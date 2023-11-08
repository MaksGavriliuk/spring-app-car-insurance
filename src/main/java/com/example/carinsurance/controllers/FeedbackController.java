package com.example.carinsurance.controllers;

import com.example.carinsurance.dtos.FeedbackDTO;
import com.example.carinsurance.models.Feedback;
import com.example.carinsurance.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;


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

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFeedback(@PathVariable Integer id, @RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.updateFeedback(id, feedbackDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Feedback getFeedbackById(@PathVariable Integer id) {
        return feedbackService.getFeedbackById(id);
    }


}
