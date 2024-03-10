package com.davy.restapi.payment.entity;

import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.payment.enums.PaymentMethod;
import com.davy.restapi.payment.enums.PaymentStatus;
import com.davy.restapi.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "payment_details")
@SequenceGenerator(
        name="default_gen",
        sequenceName = "payment_details_id_seq",
        allocationSize=1)
public class PaymentEntity extends BaseEntity {

    @Column(name = "amaount")
    private int amount;

    @Column(name = "method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToOne(mappedBy = "payment")
    private OrderEntity order;

    @Builder
    public PaymentEntity(LocalDateTime createdAt,
                         LocalDateTime updatedAt,
                         LocalDateTime deletedAt,
                         Long createdBy,
                         Long updatedBy,
                         int amount,
                         PaymentMethod paymentMethod,
                         PaymentStatus paymentStatus,
                         OrderEntity order,
                         Long id) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.order = order;
    }
}
