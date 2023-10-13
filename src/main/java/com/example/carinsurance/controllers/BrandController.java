package com.example.carinsurance.controllers;

import com.example.carinsurance.models.Brand;
import com.example.carinsurance.services.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/brands")
@Slf4j
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<Brand> getBrands(@RequestParam(name = "brand", required = false) String brand) {
        return brandService.listBrands(brand);
    }

    @GetMapping("/{id}")
    public Brand getBrandInfo(@PathVariable Integer id) {
        return brandService.getBrandById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createBrand(@RequestBody Brand brand) {
        brandService.saveBrand(brand);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok().build();
    }

}
