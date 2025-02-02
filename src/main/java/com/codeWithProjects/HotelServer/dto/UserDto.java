package com.codeWithProjects.HotelServer.dto;

import com.codeWithProjects.HotelServer.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private UserRole UserRole;

    public void setId(long id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUserRole(UserRole userRole) {
        this.UserRole = userRole;
    }



}
