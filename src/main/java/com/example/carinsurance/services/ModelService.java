//package com.example.carinsurance.services;
//
//import com.example.carinsurance.repositories.BrandRepository;
//import com.example.carinsurance.repositories.ModelRepository;
//import com.example.carinsurance.models.Model;
//import com.example.carinsurance.models.Brand;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor
//public class ModelService {
//
//    private ModelRepository modelRepository;
//    private BrandRepository brandRepository;
//
//
//    public Model createModel(String modelName, String brandName) {
//        Brand brand = (Brand) brandRepository.findByBrand(brandName);
//        if (brand != null) {
//            // Обработка отсутствия бренда
//        }
//
//        Model model = new Model();
//        model.setModel(modelName);
//        model.setBrand(brand);
//        return null;
////        return modelRepository.save(model);
//    }
//}