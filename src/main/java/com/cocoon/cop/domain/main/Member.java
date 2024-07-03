package com.cocoon.cop.domain.main;

import com.cocoon.cop.domain.bank.Account;
import com.cocoon.cop.domain.base.TimeBaseEntity;
import com.cocoon.cop.domain.enums.Role;
import jakarta.persistence.*;

import lombok.*;

import java.awt.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "`Member`")
@ToString(of = {"id", "account" , "name", "ruby", "email", "role", "phoneNumber", "expPoint"})
public class Member extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    // ふりがな
    private String ruby;

    @Column(nullable = false, length = 180)
    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;

    @Column(name = "exp_point")
    private int expPoint;

    @Column(name = "level", columnDefinition = "INT NOT NULL DEFAULT 1")
    private int level;

    /*
        通常、口座は特定のユーザーに属しているため、Member EntityからAccount Entityへの関係が自然に一方向に形成される。
        この場合、Member Entityが外来キーを管理する方が直感的で管理しやすい。

        Todo : Memberの口座を会員登録する時に設定するか、会員登録をしてから口座を設定するか、先に決めること。
        Todo : 会員登録時に口座を設定する場合は、Accountフィルドはnullable = falseに設定する。
        Todo : 会員登録してから口座を設定する場合は、nullable = trueで、後でUpdateする。
     */
    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "account_id")
    private Account account;


    /**
     * テスト用Constructor
     */
    public Member(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    @Builder
    public Member(String name, String ruby, String password, String email, String role, String phoneNumber) {
        this.name = name;
        this.ruby = ruby;
        this.password = password;
        this.email = email;
        this.role = Role.fromKey(role);
        this.phoneNumber = phoneNumber;
        this.level = 1;
    }

    /**
     * JWTテスト用Constructor
     */
    public Member(Long id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = Role.fromKey(role);
    }

    @Builder
    public Member(String name, String ruby, String email, String password, Role role, String phoneNumber, int level, int expPoint) {
        this.name = name;
        this.ruby = ruby;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.level = level;
        this.expPoint = expPoint;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void addExpPoint(int expPoint) {
        this.expPoint += expPoint;
    }
}
