package ru.mihail.spring.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mihail.spring.ispi.models.Schedule;
import ru.mihail.spring.ispi.models.Specialty;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByScheduleDate(LocalDate date);

    Optional<Schedule> findByDoctorIdAndScheduleDate(Long doctorId, LocalDate date);
}
