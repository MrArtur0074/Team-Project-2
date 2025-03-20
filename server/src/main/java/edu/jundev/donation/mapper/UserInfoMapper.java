package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.UserInfoDto;
import edu.jundev.donation.dto.requests.UserEditRequest;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.entity.UserActivation;
import edu.jundev.donation.entity.UserInfo;
import edu.jundev.donation.exception.NotFoundException;
import edu.jundev.donation.repository.BloodTypeRepository;
import edu.jundev.donation.repository.GenderRepository;
import edu.jundev.donation.repository.RegionRepository;
import edu.jundev.donation.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UserInfoMapper {
    private final StatusMapper statusMapper;
    private final UserMapper userMapper;
    private final BloodTypeMapper bloodTypeMapper;
    private final GenderMapper genderMapper;
    private final GenderRepository genderRepository;
    private final BloodTypeRepository bloodTypeRepository;
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;
    private final StatusRepository statusRepository;

    public UserInfo toUserInfoFromRegister(UserActivation activation, User savedUser) {
        return UserInfo.builder()
                .user(savedUser)
                .amountOfDonations(0)
                .points(0)
                .status(statusRepository.findByQueueNumber(0)
                        .orElseThrow(() -> new NotFoundException("Status with queue 0 not found!")))
                .gender(activation.getGender())
                .bloodType(activation.getBloodType())
                .build();
    }

    public UserInfoDto toDto(UserInfo userInfo) {
        return UserInfoDto.builder()
                .id(userInfo.getId())
                .user(userMapper.toDto(userInfo.getUser()))
                .bloodType(bloodTypeMapper.toDto(userInfo.getBloodType()))
                .gender(genderMapper.toDto(userInfo.getGender()))
                .status(statusMapper.toDto(userInfo.getStatus()))
                .amountOfDonations(userInfo.getAmountOfDonations())
                .points(userInfo.getPoints())
                .region(regionMapper.toDto(userInfo.getRegion()))
                .phoneNumber(userInfo.getPhoneNumber())
                .age(LocalDate.now().getYear() - userInfo.getUser().getBirthDate().getYear())
                .build();
    }

    public UserInfo toUserInfoFromEdit(UserInfo userInfo, User updatedUser, UserEditRequest form) {
        userInfo.setUser(updatedUser);
        userInfo.setGender(genderRepository.findById(form.getGenderId())
                .orElseThrow(() -> new NotFoundException("Gender with id " + form.getGenderId() + " not found!")));
        userInfo.setBloodType(bloodTypeRepository.findById(form.getBloodTypeId())
                .orElseThrow(() -> new NotFoundException("Blood type with id " + form.getBloodTypeId() + " not found!")));
        userInfo.setPhoneNumber(form.getPhoneNumber());
        userInfo.setRegion(regionRepository.findById(form.getRegionId())
                .orElseThrow(() -> new NotFoundException("Region with id " + form.getRegionId() + " not found!")));
        return userInfo;
    }
}