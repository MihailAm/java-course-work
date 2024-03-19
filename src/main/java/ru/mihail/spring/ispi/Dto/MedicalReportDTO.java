package ru.mihail.spring.ispi.Dto;


import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalReportDTO {
    private Long id;

    private Long patientId;

    private Long doctorId;

    private Date admission;

    private String report;
}
