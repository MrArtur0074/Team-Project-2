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
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseJwt> authenticateUser(@Valid @RequestBody LoginRequest requestLogin){
        // Additional validation checks can be placed here if needed
        return ResponseEntity.ok(userService.authenticateUser(requestLogin));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest requestSignup){
        // Ensure the user signup request is valid
        if (requestSignup.getEmail() == null || requestSignup.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email and password cannot be null");
        }

        userService.registerUser(requestSignup);
        return ResponseEntity.ok("Code has been sent to email, please check it!");
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activateUser(@RequestParam(name = "code") String code,
                                          @RequestParam(name = "email") String email) {
        // Validate email and activation code
        if (code == null || code.isEmpty() || email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Activation code and email cannot be empty");
        }

        userService.activateUser(code, email);
        return ResponseEntity.ok("Your account has been activated!");
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String email) {
        // Validate email input
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email cannot be empty");
        }

        userService.restorePassword(email);
        return ResponseEntity.ok("Code has been sent to email, please check it!");
    }

    @PostMapping("/reset/password")
    public ResponseEntity<?> setNewPassword(@Valid @RequestBody ResetPasswordRequest form) {
        // Additional validation to ensure the new password is provided


        userService.setNewPassword(form);
        return ResponseEntity.ok("Your password has been changed!");
    }
}
