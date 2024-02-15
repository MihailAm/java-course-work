package ru.mihail.spring.ispi.controllers;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.models.Users;
import ru.mihail.spring.ispi.services.UsersService;

@RestController
@RequestMapping("api/auth")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;

    }

    @PostMapping("/new-doctor")
    public String create_doctor(@RequestBody @Valid Users users) {
//        usersService.save(users);
          return "Doctor is saved";
    }

    @PostMapping("/new-patient")
    public String create(@RequestBody @Valid Users users) {
        usersService.save(users);
        return "user is saved";
    }

}
