package com.springboottests.practice_session.dto.response;

import com.springboottests.practice_session.entity.enums.MeasuringUnitType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemGetResponseDTO {
    private String itemID;
    private String itemName;
    private MeasuringUnitType measuringUnitType;
    private double balanceQty;
    private double price;
    private double sellingPrice;
    private boolean activeState;
}
