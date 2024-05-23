package com.cocoon.cop.domain.main;

import com.cocoon.cop.domain.bank.Account;
import com.cocoon.cop.domain.base.TimeBaseEntity;
import com.cocoon.cop.domain.enums.Role;
import jakarta.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "`Member`")
@ToString(of = {"id", "name", "email", "role", "phoneNumber", "rankPoint"})
public class Member extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 180)
    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;

    @Column(name = "rank_point")
    private int rankPoint;

    /*
        通常、口座は特定のユーザーに属しているため、Member EntityからAccount Entityへの関係が自然に一方向に形成される。
        この場合、Member Entityが外来キーを管理する方が直感的で管理しやすい。

        Todo : Memberの口座を会員登録する時に設定するか、会員登録をしてから口座を設定するか、先に決めること。
        Todo : 会員登録時に口座を設定する場合は、Accountフィルドはnullable = falseに設定する。
        Todo : 会員登録してから口座を設定する場合は、nullable = trueで、後でUpdateする。
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
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
    public Member(String name, String email, String password, Role role, String phoneNumber, int rankPoint, Image image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.rankPoint = rankPoint;
        this.image = image;
    }
}
