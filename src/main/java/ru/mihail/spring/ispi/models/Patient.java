package ru.mihail.spring.ispi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patient")
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private Users user;

    @NotEmpty(message="Поле 'Имя' не может быть пустым")
    @Size(min=2, max=30, message = "Имя должно содержать от 2 до 30 символов")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message="Поле 'Фамилия' не может быть пустым")
    @Size(min=2, max=30, message = "Имя должно содержать от 2 до 30 символов")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    @Min(value = 0, message = "Возраст не может быть меньше 0")
    @Max(value = 150, message = "Возраст не может быть больше 150")
    private Integer age;

    @Column(name = "phone_number")
    @Pattern(regexp="^\\+?[0-9]{3}-?\\(?[0-9]{2}\\)?-?[0-9]{3}-?[0-9]{2}-?[0-9]{2}$", message="Неправильный формат номера телефона")
    private String phoneNumber;

    @Column(name = "snils")
    @Pattern(regexp="[0-9]{3}-?[0-9]{3}-?[0-9]{3}\\s[0-9]{2}", message="Неправильный формат СНИЛС")
    private String snils;
    
}

