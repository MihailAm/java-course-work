package ru.mihail.spring.ispi.services.Interfaces;

import ru.mihail.spring.ispi.models.Schedule;

import java.time.LocalDate;

public interface ScheduleServiceInterface {

    Schedule addSchedule(Schedule schedule);
    void deleteSchedule(Long id);
    Schedule findScheduleById(Long id);
    Schedule updateSchedule(Schedule schedule);
    Schedule getScheduleByDoctorIdAndDate(Long doctorId, LocalDate date);
}
