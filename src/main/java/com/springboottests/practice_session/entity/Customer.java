package com.springboottests.practice_session.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

    @Id
    @UuidGenerator
    @Column(name = "customer_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String customerId;

    @Column(name = "customer_name", length = 100, nullable = false)
    private String customerName;

    @Column(name = "customer_address", length = 255)
    private String customerAddress;

    @Column(name = "customer_salary", length = 255)
    private double customerSalary;

    @Column(name = "contact_number", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private ArrayList<String> contactNumber;

    @Column(name = "nic", length = 10)
    private String nic;

    @Column(name = "active_state", columnDefinition = "TINYINT(1) default 0")
    private boolean active;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    @OneToMany(mappedBy = "customer")
    private Set<OrderDetails> orderDetails;

}
