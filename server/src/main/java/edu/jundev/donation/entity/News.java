package edu.jundev.donation.entity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "text",nullable = false)
    private String text;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "created_date",nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
}
