package com.cocoon.cop.domain.bank;

import com.cocoon.cop.domain.base.TimeBaseEntity;
import com.cocoon.cop.domain.enums.BankStatus;
import com.cocoon.cop.domain.main.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString(of = {"id", "accountNumber", "balance", "status", "member"})
@Table(name = "`Account`")
public class Account extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private Member member;

    @Column(name = "account_number", nullable = false, length = 30)
    private String accountNumber;

    private int balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private BankStatus status;

    public Account(Member member, String accountNumber) {
        this.member = member;
        this.accountNumber = accountNumber;
        this.status = BankStatus.ACTIVE;
    }

}
