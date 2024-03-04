package ru.mihail.spring.ispi.Dto;

import lombok.Getter;
import lombok.Setter;
import ru.mihail.spring.ispi.models.Admission;
import java.util.List;

@Getter
@Setter
public class ScheduleDTO {

    private Long id;

    private String date;

    private List<Admission> admissions;

    private Long doctorId;
}
