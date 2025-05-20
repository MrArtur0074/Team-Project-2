package com.sharpness.user_service.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class CustomUser {

    public CustomUser() {}

    public CustomUser(UUID uuid, String email, String username, String password, CustomUserProfile profile) {
        this.uuid = uuid;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    @PrePersist
    public void generateUUID() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Embedded
    private CustomUserProfile profile;

    // Методы для изменения агрегата
    public void updateProfile(CustomUserProfile newProfile) {
        this.profile = newProfile;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    // Геттеры и сеттеры


    @Override
    public String toString() {
        return "CustomUser{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profile=" + profile +
                '}';
    }
}
