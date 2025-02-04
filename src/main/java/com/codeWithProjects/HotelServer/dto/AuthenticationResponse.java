package com.codeWithProjects.HotelServer.dto;

import com.codeWithProjects.HotelServer.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private Long userId;

    private UserRole userRole;

}
