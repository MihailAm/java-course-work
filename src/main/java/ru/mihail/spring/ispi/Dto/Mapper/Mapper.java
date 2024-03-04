package ru.mihail.spring.ispi.Dto.Mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.Dto.DoctorAuthDTO;
import ru.mihail.spring.ispi.Dto.DoctorDTO;
import ru.mihail.spring.ispi.Dto.ScheduleDTO;
import ru.mihail.spring.ispi.Dto.SpecialtyDTO;
import ru.mihail.spring.ispi.models.*;
import ru.mihail.spring.ispi.services.SpecialtyService;

import java.util.ArrayList;
import java.util.List;

@Service
public class Mapper {

    // МАППЕР ДЛЯ ДОКТОРА ПРИ ВХОДЕ
    @Autowired
    private SpecialtyService specialtyService;

    public Users toUsersEntity(DoctorAuthDTO doctorRequest) {
        Users user = new Users();
        user.setEmail(doctorRequest.getEmail());
        user.setPassword(doctorRequest.getPassword());
        return user;
    }

    public Doctor toDoctorEntity(DoctorAuthDTO doctorRequest) {
        Doctor doctor = new Doctor();
        doctor.setUser(toUsersEntity(doctorRequest));
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setPosition(doctorRequest.getPosition());
        Specialty specialty = specialtyService.findById(doctorRequest.getSpecialtyId());
        doctor.setSpecialty(specialty);
        return doctor;
    }
}
