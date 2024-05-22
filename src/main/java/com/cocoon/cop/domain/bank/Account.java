package com.cocoon.cop.domain.bank;

import com.cocoon.cop.domain.base.TimeBaseEntity;
import com.cocoon.cop.domain.enums.BankStatus;
import com.cocoon.cop.domain.main.Member;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "`Account`")
public class Account extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
    private Member member;

    @Column(name = "account_number", nullable = false, length = 30)
    private String accountNumber;

    private int balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private BankStatus status = BankStatus.ACTIVE;


}
