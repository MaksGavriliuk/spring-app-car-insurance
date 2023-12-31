package com.example.carinsurance.controllers;

import com.example.carinsurance.dtos.AdminDTO;
import com.example.carinsurance.models.Admin;
import com.example.carinsurance.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @GetMapping
    public List<Admin> getAdmins() {
        return adminService.listAdmins();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createAdmin(@RequestBody AdminDTO adminDTO) {
        adminService.saveAdmin(adminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdmin(@PathVariable Integer id, @RequestBody AdminDTO adminDTO) {
        adminService.updateAdmin(id, adminDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Integer id) {
        return adminService.getAdminById(id);
    }

}
