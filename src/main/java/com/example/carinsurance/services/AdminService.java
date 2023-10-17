package com.example.carinsurance.services;

import com.example.carinsurance.dtos.AdminDTO;
import com.example.carinsurance.exceptions.AdminException;
import com.example.carinsurance.exceptions.UserAuthenticationException;
import com.example.carinsurance.models.Admin;
import com.example.carinsurance.models.UserAuthentication;
import com.example.carinsurance.repositories.AdminRepository;
import com.example.carinsurance.repositories.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;


    public List<Admin> listAdmins() {
        return adminRepository.findAll();
    }

    public void saveAdmin(AdminDTO adminDTO) {
        if (userAuthenticationRepository.findByLogin(adminDTO.getLogin()) != null)
            throw new UserAuthenticationException("Пользователь с таким логином уже существует");
        Admin admin = mapAdminDTOToAdmin(adminDTO);
        adminRepository.save(admin);
    }

    public void deleteAdmin(int id) {
        if (!adminRepository.existsById(id))
            throw new AdminException("Администратора с таким id не существует");
        adminRepository.deleteById(id);
    }

    public Admin getAdminById(int id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new AdminException("Администратора с таким id не существует"));
    }

    public Admin mapAdminDTOToAdmin(AdminDTO adminDTO) {

        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setLogin(adminDTO.getLogin());
        userAuthentication.setPassword(new BCryptPasswordEncoder().encode(adminDTO.getPassword()));
        userAuthenticationRepository.save(userAuthentication);

        Admin admin = new Admin();
        admin.setUserAuthentication(userAuthentication);
        admin.setSurname(adminDTO.getSurname());
        admin.setName(adminDTO.getName());
        admin.setPatronymic(adminDTO.getPatronymic());

        return admin;

    }

}
