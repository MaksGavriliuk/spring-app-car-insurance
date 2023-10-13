package com.example.carinsurance.services;

import com.example.carinsurance.models.Admin;
import com.example.carinsurance.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;


    public List<Admin> listAdmins() {
        return adminRepository.findAll();
    }

    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }

    public Admin getAdminById(int id) {
        return adminRepository.findById(id).orElse(null);
    }

}
