package edu.jundev.donation.controller;

import edu.jundev.donation.dto.requests.LoginRequest;
import edu.jundev.donation.dto.requests.RegisterRequest;
import edu.jundev.donation.dto.requests.ResetPasswordRequest;
import edu.jundev.donation.dto.response.ResponseJwt;
import edu.jundev.donation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseJwt> authenticateUser(@Valid @RequestBody LoginRequest requestLogin){
        return ResponseEntity.ok(userService.authenticateUser(requestLogin));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest requestSignup){
        userService.registerUser(requestSignup);
        return ResponseEntity.ok("Code has been sent to email, please check it!");
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activateUser(@RequestParam(name = "code") String code,
                                          @RequestParam(name = "email") String email) {
        userService.activateUser(code, email);
        return ResponseEntity.ok("Your account has been activated!");
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String email) {
        userService.restorePassword(email);
        return ResponseEntity.ok("Code has been sent to email, please check it!");
    }

    @PostMapping("/reset/password")
    public ResponseEntity<?> setNewPassword(@Valid @RequestBody ResetPasswordRequest form) {
        userService.setNewPassword(form);
        return ResponseEntity.ok("Your password has been changed!");
    }
}
