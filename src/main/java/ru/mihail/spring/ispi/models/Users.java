package ru.mihail.spring.ispi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email(message = "Некорректный формат email")
    @NotEmpty(message = "Email не может быть пустым")
    @Pattern(regexp = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$", message = "Некорректный формат email")
    @Column(unique = true, name = "email")
    private String email;

    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 4, message = "Пароль должен содержать не менее 6 символов")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "Роль не может быть пустой")
    @Pattern(regexp = "ROLE_[A-Z]+", message = "Неправильный формат роли")
    @Column(name = "role")
    private String role;

    public Users() {}

    public Users(long id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}

