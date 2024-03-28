package ru.mihail.spring.ispi.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.Dto.ScheduleDTO;
import ru.mihail.spring.ispi.models.Schedule;
import ru.mihail.spring.ispi.models.Specialty;
import ru.mihail.spring.ispi.repositories.ScheduleRepository;
import ru.mihail.spring.ispi.services.Interfaces.ScheduleServiceInterface;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class ScheduleService implements ScheduleServiceInterface {

    @Autowired
    private ScheduleRepository scheduleRepository;


    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    public Schedule findScheduleById(Long id) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
        return scheduleOptional.orElse(null);
    }

    public Schedule updateSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule getScheduleByDoctorIdAndDate(Long doctorId, LocalDate date) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findByDoctorIdAndScheduleDate(doctorId, date);
        return scheduleOptional.orElse(null);
    }
}
