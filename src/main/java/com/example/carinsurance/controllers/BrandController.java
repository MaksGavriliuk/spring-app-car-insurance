package com.example.carinsurance.controllers;

import com.example.carinsurance.models.Brand;
import com.example.carinsurance.models.Model;
import com.example.carinsurance.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<Brand> getBrands(@RequestParam(name = "brand", required = false) String brand) {
        return brandService.listBrands(brand);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createBrand(@RequestBody Brand brand) {
        brandService.saveBrand(brand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBrand(@PathVariable Integer id, @RequestBody Brand updateBrand) {
        brandService.updateBrand(id, updateBrand);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Brand getBrandById(@PathVariable Integer id) {
        return brandService.getBrandById(id);
    }

    @GetMapping("/{id}/models")
    public List<Model> getModelsByBrand(@PathVariable Integer id) {
        return brandService.getModelsByBrand(brandService.getBrandById(id).getBrand());
    }

}
