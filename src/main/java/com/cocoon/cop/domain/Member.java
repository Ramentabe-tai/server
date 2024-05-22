package com.cocoon.cop.domain;

import com.cocoon.cop.domain.base.TimeBaseEntity;
import com.cocoon.cop.domain.enums.Role;
import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Member extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 180)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;

    @Column(name = "rank_point")
    private int rankPoint;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_image_id", referencedColumnName = "image_id")
    private Image image;

    /**
     * テスト用Constructor
     */
    public Member(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    @Builder
    public Member(String name, String password, Role role, String phoneNumber, int rankPoint, Image image) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.rankPoint = rankPoint;
        this.image = image;
    }
}
