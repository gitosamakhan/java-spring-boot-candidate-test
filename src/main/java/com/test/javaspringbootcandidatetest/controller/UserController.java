package com.test.javaspringbootcandidatetest.controller;

import com.test.javaspringbootcandidatetest.auth.AuthService;
import com.test.javaspringbootcandidatetest.dao.UserDao;
import com.test.javaspringbootcandidatetest.domain.Role;
import com.test.javaspringbootcandidatetest.domain.User;
import com.test.javaspringbootcandidatetest.domain.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("add")
    public ResponseEntity<Void> addUser(@RequestBody UserRequest request) {
        try {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.USER);
            userDao.save(user);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody UserRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userDao.findByUsername(request.getUsername())
                .orElseThrow();
        String token = authService.generateToken(user);
        return ResponseEntity.ok(token);
    }
}
