package com.example.carinsurance.controllers;

import com.example.carinsurance.models.Model;
import com.example.carinsurance.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/models")
public class ModelController {

    @Autowired
    private ModelService modelService;


    @GetMapping
    public List<Model> getModels(
            @RequestParam(name = "model", required = false) String model) {
        return modelService.listModels(model);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createModel(
            @RequestParam("brand-name") String brandName,
            @RequestBody Model model) {
        modelService.saveModel(model, brandName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Integer id) {
        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Model getModelById(@PathVariable Integer id) {
        return modelService.getModelById(id);
    }

}