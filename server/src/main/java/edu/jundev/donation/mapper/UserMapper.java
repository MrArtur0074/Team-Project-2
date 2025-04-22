package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.UserDto;
import edu.jundev.donation.dto.requests.UserEditRequest;
import edu.jundev.donation.dto.response.ResponseJwt;
import edu.jundev.donation.entity.PasswordReset;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.entity.UserActivation;
import edu.jundev.donation.exception.NotFoundException;
import edu.jundev.donation.repository.BloodTypeRepository;
import edu.jundev.donation.repository.GenderRepository;
import edu.jundev.donation.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final BloodTypeRepository bloodTypeRepository;
    private final GenderRepository genderRepository;
    private final RoleRepository roleRepository;
    private final GenderMapper genderMapper;
    private final BloodTypeMapper bloodTypeMapper;


    public User toEntity(UserActivation activation) {
        return User.builder()
                .firstName(activation.getName())
                .lastName(activation.getSurname())
                .email(activation.getEmail())
                .birthDate(activation.getBirthDate())
                .password(activation.getPassword())
                .roles(Set.of(roleRepository.findRoleByName("ROLE_USER")
                        .orElseThrow(() -> new NotFoundException("Role 'ROLE_USER' not found!"))))
                .avatarUrl("anon.jpg")
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .build();
    }

    public User toUserFromEdit(User user, UserEditRequest form, String avatarUrl) {
        user.setFirstName(form.getName());
        user.setLastName(form.getSurname());
        user.setBirthDate(form.getBirthDate());
        user.setAvatarUrl(avatarUrl);
        return user;
    }

    public ResponseJwt toJwt(User user, String token) {
        return ResponseJwt.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .token(token)
                .tokenType("Bearer")
                .build();
    }

    public PasswordReset toPasswordReset(User user) {
        Random rnd = new Random();
        return PasswordReset.builder()
                .user(user)
                .code(String.valueOf(rnd.nextInt(900000) + 100000))
                .build();
    }
}
