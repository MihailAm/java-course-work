package ru.mihail.spring.ispi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medical_service")
@Getter
@Setter
public class MedicalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Поле 'Название услуги' не может быть пустым")
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    @PositiveOrZero(message = "Цена не может быть отрицательной")
    private Double price;

    @Column(name = "description")
    private String description;
}
