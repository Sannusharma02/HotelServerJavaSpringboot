package com.codeWithProjects.HotelServer.controller.auth;

import com.codeWithProjects.HotelServer.dto.SignUpRequest;
import com.codeWithProjects.HotelServer.dto.UserDto;
import com.codeWithProjects.HotelServer.service.auth.AuthService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest) {
        try {
            UserDto createdUser = authService.createUser(signUpRequest);
            System.out.println(HttpStatus.OK);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (EntityExistsException entityExistsException) {
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e){
            return new ResponseEntity<>("User not created, come again later",HttpStatus.BAD_REQUEST);
        }
    }
}
