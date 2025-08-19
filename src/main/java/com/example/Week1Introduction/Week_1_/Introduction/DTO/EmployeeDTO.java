package com.example.Week1Introduction.Week_1_.Introduction.DTO;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private Integer age ;
    private boolean isActive;

    EmployeeDTO()
    {

    }

    public EmployeeDTO(boolean isActive, Integer age, String email, String name, Long id) {
        this.isActive = isActive;
        this.age = age;
        this.email = email;
        this.name = name;
        this.id = id;
    }
}
