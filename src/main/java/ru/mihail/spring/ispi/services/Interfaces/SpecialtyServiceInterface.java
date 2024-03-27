package ru.mihail.spring.ispi.services.Interfaces;

import ru.mihail.spring.ispi.models.Specialty;

import java.util.List;

public interface SpecialtyServiceInterface {
    Specialty findById(Long id);

    Specialty createSpecialty(Specialty specialty);

    List<Specialty> getAllSpecialties();

    void deleteSpecialty(Long id);
}
