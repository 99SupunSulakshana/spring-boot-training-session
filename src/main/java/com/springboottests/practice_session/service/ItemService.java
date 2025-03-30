package com.springboottests.practice_session.service;

import com.springboottests.practice_session.dto.CustomerDTO;
import com.springboottests.practice_session.dto.pagination.PaginatedResponseItemDTO;
import com.springboottests.practice_session.dto.request.CustomerUpdateDTO;
import com.springboottests.practice_session.dto.request.ItemSaveRequestDTO;
import com.springboottests.practice_session.dto.response.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    public String saveCustomer(ItemSaveRequestDTO itemSaveRequestDTO);

    public String updateCustomer(ItemSaveRequestDTO itemUpdateDTO);

    public CustomerDTO getCustomerById(int itemId);

    public List<CustomerDTO> getAllCustomers();

    public String deleteCustomer(int itemId);

    public List<CustomerDTO> getAllCustomersByActiveState(boolean status);

    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);

    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String itemName);

    public List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus, int page, int size);

    public PaginatedResponseItemDTO getItemByActiveStatusWithPaginated(boolean activeStatus, int page, int size);
}
