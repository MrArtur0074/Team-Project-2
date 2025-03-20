package edu.jundev.donation.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "service_category")
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "name_of_category",nullable = false)
    private String nameOfCategory;

    @Column(name = "price",nullable = false)
    private Integer price;

}
