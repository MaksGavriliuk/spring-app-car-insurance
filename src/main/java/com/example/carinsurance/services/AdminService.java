package com.example.carinsurance.services;

import com.example.carinsurance.dtos.AdminDTO;
import com.example.carinsurance.exceptions.AdminException;
import com.example.carinsurance.exceptions.UserAuthenticationException;
import com.example.carinsurance.models.Admin;
import com.example.carinsurance.models.UserAuthentication;
import com.example.carinsurance.repositories.AdminRepository;
import com.example.carinsurance.repositories.UserAuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserAuthenticationRepository userAuthenticationRepository;


    public List<Admin> listAdmins() {
        return adminRepository.findAll();
    }

    public void saveAdmin(AdminDTO adminDTO) {

        if (userAuthenticationRepository.findByLogin(adminDTO.getUserAuthentication().getLogin()) != null)
            throw new UserAuthenticationException("Пользователь с таким логином уже существует");

        userAuthenticationRepository.save(adminDTO.getUserAuthentication());

        Admin admin = mapAdminDTOToAdmin(adminDTO);
        adminRepository.save(admin);

    }

    public void deleteAdmin(int id) {
        if (!adminRepository.existsById(id))
            throw new AdminException("Администратора с таким id не существует");
        adminRepository.deleteById(id);
    }

    public void updateAdmin(int id, AdminDTO adminDTO) {

        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new AdminException("Администратор с таким id не существует"));

        UserAuthentication userAuthentication = existingAdmin.getUserAuthentication();

        String login = adminDTO.getUserAuthentication().getLogin();
        String password = adminDTO.getUserAuthentication().getPassword();

        if (!userAuthentication.getLogin().equals(login)) {
            if (userAuthenticationRepository.findByLogin(login) != null)
                throw new UserAuthenticationException("Пользователь с таким логином уже существует");
            userAuthentication.setLogin(login);
        }
        if (!userAuthentication.getPassword().equals(password))
            userAuthentication.setPassword(password);

        userAuthenticationRepository.save(userAuthentication);
        existingAdmin.setUserAuthentication(userAuthentication);

        existingAdmin.setSurname(adminDTO.getSurname());
        existingAdmin.setName(adminDTO.getName());
        existingAdmin.setPatronymic(adminDTO.getPatronymic());

        adminRepository.save(existingAdmin);

    }

    public Admin getAdminById(int id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new AdminException("Администратора с таким id не существует"));
    }

    public Admin mapAdminDTOToAdmin(AdminDTO adminDTO) {

        Admin admin = new Admin();
        admin.setUserAuthentication(adminDTO.getUserAuthentication());
        admin.setSurname(adminDTO.getSurname());
        admin.setName(adminDTO.getName());
        admin.setPatronymic(adminDTO.getPatronymic());

        return admin;

    }

}
