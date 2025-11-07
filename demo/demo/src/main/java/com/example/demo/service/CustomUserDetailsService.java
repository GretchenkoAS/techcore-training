package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.CustomException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean register(UserDto dto) {
        if(findByUsername(dto.getUsername()) != null) {
            throw new CustomException("User already exists");
        }

        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        User user = new User(
                dto.getUsername(),
                hashedPassword,
                dto.getRole() != null ? dto.getRole() : "ROLE_USER"
        );

        userRepository.save(user);
        return true;
    }
}
