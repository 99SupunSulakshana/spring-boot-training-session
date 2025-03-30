package com.springboottests.practice_session.service;

import com.springboottests.practice_session.dto.pagination.PaginatedResponseOrderDetailsDTO;
import com.springboottests.practice_session.dto.request.RequestOrderSaveDTO;
import jakarta.validation.constraints.Max;
import org.springframework.stereotype.Service;


public interface OrderService {
    public String saveCustomer(RequestOrderSaveDTO requestOrderSaveDTO);

    PaginatedResponseOrderDetailsDTO getAllOrderDetails(int page, @Max(50) int size);
}
