package com.codeWithProjects.HotelServer.service.auth;

import com.codeWithProjects.HotelServer.Repository.UserRepository;
import com.codeWithProjects.HotelServer.entity.User;
import com.codeWithProjects.HotelServer.enums.UserRole;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

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

}
