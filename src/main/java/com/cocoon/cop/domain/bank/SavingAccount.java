package com.cocoon.cop.domain.bank;

import com.cocoon.cop.domain.main.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@ToString(of = {"id","savingAccountNumber", "balance", "createdDate"})
public class SavingAccount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saving_account_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @Column(name = "saving_account_number", nullable = false, length = 30)
    private String savingAccountNumber;

    @Column(name = "saving_account_balance")
    private int balance;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Builder
    public SavingAccount(Member member, String savingAccountNumber, int balance, LocalDateTime createdDate) {
        this.member = member;
        this.savingAccountNumber = savingAccountNumber;
        this.balance = balance;
        this.createdDate = createdDate;
    }

    public int addBalance(int balance) {
        this.balance += balance;
        return this.balance;
    }
}
