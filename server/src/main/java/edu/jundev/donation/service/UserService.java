package edu.jundev.donation.service;

import edu.jundev.donation.configuration.JwtUtils;
import edu.jundev.donation.dto.UserDto;
import edu.jundev.donation.dto.UserInfoDto;
import edu.jundev.donation.dto.requests.ResetPasswordRequest;
import edu.jundev.donation.dto.requests.UserEditRequest;
import edu.jundev.donation.dto.requests.LoginRequest;
import edu.jundev.donation.dto.requests.RegisterRequest;
import edu.jundev.donation.dto.response.ResponseJwt;
import edu.jundev.donation.entity.PasswordReset;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.entity.UserActivation;
import edu.jundev.donation.entity.UserInfo;
import edu.jundev.donation.exception.ActivationException;
import edu.jundev.donation.exception.EmailExistsException;
import edu.jundev.donation.exception.FileException;
import edu.jundev.donation.exception.NotFoundException;
import edu.jundev.donation.mapper.StatusMapper;
import edu.jundev.donation.mapper.UserActivationMapper;
import edu.jundev.donation.mapper.UserInfoMapper;
import edu.jundev.donation.mapper.UserMapper;
import edu.jundev.donation.repository.PasswordResetRepository;
import edu.jundev.donation.repository.UserActivationRepository;
import edu.jundev.donation.repository.UserInfoRepository;
import edu.jundev.donation.repository.UserRepository;
import edu.jundev.donation.utils.CloudStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserActivationRepository userActivationRepository;
    private final PasswordResetRepository passwordResetRepository;
    private final MailSenderService mailSenderService;
    private final UserMapper userMapper;
    private final UserActivationMapper userActivationMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final CloudStorage cloudStorage;
    private final UserInfoMapper userInfoMapper;
    private final UserInfoRepository userInfoRepository;


    public ResponseJwt authenticateUser(LoginRequest requestLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestLogin.getEmail(), requestLogin.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User user = (User) authentication.getPrincipal();
        return userMapper.toJwt(user, jwt);
    }

    public void registerUser(@Valid RegisterRequest registerRequest) throws NotFoundException, EmailExistsException {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailExistsException("Error: Email is already in use!");
        }
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        UserActivation activation = userActivationMapper.toEntity(registerRequest);
        UserActivation saved = userActivationRepository.save(activation);
        mailSenderService.sendMessage(saved.getEmail(), "Activate your account",
                "Code to activate account - " + saved.getCode());
    }

    public void activateUser(String code, String email) throws ActivationException {
        UserActivation activation = userActivationRepository.findByCodeAndEmail(code, email)
                .orElseThrow(() -> new ActivationException("Error: Code doesn't exist!"));
        if (activation.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now()))
            throw new ActivationException("Error: Code time has been expired!");

        User user = userMapper.toEntity(activation);
        User savedUser = userRepository.save(user);
        UserInfo userInfo = userInfoMapper.toUserInfoFromRegister(activation,savedUser);
        userInfoRepository.save(userInfo);
        userActivationRepository.delete(activation);
    }

    public UserInfoDto updateUser(UserEditRequest userEditRequest, User user) throws FileException {
        String avatar = uploadAvatar(userEditRequest.getAvatar());
        User editUser = userMapper.toUserFromEdit(user, userEditRequest, avatar);
        User updatedUser = userRepository.save(editUser);

        UserInfo userInfo = userInfoRepository.findByUser(updatedUser).orElseThrow(()->
                new NotFoundException("No such a user card info found"));

        UserInfo updatedUserInfo = userInfoMapper.toUserInfoFromEdit(userInfo,updatedUser,userEditRequest);
        userInfoRepository.save(updatedUserInfo);
        return userInfoMapper.toDto(updatedUserInfo);
    }

    public void restorePassword(String email) throws NotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found!"));
        PasswordReset passwordReset = userMapper.toPasswordReset(user);
        PasswordReset saved = passwordResetRepository.save(passwordReset);
        mailSenderService.sendMessage(saved.getUser().getEmail(), "Reset your password!",
                "Code to reset your password - " + saved.getCode());
    }

    public void setNewPassword(ResetPasswordRequest form) throws NotFoundException, ActivationException {
        User user = userRepository.findByEmail(form.getEmail())
                .orElseThrow(() -> new NotFoundException("User with email " + form.getEmail() + " not found!"));
        PasswordReset passwordReset = passwordResetRepository.findByCode(form.getCode())
                .orElseThrow(() -> new NotFoundException("Code doesn't exist!"));
        if (passwordReset.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now()))
            throw new ActivationException("Code time has been expired!");
        user.setPassword(passwordEncoder.encode(form.getPassword()));
    }

    private String uploadAvatar(MultipartFile file) throws FileException {
        if (file == null)
            return "anon.jpg";
        if (!cloudStorage.isImageFile(file))
            throw new FileException("Avatar is not image file!");
        return cloudStorage.uploadFile(file);
    }

    public UserInfoDto getUserById(Long id) {
        UserInfo userInfo = userInfoRepository.findById(id).orElseThrow(()->
                new NotFoundException("No such a user found with id" + id));
        return userInfoMapper.toDto(userInfo);
    }
}
