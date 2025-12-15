package com.example.Week1Introduction.Week_1_.Introduction.config;

import com.example.Week1Introduction.Week_1_.Introduction.DTOClass.LoginDTO;
import com.example.Week1Introduction.Week_1_.Introduction.DTOClass.UserDto;

import com.example.Week1Introduction.Week_1_.Introduction.api.model.ApiResponse;
import com.example.Week1Introduction.Week_1_.Introduction.system.UserRepo;
import com.example.Week1Introduction.Week_1_.Introduction.system.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepo userRepo;
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto userDto, HttpServletResponse response)
    {
        return ResponseEntity.ok(userService.signUp(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDTO loginDTO,HttpServletResponse response)
    {
        String token = authService.login(loginDTO);
        response.setHeader("Authorization", "Bearer " + token);
        return ResponseEntity.ok(ApiResponse.success(token,"Token is Generated"));
    }
}
