package com.example.Week1Introduction.Week_1_.Introduction.system;

import com.example.Week1Introduction.Week_1_.Introduction.DTOClass.LoginDTO;
import com.example.Week1Introduction.Week_1_.Introduction.DTOClass.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username).orElseThrow(()-> new RuntimeException("User not found"));
    }

    public UserDto signUp(UserDto userDto)
    {
        Optional<QC_EGMS_USERS> user = userRepo.findByEmail(userDto.getEmail());
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
