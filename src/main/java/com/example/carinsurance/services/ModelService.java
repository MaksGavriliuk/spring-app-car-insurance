package com.example.carinsurance.services;

import com.example.carinsurance.dtos.ModelDTO;
import com.example.carinsurance.exceptions.BrandNotFoundException;
import com.example.carinsurance.repositories.BrandRepository;
import com.example.carinsurance.repositories.ModelRepository;
import com.example.carinsurance.models.Model;
import com.example.carinsurance.models.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private BrandRepository brandRepository;


    public List<Model> listModels(String modelName) {
        if (modelName != null) {
            return modelRepository.findByModel(modelName);
        }
        return modelRepository.findAll();
    }

    public void saveModel(ModelDTO modelDTO) {
        brandRepository.findById(modelDTO.getBrandId()).orElseThrow(() -> new BrandNotFoundException("Бренд не существует"));
        Model model = mapModelDTOToModel(modelDTO);
        modelRepository.save(model);
    }

    public void deleteModel(int id) {
        modelRepository.deleteById(id);
    }

    public Model getModelById(int id) {
        return modelRepository.findById(id).orElse(null);
    }

    public Model mapModelDTOToModel(ModelDTO modelDTO) {
        Model model = new Model();
        model.setModel(modelDTO.getModel());
        model.setBrand(brandRepository.findById(modelDTO.getBrandId()).orElseThrow());
        return model;
    }

}