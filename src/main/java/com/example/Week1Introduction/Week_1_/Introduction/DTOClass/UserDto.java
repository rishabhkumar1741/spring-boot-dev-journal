package com.example.Week1Introduction.Week_1_.Introduction.DTOClass;
import com.example.Week1Introduction.Week_1_.Introduction.system.Permission;
import com.example.Week1Introduction.Week_1_.Introduction.system.Roles;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class UserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private Set<Roles> roles;
    private Set<Permission> principals;
}
