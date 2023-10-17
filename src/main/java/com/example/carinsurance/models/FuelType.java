package com.example.carinsurance.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@Entity
@Table(name = "fuel_types")
@AllArgsConstructor
@RequiredArgsConstructor
public class FuelType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fuel_type")
    private String fuelType;

    @JsonIgnore
    @OneToMany(mappedBy = "fuelType")
    private List<Car> cars;

}
