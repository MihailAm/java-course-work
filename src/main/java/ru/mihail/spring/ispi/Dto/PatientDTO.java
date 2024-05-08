package ru.mihail.spring.ispi.Dto;

import lombok.Getter;
import lombok.Setter;
import ru.mihail.spring.ispi.models.Users;

@Getter
@Setter
public class PatientDTO {
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;
    private String snils;
}
