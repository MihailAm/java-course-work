package ru.mihail.spring.ispi.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.Dto.SpecialtyDTO;
import ru.mihail.spring.ispi.models.Specialty;
import ru.mihail.spring.ispi.services.Impl.SpecialtyService;
import ru.mihail.spring.ispi.Dto.Mapper.Mapper;


import java.util.List;

@RestController
@RequestMapping("api/specialty")
public class SpecialtyController {
    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private Mapper Mapper;

    // Добавить +

    @PostMapping("/new-spec")
    public ResponseEntity<SpecialtyDTO> createSpecialty(@Valid @RequestBody SpecialtyDTO specialtyDTO) {
        Specialty specialty = Mapper.SpecialtyConvertToEntity(specialtyDTO);
        Specialty createdSpecialty = specialtyService.createSpecialty(specialty);
        return new ResponseEntity<>(Mapper.SpecialtyConvertToDto(createdSpecialty), HttpStatus.CREATED);
    }
    // Полный список +
    @GetMapping("/all")
    public ResponseEntity<List<SpecialtyDTO>> getAllSpecialties() {
        List<SpecialtyDTO> specialtyDTOs = Mapper.convertToDtoList(specialtyService.getAllSpecialties());
        return new ResponseEntity<>(specialtyDTOs, HttpStatus.OK);
    }

    // Поиск специальности по id +
    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> getSpecialtyById(@PathVariable Long id) {
        Specialty specialty = specialtyService.findById(id);
        if (specialty != null) {
            return new ResponseEntity<>(Mapper.SpecialtyConvertToDto(specialty), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удаление по id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSpecialty(@PathVariable Long id) {
        specialtyService.deleteSpecialty(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
