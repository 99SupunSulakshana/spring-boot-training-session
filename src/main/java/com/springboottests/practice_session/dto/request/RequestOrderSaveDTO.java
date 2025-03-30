package com.springboottests.practice_session.dto.request;

import com.springboottests.practice_session.entity.Customer;
import com.springboottests.practice_session.entity.OrderDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestOrderSaveDTO {
    private String customer;
    private Date date;
    private Double total;
    private List<RequestOrderDetailsSaveDTO> orderDetails;
}
