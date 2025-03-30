package com.springboottests.practice_session.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "order_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetails {

    @Id
    @UuidGenerator
    @Column(name = "order_details_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String orderDetailsID;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "qty", length = 100, nullable = false)
    private double qty;

    @Column(name = "amount", nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order orders;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

}
