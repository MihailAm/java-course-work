package ru.mihail.spring.ispi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "medical_service")
public class MedicalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Поле 'Название услуги' не может быть пустым")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Поле 'Описание' не может быть пустым")
    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    @PositiveOrZero(message = "Цена не может быть отрицательной")
    private Double price;

    // Дополнительные поля, если необходимо

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

