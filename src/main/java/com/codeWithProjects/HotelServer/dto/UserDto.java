package com.codeWithProjects.HotelServer.dto;

import com.codeWithProjects.HotelServer.enums.UserRole;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private UserRole UserRole;

}
