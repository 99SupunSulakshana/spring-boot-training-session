package com.springboottests.practice_session.dto.pagination;

import com.springboottests.practice_session.dto.response.ItemGetResponseDTO;
import com.springboottests.practice_session.dto.response.OrderDetailsGetResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseOrderDetailsDTO {
    List<OrderDetailsGetResponseDTO> list;
    private long dataCount;
}
