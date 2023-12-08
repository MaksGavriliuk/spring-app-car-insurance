package com.example.carinsurance.services;

import com.example.carinsurance.calculate.PremiumCalculation;
import com.example.carinsurance.dtos.InsuranceAgentDTO;
import com.example.carinsurance.exceptions.InsuranceAgentException;
import com.example.carinsurance.exceptions.UserAuthenticationException;
import com.example.carinsurance.models.Contract;
import com.example.carinsurance.models.InsuranceAgent;
import com.example.carinsurance.models.UserAuthentication;
import com.example.carinsurance.repositories.InsuranceAgentRepository;
import com.example.carinsurance.repositories.UserAuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class InsuranceAgentService {

    private final InsuranceAgentRepository insuranceAgentRepository;
    private final UserAuthenticationRepository userAuthenticationRepository;


    public List<InsuranceAgent> listInsuranceAgents() {
        return insuranceAgentRepository.findAll();
    }

    public void saveInsuranceAgent(InsuranceAgentDTO insuranceAgentDTO) {

        if (userAuthenticationRepository.findByLogin(insuranceAgentDTO.getUserAuthentication().getLogin()) != null)
            throw new UserAuthenticationException("Пользователь с таким логином уже существует");

        UserAuthentication userAuthentication = new UserAuthentication(
                insuranceAgentDTO.getUserAuthentication().getLogin(),
                new BCryptPasswordEncoder().encode(insuranceAgentDTO.getUserAuthentication().getLogin())
        );

        InsuranceAgent insuranceAgent = mapInsuranceAgentDTOToInsuranceAgent(insuranceAgentDTO);
        insuranceAgent.setUserAuthentication(userAuthentication);

        userAuthenticationRepository.save(userAuthentication);
        insuranceAgentRepository.save(insuranceAgent);

    }

    public void deleteInsuranceAgent(int id) {
        InsuranceAgent insuranceAgent = insuranceAgentRepository.findById(id)
                .orElseThrow(() -> new InsuranceAgentException("Агента с таким id не существует"));
        userAuthenticationRepository.deleteById(insuranceAgent.getUserAuthentication().getId());
        insuranceAgentRepository.deleteById(id);
    }

    public InsuranceAgent getInsuranceAgentById(int id) {
        return insuranceAgentRepository.findById(id)
                .orElseThrow(() -> new UserAuthenticationException("Пользователя с таким логином не существует"));
    }

    public InsuranceAgent mapInsuranceAgentDTOToInsuranceAgent(InsuranceAgentDTO insuranceAgentDTO) {

        InsuranceAgent insuranceAgent = new InsuranceAgent();
        insuranceAgent.setSurname(insuranceAgentDTO.getSurname());
        insuranceAgent.setName(insuranceAgentDTO.getName());
        insuranceAgent.setPatronymic(insuranceAgentDTO.getPatronymic());
        insuranceAgent.setSex(insuranceAgentDTO.getSex());
        insuranceAgent.setAge(insuranceAgentDTO.getAge());
        insuranceAgent.setProfit(insuranceAgentDTO.getProfit());
        return insuranceAgent;

    }

    public BigDecimal calculatePremium(PremiumCalculation premiumCalculation) {
        InsuranceAgent insuranceAgent = insuranceAgentRepository.findById(premiumCalculation.getInsuranceAgentId())
                .orElseThrow(() -> new InsuranceAgentException("Агента с таким id нет"));
        BigDecimal premium = premiumCalculation.getAmount().multiply(premiumCalculation.getPercent().divide(new BigDecimal("100")));
        insuranceAgent.setProfit(insuranceAgent.getProfit().add(premium));
        insuranceAgentRepository.save(insuranceAgent);
        return premium;
    }

}
