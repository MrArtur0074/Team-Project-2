package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.UserInfoDto;
import edu.jundev.donation.dto.requests.UserEditRequest;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.entity.UserActivation;
import edu.jundev.donation.entity.UserInfo;
import edu.jundev.donation.repository.BloodTypeRepository;
import edu.jundev.donation.repository.GenderRepository;
import edu.jundev.donation.repository.RegionRepository;
import edu.jundev.donation.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

    public UserInfo toUserInfoFromRegister(UserActivation activation, User savedUser) {
        return UserInfo.builder()
                .user(savedUser)
                .amountOfDonations(0)
                .phoneNumber(996000000)
                .region(regionRepository.findById(9L).orElseThrow())
                .points(0)
                .status(statusMapper.initialStatus())
                .gender(activation.getGender())
                .bloodType(activation.getBloodType())
                .build();
    }

    public UserInfoDto toDto(UserInfo userInfo) {
        return UserInfoDto.builder()
                .id(userInfo.getId())
                .userDto(userMapper.toDto(userInfo.getUser()))
                .bloodTypeDto(bloodTypeMapper.toDto(userInfo.getBloodType()))
                .genderDto(genderMapper.toDto(userInfo.getGender()))
                .statusDto(statusMapper.toDto(userInfo.getStatus()))
                .amountOfDonations(userInfo.getAmountOfDonations())
                .points(userInfo.getPoints())
                .regionDto(regionMapper.toDto(userInfo.getRegion()))
                .phoneNumber(userInfo.getPhoneNumber())
                .build();
    }

    public UserInfo toUserInfoFromEdit(UserInfo userInfo, User updatedUser, UserEditRequest userEditRequest) {
        userInfo.setUser(updatedUser);
        userInfo.setGender(genderRepository.findById(userEditRequest.getGenderId()).orElseThrow());
        userInfo.setBloodType(bloodTypeRepository.findById(userEditRequest.getBloodTypeId()).orElseThrow());
        userInfo.setPhoneNumber(userEditRequest.getPhoneNumber());
        userInfo.setRegion(regionRepository.findById(userEditRequest.getRegionId()).orElseThrow());
        return userInfo;
    }
}