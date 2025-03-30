package com.springboottests.practice_session.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailsGetResponseDTO {
    //Customer
    private String customerName;
    private String customerAddress;
    private List<String> contactNumber;

    //order
    private Date date;
    private Double total;
}
