package ru.mihail.spring.ispi.Dto;

import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.models.Patient;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AdmissionDTO {

    private Long id;

    private Doctor doctor;

    private Patient patient;

    private Date date;

    private Date time;

    private Long serviceId;
}
