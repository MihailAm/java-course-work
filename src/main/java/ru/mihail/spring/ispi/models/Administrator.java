package ru.mihail.spring.ispi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "administrator")
public class Administrator {

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


}
