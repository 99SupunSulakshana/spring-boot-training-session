package com.springboottests.practice_session.dto.request;

import com.springboottests.practice_session.entity.Item;
import com.springboottests.practice_session.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSaveDTO {
    private String itemName;
    private double qty;
    private double amount;
    private String orders;
    private String item;
}
