package br.com.rayan.backend.picpaysimplificado.domain.transaction;

import br.com.rayan.backend.picpaysimplificado.domain.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="TB_TRANSACTIONS")
@Getter
@Setter
@EqualsAndHashCode(of="transactionId")
public class Transaction {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name="senderId")
    private User sender;

    @ManyToOne
    @JoinColumn(name="receiverId")
    private User receiver;

    private LocalDateTime timestamp;
}
