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
            brandRepository.findByBrand(brand);
        return brandRepository.findAll();
    }

    public void saveBrand(Brand brand) {
        log.info("Saving new {}", brand);
        brandRepository.save(brand);
    }

    public void deleteBrand(long id) {
        brandRepository.deleteById(id);
    }

    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

}
