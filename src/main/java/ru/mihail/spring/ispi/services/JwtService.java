package ru.mihail.spring.ispi.services;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.mihail.spring.ispi.models.Administrator;
import ru.mihail.spring.ispi.models.Doctor;
import ru.mihail.spring.ispi.models.Patient;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {
    private SecretKey key;

    public JwtService() {
        String secretKey = "aaskldhj12fasdfas3123kjdlf3hajsdhfuawe112323hkjsadhfjkhuaksjfh";
        byte[] keyBytes = Base64.getDecoder().decode(secretKey.getBytes(StandardCharsets.UTF_8));
        this.key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String generateDoctorToken(Doctor doctor) {
        return Jwts.builder()
                .claim("id", doctor.getId())
                .claim("email", doctor.getUser().getEmail())
                .claim("lastName", doctor.getLastName())
                .claim("role", doctor.getUser().getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }

    public String generatePatientToken(Patient patient) {
        return Jwts.builder()
                .claim("id", patient.getId())
                .claim("email", patient.getUser().getEmail())
                .claim("lastName", patient.getLastName())
                .claim("role", patient.getUser().getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }

    public String generateAdminToken(Administrator admin) {
        return Jwts.builder()
                .claim("id", admin.getId())
                .claim("email", admin.getUser().getEmail())
                .claim("lastName", admin.getLastName())
                .claim("role", admin.getUser().getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }


    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
