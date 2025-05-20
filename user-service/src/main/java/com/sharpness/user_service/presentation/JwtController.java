package com.sharpness.user_service.presentation;

import com.sharpness.user_service.application.service.JwtService;
import com.sharpness.user_service.application.service.RefreshAccessTokenResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt-api")
@Validated
public class JwtController {
    @Autowired
    JwtService jwtService;

    @Operation(summary = "Refresh accessToken with refreshToken")
    @GetMapping("/refresh-accessToken")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RefreshAccessTokenResponseDto.class)))
    public ResponseEntity<?> refreshAccessToken(@RequestHeader("Authorization") String token) {
        return jwtService.refreshAccessToken(token);
    };
}
