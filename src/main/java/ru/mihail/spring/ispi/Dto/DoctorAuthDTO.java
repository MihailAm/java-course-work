package ru.mihail.spring.ispi.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorAuthDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Long specialtyId;
    private String position;
    private String office;
}
