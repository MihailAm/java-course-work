package ru.mihail.spring.ispi.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.Specialty;
import ru.mihail.spring.ispi.repositories.SpecialtyRepository;
import ru.mihail.spring.ispi.services.Interfaces.SpecialtyServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyService implements SpecialtyServiceInterface {


    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public Specialty findById(Long id) {
        Optional<Specialty> specialtyOptional = specialtyRepository.findById(id);
        return specialtyOptional.orElse(null);
    }

    @Override
    public Specialty createSpecialty(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }


    @Override
    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }

    @Override
    public void deleteSpecialty(Long id) {
        specialtyRepository.deleteById(id);
    }
}
