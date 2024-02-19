package ru.mihail.spring.ispi.services;

import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.Specialty;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialtyService {
    // Заглушка для создания новой специализации
    public Specialty createSpecialty(Specialty specialty) {
        // Логика создания специализации

        return new Specialty();
    }

    // Заглушка для получения специализации по её идентификатору (id)
    public Specialty getSpecialtyById(Long id) {
        // Логика получения специализации по id
        return new Specialty();
    }

    // Заглушка для получения списка всех специализаций
    public List<Specialty> getAllSpecialties() {
        // Логика получения всех специализаций
        return new ArrayList<>();
    }

    // Заглушка для обновления специализации по id
    public Specialty updateSpecialty(Long id, Specialty specialtyDetails) {
        // Логика обновления специализации
        return new Specialty();
    }

    // Заглушка для удаления специализации по id
    public void deleteSpecialty(Long id) {
        // Реальная логика удаления специализации
    }
}
