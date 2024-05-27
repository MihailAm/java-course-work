package ru.mihail.spring.ispi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.Dto.MedicalReportDTO;
import ru.mihail.spring.ispi.models.MedicalReport;
import ru.mihail.spring.ispi.models.Patient;
import ru.mihail.spring.ispi.services.Impl.MedicalReportService;
import ru.mihail.spring.ispi.Dto.Mapper.Mapper;

import jakarta.validation.Valid;
import ru.mihail.spring.ispi.services.Impl.PatientService;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/medical-reports")
public class MedicalReportController {

    @Autowired
    private MedicalReportService medicalReportService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private Mapper mapper;

    // Создание нового медицинского отчета
    @PostMapping("/create")
    public ResponseEntity<MedicalReportDTO> createMedicalReport(@Valid @RequestBody MedicalReportDTO medicalReportDTO) {
        MedicalReport medicalReport = mapper.convertToMedicalReportEntity(medicalReportDTO);

        MedicalReport createdMedicalReport = medicalReportService.createMedicalReport(medicalReport);

        if (createdMedicalReport != null) {
            MedicalReportDTO createdMedicalReportDTO = mapper.convertToMedicalReportDTO(createdMedicalReport);
            return new ResponseEntity<>(createdMedicalReportDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Получение медицинского отчета по его идентификатору (id)
    @GetMapping("get/{id}")
    public ResponseEntity<MedicalReportDTO> getMedicalReportById(@PathVariable Long id) {
        MedicalReport medicalReport = medicalReportService.getMedicalReportById(id);
        if (medicalReport != null) {
            MedicalReportDTO medicalReportDTO = mapper.convertToMedicalReportDTO(medicalReport);
            return new ResponseEntity<>(medicalReportDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Получение списка всех медицинских отчетов
    @GetMapping("/all")
    public ResponseEntity<List<MedicalReportDTO>> getAllMedicalReports() {
        List<MedicalReport> medicalReports = medicalReportService.getAllMedicalReports();
        List<MedicalReportDTO> medicalReportDTOs = mapper.convertToMedicalReportDTOList(medicalReports);
        return new ResponseEntity<>(medicalReportDTOs, HttpStatus.OK);
    }

    // Обновление медицинского отчета по его идентификатору (id)
    @PutMapping("update/{id}")
    public ResponseEntity<MedicalReportDTO> updateMedicalReport(@PathVariable Long id, @Valid @RequestBody MedicalReportDTO medicalReportDTO) {
        medicalReportDTO.setId(id);
        MedicalReport medicalReport = mapper.convertToMedicalReportEntity(medicalReportDTO);
        MedicalReport updatedMedicalReport = medicalReportService.updateMedicalReport(medicalReport);
        if (updatedMedicalReport != null) {
            MedicalReportDTO updatedMedicalReportDTO = mapper.convertToMedicalReportDTO(updatedMedicalReport);
            return new ResponseEntity<>(updatedMedicalReportDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удаление медицинского отчета по его идентификатору (id)
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteMedicalReport(@PathVariable Long id) {
        medicalReportService.deleteMedicalReport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Поиск медицинских отчетов по пациенту
    @GetMapping("/searchByPatient/{patientId}")
    public ResponseEntity<List<MedicalReportDTO>> searchMedicalReportByPatient(@PathVariable("patientId") Long patientId) {
        Patient patient = patientService.getPatientById(patientId); // Получаем пациента по его ID
        if (patient != null) {
            List<MedicalReport> medicalReports = medicalReportService.findMedicalReportByPatient(patient);
            List<MedicalReportDTO> medicalReportDTOs = mapper.convertToMedicalReportDTOList(medicalReports);
            return new ResponseEntity<>(medicalReportDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
