package com.example.Week1Introduction.Week_1_.Introduction.config;

import com.example.Week1Introduction.Week_1_.Introduction.DTOClass.LoginDTO;
import com.example.Week1Introduction.Week_1_.Introduction.system.JWTAuth.JwtService;
import com.example.Week1Introduction.Week_1_.Introduction.system.QC_EGMS_USERS;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
        );
        QC_EGMS_USERS users = (QC_EGMS_USERS) authentication.getPrincipal();
        System.out.println(users);
        return jwtService.generateToken(users);
    }
}
