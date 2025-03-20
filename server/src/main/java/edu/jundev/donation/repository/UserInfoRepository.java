package edu.jundev.donation.repository;

import edu.jundev.donation.entity.User;
import edu.jundev.donation.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUser(User user);
}
