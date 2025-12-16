package com.example.Week1Introduction.Week_1_.Introduction.config;

import com.example.Week1Introduction.Week_1_.Introduction.DTOClass.LoginDTO;
import com.example.Week1Introduction.Week_1_.Introduction.DTOClass.UserDto;
import com.example.Week1Introduction.Week_1_.Introduction.system.JWTAuth.JwtService;
import com.example.Week1Introduction.Week_1_.Introduction.system.QC_EGMS_USERS;
import com.example.Week1Introduction.Week_1_.Introduction.system.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword())
        );
        QC_EGMS_USERS users = (QC_EGMS_USERS) authentication.getPrincipal();
        System.out.println(users);
        return jwtService.generateToken(users);
    }

    public UserDto signUp(UserDto userDto)
    {

        Optional<QC_EGMS_USERS> user = userRepo.findByUsername(userDto.getEmail());
        if(user.isPresent())
        {
            throw new RuntimeException("User already Presend");
        }else{
            QC_EGMS_USERS newuser = modelMapper.map(userDto,QC_EGMS_USERS.class);
            newuser.setPassword(passwordEncoder.encode(newuser.getPassword()));
            QC_EGMS_USERS savedUser = userRepo.save(newuser);
            return modelMapper.map(savedUser,UserDto.class);
        }
    }
}
