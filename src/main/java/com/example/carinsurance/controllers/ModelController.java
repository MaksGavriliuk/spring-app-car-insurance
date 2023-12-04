package com.example.carinsurance.controllers;

import com.example.carinsurance.dtos.ModelDTO;
import com.example.carinsurance.models.Model;
import com.example.carinsurance.services.ModelService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;


    @GetMapping
    public List<Model> getModels(@RequestParam(name = "brand", required = false) String brand) {
        return modelService.listModels(brand);
    }

    @PostMapping("/create")
    public ResponseEntity<Model> createModel(@RequestBody ModelDTO modelDTO) {
        Model model = modelService.saveModel(modelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Integer id) {
        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable Integer id, @RequestBody ModelDTO modelDTO) {
        Model model = modelService.updateModel(id, modelDTO);
        return ResponseEntity.ok().body(model);
    }

    @GetMapping("/{id}")
    public Model getModelById(@PathVariable Integer id) {
        return modelService.getModelById(id);
    }

    @GetMapping("/pages/{page}")
    public List<Model> get(@PathVariable(name = "page") int page) {
        return modelService.getModelPagination(page, 2).getContent();
    }

}