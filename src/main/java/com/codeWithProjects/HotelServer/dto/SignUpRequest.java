package com.codeWithProjects.HotelServer.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class SignUpRequest {

    private String email;
    private String password;
    private String name;

    public SignUpRequest() {
    }

    public SignUpRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

}
