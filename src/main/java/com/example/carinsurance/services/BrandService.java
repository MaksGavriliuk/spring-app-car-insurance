package com.example.carinsurance.services;

import com.example.carinsurance.models.Brand;
import com.example.carinsurance.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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

    public Brand getBrandById(int id) {
        return brandRepository.findById(id).orElse(null);
    }

}
