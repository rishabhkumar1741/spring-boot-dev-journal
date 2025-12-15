package com.example.Week1Introduction.Week_1_.Introduction.DTOClass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {
    private  Long id;
    private String email;
    private String password;
}
