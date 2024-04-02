package ru.mihail.spring.ispi.Dto;


import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import ru.mihail.spring.ispi.models.Admission;
import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.models.Patient;

@Getter
@Setter
public class MedicalReportDTO {
    private Long id;

    private Patient patient;

    private Doctor doctor;

    private Admission admission;

    private String report;
}
