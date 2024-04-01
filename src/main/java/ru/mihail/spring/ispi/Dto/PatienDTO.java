package ru.mihail.spring.ispi.Dto;

import lombok.Getter;
import lombok.Setter;
import ru.mihail.spring.ispi.models.Users;

@Getter
@Setter
public class PatienDTO {
    private Long id;
    private Users user;
    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;
    private String snils;
}
