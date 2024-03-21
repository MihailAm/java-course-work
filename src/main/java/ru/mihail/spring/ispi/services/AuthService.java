package ru.mihail.spring.ispi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.Dto.LoginDTO;
import ru.mihail.spring.ispi.models.Administrator;
import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.models.Patient;
import ru.mihail.spring.ispi.models.Users;
import ru.mihail.spring.ispi.repositories.AdministratorRepository;
import ru.mihail.spring.ispi.repositories.DoctorRepository;
import ru.mihail.spring.ispi.repositories.PatientRepository;
import ru.mihail.spring.ispi.repositories.UserRepository;

import java.util.HashMap;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AdministratorRepository adminRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public void savePatient(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRole("ROLE_PATIENT");
        userRepository.save(users);
    }

    public void saveDoctor(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRole("ROLE_DOCTOR");
        userRepository.save(users);
    }


    public String authenticateUser(LoginDTO userDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
            // Кодирование переданного пароля
//            String encodedPassword = passwordEncoder.encode(userDto.getPassword());

            // Поиск пользователя в базе данных по email и закодированному паролю
            Users user = userRepository.findByEmail(userDto.getEmail());

            if (user != null) {
                // Получить роль пользователя
                String role = user.getRole();

                // Определить, какой тип пользователя (доктор, пациент, админ) и сформировать токен
                if (role.equals("ROLE_DOCTOR")) {
                    Doctor doctor = doctorRepository.findByUser(user);
                    var jwt = jwtService.generateDoctorToken(doctor);
                    var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), (UserDetails) user);
                    return jwt;
                } else if (role.equals("ROLE_PATIENT")) {

                    Patient patient = patientRepository.findByUser(user);
                    var jwt = jwtService.generatePatientToken(patient);
                    var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), (UserDetails) user);
                    return jwt;

                } else if (role.equals("ROLE_ADMIN")) {
                    Administrator admin = adminRepository.findByUser(user);
                    var jwt = jwtService.generateAdminToken(admin);
                    var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), (UserDetails) user);
                    return jwt;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
