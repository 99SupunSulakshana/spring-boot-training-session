package com.springboottests.practice_session.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.springboottests.practice_session.entity.enums.MeasuringUnitType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {

    @Id
    @UuidGenerator
    @Column(name = "item_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String itemID;

    @Column(name = "item_name", length = 200, nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "measure_type", length = 100, nullable = false)
    private MeasuringUnitType measuringUnitType;

    @Column(name = "balance_qty", length = 100, nullable = false)
    private double balanceQty;

    @Column(name = "supplier_price", length = 100, nullable = false)
    private double price;

    @Column(name = "selling_price", length = 100, nullable = false)
    private double sellingPrice;

    @Column(name = "active_state", columnDefinition = "TINYINT(1) default 0")
    private boolean activeState;

    @OneToMany(mappedBy = "item")
    private Set<OrderDetails> orderDetails;

}
