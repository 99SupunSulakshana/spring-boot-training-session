package com.springboottests.practice_session.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    @UuidGenerator
    @Column(name = "order_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "order_date", columnDefinition = "DATETIME")
    private Date date;

    @Column(name = "total", nullable = false)
    private Double total;

    @OneToMany(mappedBy = "orders")
    private Set<OrderDetails> orderDetails;


    public Order(Customer customer, Date date, Double total) {
        this.customer = customer;
        this.date = date;
        this.total = total;
    }
}
