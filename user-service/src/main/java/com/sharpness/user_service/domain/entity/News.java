package com.sharpness.user_service.domain.entity;
import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 10000)
    private String title;

    @Column(length = 10000)
    private String description;

    @Column(length = 10000)
    private String image;

    @Column(length = 10000)
    private String date;
}
