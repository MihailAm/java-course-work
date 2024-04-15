package ru.mihail.spring.ispi.services.Impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.Administrator;
import ru.mihail.spring.ispi.models.Admission;
import ru.mihail.spring.ispi.repositories.AdministratorRepository;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Transactional
    public void save(Administrator admin) {
        administratorRepository.save(admin);
    }

}
