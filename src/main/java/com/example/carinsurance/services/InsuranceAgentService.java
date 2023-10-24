package com.example.carinsurance.services;

import com.example.carinsurance.dtos.InsuranceAgentDTO;
import com.example.carinsurance.exceptions.UserAuthenticationException;
import com.example.carinsurance.models.InsuranceAgent;
import com.example.carinsurance.models.UserAuthentication;
import com.example.carinsurance.repositories.InsuranceAgentRepository;
import com.example.carinsurance.repositories.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InsuranceAgentService {

    @Autowired
    private InsuranceAgentRepository insuranceAgentRepository;
    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;


    public List<InsuranceAgent> listInsuranceAgents() {
        return insuranceAgentRepository.findAll();
    }

    public void saveInsuranceAgent(InsuranceAgentDTO insuranceAgentDTO) {

        if (userAuthenticationRepository.findByLogin(insuranceAgentDTO.getUserAuthentication().getLogin()) != null)
            throw new UserAuthenticationException("Пользователь с таким логином уже существует");

        userAuthenticationRepository.save(insuranceAgentDTO.getUserAuthentication());

        InsuranceAgent insuranceAgent = mapInsuranceAgentDTOToInsuranceAgent(insuranceAgentDTO);
        insuranceAgentRepository.save(insuranceAgent);

    }

    public void deleteInsuranceAgent(int id) {
        insuranceAgentRepository.deleteById(id);
    }

    public InsuranceAgent getInsuranceAgentById(int id) {
        return insuranceAgentRepository.findById(id)
                .orElseThrow(() -> new UserAuthenticationException("Пользователя с таким логином не существует"));
    }

    public InsuranceAgent mapInsuranceAgentDTOToInsuranceAgent(InsuranceAgentDTO insuranceAgentDTO) {

        InsuranceAgent insuranceAgent = new InsuranceAgent();
        insuranceAgent.setUserAuthentication(insuranceAgentDTO.getUserAuthentication());
        insuranceAgent.setSurname(insuranceAgentDTO.getSurname());
        insuranceAgent.setName(insuranceAgentDTO.getName());
        insuranceAgent.setPatronymic(insuranceAgentDTO.getPatronymic());
        insuranceAgent.setSex(insuranceAgentDTO.getSex());
        insuranceAgent.setAge(insuranceAgentDTO.getAge());
        insuranceAgent.setProfit(insuranceAgentDTO.getProfit());
        return insuranceAgent;

    }

}
