package ru.mihail.spring.ispi.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalReportDTO {
    private Long id;

    private Long patientId;

    private Long doctorId;

    private Long admissionId;

    private String report;
}
