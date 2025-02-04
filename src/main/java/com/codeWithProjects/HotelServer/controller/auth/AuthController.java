package com.codeWithProjects.HotelServer.controller.auth;

import com.codeWithProjects.HotelServer.Repository.UserRepository;
import com.codeWithProjects.HotelServer.dto.AuthenticationRequest;
import com.codeWithProjects.HotelServer.dto.AuthenticationResponse;
import com.codeWithProjects.HotelServer.dto.SignUpRequest;
import com.codeWithProjects.HotelServer.dto.UserDto;
import com.codeWithProjects.HotelServer.entity.User;
import com.codeWithProjects.HotelServer.service.auth.AuthService;
import com.codeWithProjects.HotelServer.service.jwt.UserService;
import com.codeWithProjects.HotelServer.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtUtil jwtUtil;


    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest) {
        try {
            UserDto createdUser = authService.createUser(signUpRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (EntityExistsException entityExistsException) {
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e){
            return new ResponseEntity<>("User not created, come again later",HttpStatus.BAD_REQUEST);
        }
     }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationResponse(@RequestBody AuthenticationRequest authenticationRequest) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid email or password");
        }

        final UserDetails userDetails = userService.userDetailService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        System.out.println("Here");

        final  String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }
        return authenticationResponse;
    }
}
