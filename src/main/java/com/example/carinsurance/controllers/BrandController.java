package com.example.carinsurance.controllers;

import com.example.carinsurance.models.Brand;
import com.example.carinsurance.services.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/brands")
    public String brands(@RequestParam(name = "brand", required = false) String brand,
                         Map<String, Object> model) {
        model.put("brands", brandService.listBrands(brand));
        return "brands";
    }

    @GetMapping("/brands/{id}")
    public String brandInfo(@PathVariable Long id, Map<String, Object> model) {
        model.put("brand", brandService.getBrandById(id));
        return "brand-info";
    }

//    @PostMapping("/brands/create")
//    public String createBrand(@RequestParam("brand") String brandName) {
//        brandService.saveBrand(new Brand(brandName));
//        return "redirect:/brands";
//    }

    @PostMapping("/brands/create")
    public String createBrand(Brand brand) {
        brandService.saveBrand(brand);
        return "redirect:/brands";
    }

    @PostMapping("/brands/delete/{id}")
    public String deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return "redirect:/brands";
    }

}
