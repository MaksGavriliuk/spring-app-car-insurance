package com.example.carinsurance.controllers;

import com.example.carinsurance.models.Admin;
import com.example.carinsurance.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping
    public List<Admin> getAdmins() {
        return adminService.listAdmins();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Integer id) {
        return adminService.getAdminById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

}
