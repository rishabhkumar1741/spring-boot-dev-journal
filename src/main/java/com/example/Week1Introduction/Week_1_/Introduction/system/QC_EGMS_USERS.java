package com.example.Week1Introduction.Week_1_.Introduction.system;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class QC_EGMS_USERS implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Column(unique = true,nullable = false)
    private String username;
    @Enumerated(EnumType.STRING )
    private Set<Roles> roles;
    @Enumerated(EnumType.STRING)
    private Set<Permission> principals;

    public QC_EGMS_USERS() {
        // default constructor required by ModelMapper
    }

    public QC_EGMS_USERS(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> auth = roles.stream().map((x)->  new SimpleGrantedAuthority("ROLE_"+x.name())).collect(Collectors.toSet());
        principals.forEach(permissions -> auth.add(new SimpleGrantedAuthority(permissions.name())));
        return auth;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
