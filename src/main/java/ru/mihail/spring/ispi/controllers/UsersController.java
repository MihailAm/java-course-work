package ru.mihail.spring.ispi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mihail.spring.ispi.models.Users;
import ru.mihail.spring.ispi.services.UsersService;

@RestController
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;

    }
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome you the Hospital";
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    public String doctor(){
        return "You doctor";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin(){
        return "You admin";
    }

    @GetMapping("/patient")
    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    public String patient(){
        return "You patient";
    }


    @GetMapping("/people/new")
    public String newPerson(@ModelAttribute("users") Users users) {return "redirect:/people/new";}

    @PostMapping("/new-user")
    public String create(@RequestBody Users users) {
        usersService.save(users);
        return "user is saved";
    }



}
