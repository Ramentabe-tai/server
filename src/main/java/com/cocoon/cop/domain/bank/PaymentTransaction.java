package com.cocoon.cop.domain.bank;

import com.cocoon.cop.domain.enums.TransactionType;
import com.cocoon.cop.domain.main.Category;
import com.cocoon.cop.domain.main.Member;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "`PaymentTransaction`")
public class PaymentTransaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    private String message;

    @CreatedDate
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;


}
