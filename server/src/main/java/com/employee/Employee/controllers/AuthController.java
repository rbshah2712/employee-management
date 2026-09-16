package com.employee.Employee.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employee.Employee.dtos.LoginRequestDTO;
import com.employee.Employee.dtos.LoginResponseDTO;
import com.employee.Employee.services.AuthService;

@RestController 
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
      public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDTO loginRequest) {
      LoginResponseDTO loginResponse = authService.login(loginRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Login successful");
        response.put("token", loginResponse.getToken());
        response.put("employee", loginResponse.getEmployee());
        

        return ResponseEntity.ok(response);
    }

    // This local exception handler intercepts the AuthService errors
    // and turns them into a clean 400 Bad Request instead of a 500 error
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
            "success", false,
            "message", ex.getMessage()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody LoginRequestDTO loginRequest) {
          return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Registration successful"
          ));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String, Object>> forgotPassword(@RequestBody LoginRequestDTO loginRequest) {
          return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Password reset link sent to your email"
          ));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody LoginRequestDTO loginRequest) {
          return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Password reset successful"
          ));
    }
}
