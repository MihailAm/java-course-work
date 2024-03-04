package ru.mihail.spring.ispi.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientAuthDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;
    private String snils;
}