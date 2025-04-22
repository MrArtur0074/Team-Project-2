package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.requests.RegisterRequest;
import edu.jundev.donation.entity.UserActivation;
import edu.jundev.donation.entity.UserInfo;
import edu.jundev.donation.exception.NotFoundException;
import edu.jundev.donation.repository.BloodTypeRepository;
import edu.jundev.donation.repository.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserActivationMapper {
    private final GenderRepository genderRepository;
    private final BloodTypeRepository bloodTypeRepository;
    private final UserInfoMapper userInfoMapper;

    public UserActivation toEntity(RegisterRequest form) {
        Random rnd = new Random();
        return UserActivation.builder()
                .name(form.getName())
                .surname(form.getSurname())
                .email(form.getEmail())
                .code(String.valueOf(rnd.nextInt(900000) + 100000))
                .bloodType(bloodTypeRepository.findById(form.getBloodTypeId())
                        .orElseThrow(() -> new NotFoundException("Blood type with id " + form.getBloodTypeId() + " not found!")))
                .gender(genderRepository.findById(form.getGenderId())
                        .orElseThrow(() -> new NotFoundException("Gender with id " + form.getGenderId() + " not found!")))
                .birthDate(form.getBirthDate())
                .password(form.getPassword())
                .build();
    }
}
