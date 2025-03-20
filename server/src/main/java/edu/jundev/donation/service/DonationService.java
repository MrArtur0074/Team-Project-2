package edu.jundev.donation.service;

import edu.jundev.donation.dto.DonationDto;
import edu.jundev.donation.dto.requests.DonationRequest;
import edu.jundev.donation.entity.*;
import edu.jundev.donation.exception.NotFoundException;
import edu.jundev.donation.mapper.DonationMapper;
import edu.jundev.donation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;
    private final UserInfoRepository userInfoRepository;
    private final MedicalCenterRepository medicalCenterRepository;
    private final StatusRepository statusRepository;
    private final BloodTypeRepository bloodTypeRepository;
    private final UserRepository userRepository;
    private final DonationMapper donationMapper;


    public DonationDto donate(DonationRequest form) throws NotFoundException {
        User user = userRepository.findByEmail(form.getEmail())
                .orElseThrow(() -> new NotFoundException("No user found with email " + form.getEmail()));
        MedicalCenter medicalCenter = medicalCenterRepository.findById(form.getMedicalCenterId())
                .orElseThrow(() -> new NotFoundException("No medical centre found with id" + form.getMedicalCenterId()));
        UserInfo userInfo = getUserCardByUser(user);
        Donation donation = donationMapper.toEntity(user, userInfo, medicalCenter, form.getBloodVolume());
        Integer newPoints = updateStatus(userInfo);
        donation.setPoints(newPoints);
        Donation savedDonation = donationRepository.save(donation);
        return donationMapper.toDto(savedDonation);
    }

    public Page<DonationDto> findHistory(User user, Pageable pageable) {
        Page<Donation> donations;
        if (ifUserAdmin(user)) {
            donations = donationRepository.findAll(pageable);
        } else {
            donations = donationRepository.findByUser(user, pageable);
        }
        return donations.map(donationMapper::toDto);
    }

    public Page<DonationDto> findAll(Pageable pageable) {
        var donations = donationRepository.findByOrderByCreatedAtDesc(pageable);
        return donations.map(donationMapper::toDto);
    }

    private boolean ifUserAdmin(User user) {
        var roles = user.getRoles();
        AtomicBoolean result = new AtomicBoolean(false);
        roles.forEach(e -> {
            if (e.getName().equals("ROLE_ADMIN")) result.set(true);
        });
        return result.get();
    }

    private Integer updateStatus(UserInfo userInfo) throws NotFoundException {
        Integer amount = userInfo.getAmountOfDonations();
        Status newStatus = statusRepository.findByQueueNumber(amount + 1)
                .orElse(userInfo.getStatus());
        userInfo.setAmountOfDonations(amount + 1);
        userInfo.setStatus(newStatus);
        userInfo.setPoints(userInfo.getPoints() + newStatus.getPoints());
        userInfoRepository.save(userInfo);

        return newStatus.getPoints();
    }

    private UserInfo getUserCardByUser(User user) throws NotFoundException {
        return userInfoRepository.findByUser(user)
                .orElseThrow(() -> new NotFoundException("User's card doesn't exist!"));
    }
}
