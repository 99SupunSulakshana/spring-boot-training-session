package com.springboottests.practice_session.util.mappers;

import com.springboottests.practice_session.dto.CustomerDTO;
import com.springboottests.practice_session.dto.response.ItemGetResponseDTO;
import com.springboottests.practice_session.entity.Customer;
import com.springboottests.practice_session.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    List<ItemGetResponseDTO> entityListToDTOList(List<Item> items);
    List<CustomerDTO> entityCustomerListToDTOList(List<Customer> customers);
    List<ItemGetResponseDTO> listDTOToPage(Page<Item> items);

}
