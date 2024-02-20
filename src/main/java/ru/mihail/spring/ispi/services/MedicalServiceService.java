package ru.mihail.spring.ispi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.MedicalService;

import java.util.List;

@Service
public class MedicalServiceService {
    /*
    Create REP
    @Autowired
    private MedicalServiceRepository medicalServiceRepository;
    */
    public List<MedicalService> getAllMedicalServices() {
        //return medicalServiceRepository.findAll();
        return null;  //tmp
    }

    public MedicalService getMedicalServiceById(Long id) {
        //return medicalServiceRepository.findById(id).orElse(null);
        return null;  //tmp
    }

    public MedicalService createMedicalService(MedicalService medicalService) {
        //return medicalServiceRepository.save(medicalService);
        return medicalService;  //tmp
    }

    public MedicalService updateMedicalServiceById(Long id, MedicalService updatedMedicalService) {
        /*
        if (medicalServiceRepository.existsById(id)) {
            updatedMedicalService.setId(id);
            return medicalServiceRepository.save(updatedMedicalService);
        }
        return null;
        */
        return updatedMedicalService;  //tmp
    }

    public boolean deleteMedicalServiceById(Long id) {
        /*
        if (medicalServiceRepository.existsById(id)) {
            medicalServiceRepository.deleteById(id);
            return true;
        }
        return false;
         */
        return false;  //tmp
    }
}
