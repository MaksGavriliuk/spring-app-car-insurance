package com.example.carinsurance.services;

import com.example.carinsurance.exceptions.BrandException;
import com.example.carinsurance.models.Brand;
import com.example.carinsurance.models.Model;
import com.example.carinsurance.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;


    public List<Brand> listBrands(String brand) {
        if (brand != null)
            return brandRepository.findByBrand(brand);
        return brandRepository.findAll();
    }

    public void saveBrand(Brand brand) {
        brandRepository.save(brand);
    }

    public void deleteBrand(int id) {
        brandRepository.deleteById(id);
    }

    public void updateBrand(int id, Brand updateBrand) {
        updateBrand.setId(id);
        brandRepository.save(updateBrand);
    }

    public Brand getBrandById(int id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new BrandException("Бренд с таким id не найден"));
    }

    public List<Model> getModelsByBrand(String brandName) {
        List<Brand> brands = brandRepository.findByBrand(brandName);
        return (brands.isEmpty()) ? Collections.emptyList() : brands.get(0).getModels();
    }

}
