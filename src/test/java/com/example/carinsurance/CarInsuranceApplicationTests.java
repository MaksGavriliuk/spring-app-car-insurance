package com.example.carinsurance;

import com.example.carinsurance.calculate.DaysBetweenDates;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CarInsuranceApplicationTests {

    @Test
    void contextLoads() {
        assertEquals(DaysBetweenDates.daysBetweenDates(
                "2022-12-01",
                "2023-12-21"
        ), 385);
    }

}
