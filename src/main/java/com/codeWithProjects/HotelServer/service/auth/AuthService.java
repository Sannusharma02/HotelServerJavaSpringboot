package com.codeWithProjects.HotelServer.service.auth;

import com.codeWithProjects.HotelServer.dto.SignUpRequest;
import com.codeWithProjects.HotelServer.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignUpRequest signUpRequest);
}
