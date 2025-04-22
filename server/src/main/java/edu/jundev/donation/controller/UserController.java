package edu.jundev.donation.controller;

import edu.jundev.donation.dto.UserDto;
import edu.jundev.donation.dto.UserInfoDto;
import edu.jundev.donation.dto.requests.UserEditRequest;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.entity.UserInfo;
import edu.jundev.donation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    private final UserService userService;

    @PostMapping("/edit")
    ResponseEntity<UserInfoDto> editUser(@Valid @RequestBody UserEditRequest userEditRequest,
                                     @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.updateUser(userEditRequest, user));
    }
    @GetMapping("get/by-id")
    ResponseEntity<UserInfoDto> getUserById(@RequestParam Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
