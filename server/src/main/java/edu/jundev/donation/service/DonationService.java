package edu.jundev.donation.service;

import edu.jundev.donation.dto.DonationDto;
import edu.jundev.donation.dto.requests.DonationRequest;
import edu.jundev.donation.entity.*;
import edu.jundev.donation.exception.NotFoundException;
import edu.jundev.donation.mapper.DonationMapper;
import edu.jundev.donation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;
    private final UserInfoRepository userInfoRepository;
    private final MedicalCenterRepository medicalCenterRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final DonationMapper donationMapper;


    public DonationDto donate(DonationRequest form) {
        User user = userRepository.findByEmail(form.getEmail())
                .orElseThrow(() -> new NotFoundException("No user found with email " + form.getEmail()));
        MedicalCenter medicalCenter = medicalCenterRepository.findById(form.getMedicalCenterId())
                .orElseThrow(() -> new NotFoundException("No medical centre found with id" + form.getMedicalCenterId()));

        Donation donation = donationMapper.toEntity(user, medicalCenter, form.getBloodVolume());
        Integer newPoints = updateStatus(user);
        donation.setPoints(newPoints);
        Donation savedDonation = donationRepository.save(donation);
        return donationMapper.toDto(savedDonation);

    }

    private Integer updateStatus(User user) throws NotFoundException {
        UserInfo userInfo = userInfoRepository.findByUser(user)
                .orElseThrow(() -> new NotFoundException("The user doesn't exist!")); //находит карточку юзера

        Integer amount = userInfo.getAmountOfDonations(); // количество донатов юзера

        Status newStatus = statusRepository.findByQueueNumber(amount + 1)
                .orElse(userInfo.getStatus()); // новый статус

        userInfo.setStatus(newStatus); // ставится новый статус для юзера
        userInfo.setPoints(userInfo.getPoints() + newStatus.getPoints()); // добавляются баллы юзеру
        userInfoRepository.save(userInfo);

        return newStatus.getPoints();// сохранение
    }

}
