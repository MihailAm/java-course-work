package ru.mihail.spring.ispi.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Long userId;

    private Long specialtyId;

    private String position;

    private String office;

    private Long work_experience;
}


