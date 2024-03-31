package ru.mihail.spring.ispi.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalServiceDTO {
    private Long id;

    private String name;

    private Double price;

    private String description;
}
