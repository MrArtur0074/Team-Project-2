package edu.jundev.donation.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private BloodType bloodType;

    @ManyToOne
    private Gender gender;

    @Column(name = "points")
    private Integer points;

    @ManyToOne(cascade = CascadeType.ALL)
    private Status status;

    @OneToOne
    private User user;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    private Region region;

    @Column(name = "amount_of_donations", nullable = false)
    private Integer amountOfDonations;
}
