package ru.mihail.spring.ispi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.Admission;

import java.util.Date;
import java.util.List;

@Service
public class AdmissionService {

    /*
    Create REP
    @Autowired
    private AdmissionRepository admissionRepository;
    */

    public List<Admission> getAllAdmissions() {
        //return admissionRepository.findAll();
        return null;  //tmp
    }

    public Admission createAdmission(Admission admission) {
        // Логика проверок перед сохранением, если необходимо
        //return admissionRepository.save(admission);
        return admission;  //tmp
    }

    public Admission getAdmissionById(Long id) {
        //return admissionRepository.findById(id).orElse(null);
        return null;  //tmp
    }

    public boolean deleteAdmissionById(Long id) {
        /*
        if (admissionRepository.existsById(id)) {
            admissionRepository.deleteById(id);
            return true;
        }
        return false;
        */
        return false;  //tmp
    }

    public List<Admission> getAdmissionsByDate(Date date) {
        // Логика поиска приемов по дате
        //return admissionRepository.findByAdmissionDatetimeContaining(date);
        return null;  //tmp
    }
}
