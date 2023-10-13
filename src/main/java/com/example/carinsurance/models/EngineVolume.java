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

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "engine_volumes")
@AllArgsConstructor
@RequiredArgsConstructor
public class EngineVolume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "engine_volume", precision = 5, scale = 2)
    private BigDecimal engineVolume;

    @JsonIgnore
    @OneToMany(mappedBy = "engineVolume")
    private List<Car> cars;

}
