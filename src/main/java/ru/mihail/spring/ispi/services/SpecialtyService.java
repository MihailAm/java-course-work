package ru.mihail.spring.ispi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.Specialty;
import ru.mihail.spring.ispi.repositories.SpecialtyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyService {


    @Autowired
    private SpecialtyRepository specialtyRepository;

    public void addSpecialty(Specialty specialty) {
        specialtyRepository.save(specialty);
    }
    public Specialty findById(Long id) {
        Optional<Specialty> specialtyOptional = specialtyRepository.findById(id);
        return specialtyOptional.orElse(null);
    }

    // Заглушка для создания новой специализации
    public Specialty createSpecialty(Specialty specialty) {
        // Логика создания специализации

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
