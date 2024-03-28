package ru.mihail.spring.ispi.Dto;

import lombok.Getter;
import lombok.Setter;
import ru.mihail.spring.ispi.models.Admission;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ScheduleDTO {

    private Long id;

    private Long doctorId;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDate scheduleDate;

    private String additionalInfo;
}
