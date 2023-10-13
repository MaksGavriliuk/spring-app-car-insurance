package com.example.carinsurance.services;

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

    public void saveModel(Model model, String brandName) {
        List<Brand> brands = brandRepository.findByBrand(brandName);
        Brand brand;

        if (brands.isEmpty()) {
            brand = new Brand();
            brand.setBrand(brandName);
            brandRepository.save(brand);
        } else {
            brand = brands.get(0);
        }

        model.setBrand(brand);
        modelRepository.save(model);

    }

    public void deleteModel(int id) {
        modelRepository.deleteById(id);
    }

    public Model getModelById(int id) {
        return modelRepository.findById(id).orElse(null);
    }

}