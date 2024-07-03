package com.cocoon.cop.domain.bank;

import com.cocoon.cop.domain.base.TimeBaseEntity;
import com.cocoon.cop.domain.enums.BankStatus;
import com.cocoon.cop.domain.main.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString(of = {"id", "accountNumber", "balance", "savingBalance", "status"})
@Table(name = "`Account`")
public class Account extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "account_number", nullable = false, length = 30)
    private String accountNumber;

    @Column(name = "balance")
    private int balance;

    @Column(name = "saving_balance")
    private int savingBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BankStatus status;

    public Account(Member member, String accountNumber, int balance) {
        this.member = member;
        this.accountNumber = accountNumber;
        this.status = BankStatus.ACTIVE;
        this.balance = balance;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int subtractBalance(int balance) {
        this.balance -= balance;
        return this.balance;
    }

    public int addBalance(int amount) {
        this.savingBalance += amount;
        return this.savingBalance;
    }
}
