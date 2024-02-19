package ru.mihail.spring.ispi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.models.Schedule;
import jakarta.validation.Valid;
import ru.mihail.spring.ispi.services.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // Получение расписания по его идентификатору (id)
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        if (schedule != null) {
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Добавление расписания
    @PostMapping("/add")
    public ResponseEntity<Schedule> addSchedule(@Valid @RequestBody Schedule schedule) {
        Schedule addedSchedule = scheduleService.addSchedule(schedule);
        return new ResponseEntity<>(addedSchedule, HttpStatus.CREATED);
    }

    // Удаление расписания по его идентификатору (id)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteScheduleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Обновление расписания
    @PutMapping("/update/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @Valid @RequestBody Schedule scheduleDetails) {
        Schedule updatedSchedule = scheduleService.updateSchedule(id, scheduleDetails);
        if (updatedSchedule != null) {
            return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Добавление списка приемов к расписанию по дате
    @PostMapping("/addAdmissions/{date}")
    public ResponseEntity<Schedule> addAdmissionsToSchedule(@PathVariable String date, @RequestBody List<Admission> admissions) {
        Schedule schedule = scheduleService.addAdmissionsToSchedule(date, admissions);
        if (schedule != null) {
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
