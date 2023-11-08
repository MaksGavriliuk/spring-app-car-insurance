package com.example.carinsurance.services;

import com.example.carinsurance.dtos.ModelDTO;
import com.example.carinsurance.exceptions.BrandException;
import com.example.carinsurance.exceptions.ModelException;
import com.example.carinsurance.repositories.BrandRepository;
import com.example.carinsurance.repositories.ModelRepository;
import com.example.carinsurance.models.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;


    public List<Model> listModels(String modelName) {
        if (modelName != null) {
            return modelRepository.findByModel(modelName);
        }
        return modelRepository.findAll();
    }

    public void saveModel(ModelDTO modelDTO) {
        brandRepository.findById(modelDTO.getBrandId()).orElseThrow(() -> new BrandException("Бренд не существует"));
        Model model = mapModelDTOToModel(modelDTO);
        modelRepository.save(model);
    }

    public void deleteModel(int id) {
        modelRepository.deleteById(id);
    }

    public void updateModel(int id, ModelDTO modelDTO) {
        brandRepository.findById(modelDTO.getBrandId()).orElseThrow(() -> new BrandException("Бренд не существует"));
        Model model = mapModelDTOToModel(modelDTO);
        model.setId(model.getId());
        modelRepository.save(model);
    }

    public Model getModelById(int id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new ModelException("Модель с таким id не найдена"));
    }

    public Model mapModelDTOToModel(ModelDTO modelDTO) {
        Model model = new Model();
        model.setModel(modelDTO.getModel());
        model.setBrand(brandRepository.findById(modelDTO.getBrandId()).orElseThrow());
        return model;
    }

    public Page<Model> getModelPagination(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return modelRepository.findAll(pageable);
    }

}