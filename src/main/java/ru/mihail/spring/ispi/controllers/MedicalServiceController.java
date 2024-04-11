package ru.mihail.spring.ispi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.Dto.MedicalServiceDTO;
import ru.mihail.spring.ispi.models.MedicalService;
import ru.mihail.spring.ispi.services.Impl.MedicalServiceService;
import ru.mihail.spring.ispi.Dto.Mapper.Mapper;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/medical-service")
public class MedicalServiceController {

    @Autowired
    private MedicalServiceService medicalServiceService;

    @Autowired
    private Mapper mapper;

    // Создание новой медицинской услуги
    @PostMapping("/create")
    public ResponseEntity<MedicalServiceDTO> createMedicalService(@Valid @RequestBody MedicalServiceDTO medicalServiceDTO) {
        MedicalService medicalService = mapper.convertToMedicalServiceEntity(medicalServiceDTO);
        MedicalService createdMedicalService = medicalServiceService.createMedicalService(medicalService);
        if (createdMedicalService != null) {
            MedicalServiceDTO createdMedicalServiceDTO = mapper.convertToMedicalServiceDTO(createdMedicalService);
            return new ResponseEntity<>(createdMedicalServiceDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // Получение медицинской услуги по ее идентификатору (id)
    @GetMapping("/search/{id}")
    public ResponseEntity<MedicalServiceDTO> getMedicalServiceById(@PathVariable Long id) {
        MedicalService medicalService = medicalServiceService.getMedicalServiceById(id);
        if (medicalService != null) {
            MedicalServiceDTO medicalServiceDTO = mapper.convertToMedicalServiceDTO(medicalService);
            return new ResponseEntity<>(medicalServiceDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Получение списка всех медицинских услуг
    @GetMapping("/all")
    public ResponseEntity<List<MedicalServiceDTO>> getAllMedicalServices() {
        List<MedicalService> medicalServices = medicalServiceService.getAllMedicalServices();
        List<MedicalServiceDTO> medicalServiceDTOs = mapper.convertToMedicalServiceDTOList(medicalServices);
        return new ResponseEntity<>(medicalServiceDTOs, HttpStatus.OK);
    }

    // Обновление медицинской услуги по id
    @PutMapping("/update/{id}")
    public ResponseEntity<MedicalServiceDTO> updateMedicalService(@PathVariable Long id, @Valid @RequestBody MedicalServiceDTO medicalServiceDTO) {
        medicalServiceDTO.setId(id);
        MedicalService medicalService = mapper.convertToMedicalServiceEntity(medicalServiceDTO);
        MedicalService updatedMedicalService = medicalServiceService.updateMedicalService(medicalService);
        if (updatedMedicalService != null) {
            MedicalServiceDTO updatedMedicalServiceDTO = mapper.convertToMedicalServiceDTO(updatedMedicalService);
            return new ResponseEntity<>(updatedMedicalServiceDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удаление медицинской услуги по id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMedicalService(@PathVariable Long id) {
        medicalServiceService.deleteMedicalService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<MedicalService>> searchMedicalServiceByName(@RequestParam("name") String name) {
        List<MedicalService> medicalServices = medicalServiceService.findMedicalServiceByName(name);
        return new ResponseEntity<>(medicalServices, HttpStatus.OK);
    }
}
