package ru.mihail.spring.ispi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.Dto.Mapper.Mapper;
import ru.mihail.spring.ispi.Dto.ScheduleDTO;
import ru.mihail.spring.ispi.models.Schedule;
import jakarta.validation.Valid;
import ru.mihail.spring.ispi.services.Impl.AdmissionService;
import ru.mihail.spring.ispi.services.Impl.ScheduleService;
import ru.mihail.spring.ispi.models.Admission;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private Mapper Mapper;

    // метод который достает все приемы по доктору и дате, формирует расписание
    @GetMapping("/generate/doctor/{doctorId}")
    public List<Admission> getAdmissionsByDoctorIdAndDate(@PathVariable Long doctorId, @RequestParam Date date) {
        return admissionService.getAdmissionsByDoctorIdAndDate(doctorId, date);
    }

    // создает расписание врачей для пациентов
    @PostMapping("/create")
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = Mapper.convertToScheduleEntity(scheduleDTO);
        Schedule createdSchedule = scheduleService.addSchedule(schedule);
        return Mapper.convertToScheduleDTO(createdSchedule);
    }

    // расписание по id
    @GetMapping("/{id}")
    public ScheduleDTO getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.findScheduleById(id);
        return Mapper.convertToScheduleDTO(schedule);
    }

    // берет расписание по врачу и дате, так можно посмотреть как работает врач в определенный день
    @GetMapping("/doctor/{doctorId}")
    public ScheduleDTO getSchedulesByDoctorIdAndDate(@PathVariable Long doctorId, @RequestParam LocalDate date) {
        Schedule schedule = scheduleService.getScheduleByDoctorIdAndDate(doctorId, date);
        return Mapper.convertToScheduleDTO(schedule);
    }

    // изменить
    @PutMapping("/update")
    public ScheduleDTO updateSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = Mapper.convertToScheduleEntity(scheduleDTO);
        Schedule updatedSchedule = scheduleService.updateSchedule(schedule);
        return Mapper.convertToScheduleDTO(updatedSchedule);
    }

    // удалить
    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
    }

}
