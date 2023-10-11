package com.example.carinsurance.controllers;

import com.example.carinsurance.models.Model;
import com.example.carinsurance.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
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
            @RequestParam(name = "model", required = false) String model)//,
//            @RequestParam(name = "brand", required = false) String brand)
    {
        return modelService.listModels(model);
    }

    @GetMapping("/{id}")
    public Model getModelInfo(@PathVariable Integer id) {
        return modelService.getModelById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createModel(
            @RequestBody Model model,
            @RequestParam("brandName") String brandName)
    {
        modelService.saveModel(model, brandName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Integer id) {
        modelService.deleteModel(id);
        return ResponseEntity.ok().build();
    }

}
