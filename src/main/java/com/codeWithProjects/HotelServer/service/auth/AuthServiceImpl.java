package com.codeWithProjects.HotelServer.service.auth;

import com.codeWithProjects.HotelServer.Repository.UserRepository;
import com.codeWithProjects.HotelServer.dto.SignUpRequest;
import com.codeWithProjects.HotelServer.dto.UserDto;
import com.codeWithProjects.HotelServer.entity.User;
import com.codeWithProjects.HotelServer.enums.UserRole;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createAnAdminAccount() {
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()) {
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin account created successfully");
        }else {
            System.out.println("Admin Account already exists");
        }
    }

    @Override
    public UserDto createUser(SignUpRequest signUpRequest){
        if(userRepository.findFirstByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new EntityExistsException("User Already Present with this email" + signUpRequest.getEmail());
        }
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        User createdUser = userRepository.save(user);
//        System.out.println("User created successfully");
        return createdUser.getUserDto();
    }

}
