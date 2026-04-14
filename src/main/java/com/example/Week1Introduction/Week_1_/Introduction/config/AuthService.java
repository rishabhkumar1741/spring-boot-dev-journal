package com.example.Week1Introduction.Week_1_.Introduction.config;

import com.example.Week1Introduction.Week_1_.Introduction.DTOClass.LoginDTO;
import com.example.Week1Introduction.Week_1_.Introduction.DTOClass.TokenDTO;
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

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public TokenDTO login(LoginDTO loginDTO) {
        Optional<QC_EGMS_USERS> users = userRepo.findByUsername(loginDTO.getUsername());
        if (users.isPresent())
        {
            if(passwordEncoder.matches(loginDTO.getPassword(),users.get().getPassword()))
            {
                TokenDTO tokenDTO = new TokenDTO();
                tokenDTO.setAccessToken(jwtService.generateAccessToken(users.get()));
                tokenDTO.setRefreshToken(jwtService.generateRefreshToken(users.get()));
                return tokenDTO;
            }
            else {
                throw new RuntimeException("Enter Valid Username or Password");
            }
        }else {
            throw new NoSuchElementException("UserName is not present");
        }
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
