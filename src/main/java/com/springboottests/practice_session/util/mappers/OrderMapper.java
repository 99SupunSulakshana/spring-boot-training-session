package com.springboottests.practice_session.util.mappers;

import com.springboottests.practice_session.dto.request.RequestOrderDetailsSaveDTO;
import com.springboottests.practice_session.dto.request.RequestOrderSaveDTO;
import com.springboottests.practice_session.dto.response.ItemGetResponseDTO;
import com.springboottests.practice_session.entity.Item;
import com.springboottests.practice_session.entity.Order;
import com.springboottests.practice_session.entity.OrderDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

}
