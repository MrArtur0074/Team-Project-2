package edu.jundev.donation.controller;

import edu.jundev.donation.dto.UserInfoDto;
import edu.jundev.donation.dto.requests.UserEditRequest;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/edit")
    public ResponseEntity<UserInfoDto> editUser(@Valid @ModelAttribute UserEditRequest userEditRequest,
                                                @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.updateUser(userEditRequest, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserInfoDto>> findAll(@PageableDefault(page = 0, size = 4) Pageable pageable) {
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserInfoDto> getProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getProfile(user));
    }
}
