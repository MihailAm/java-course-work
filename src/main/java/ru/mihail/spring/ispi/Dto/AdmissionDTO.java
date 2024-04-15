package ru.mihail.spring.ispi.Dto;

import java.time.LocalTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AdmissionDTO {

    private Long id;

    private Long doctorId;

    private Long patientId;

    private Date date;

    private LocalTime time;

    private Long serviceId;
}
