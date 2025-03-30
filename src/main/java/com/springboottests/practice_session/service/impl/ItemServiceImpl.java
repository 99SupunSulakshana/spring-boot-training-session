package com.springboottests.practice_session.service.impl;

import com.springboottests.practice_session.config.ModelMapperConfig;
import com.springboottests.practice_session.dto.CustomerDTO;
import com.springboottests.practice_session.dto.pagination.PaginatedResponseItemDTO;
import com.springboottests.practice_session.dto.request.CustomerUpdateDTO;
import com.springboottests.practice_session.dto.request.ItemSaveRequestDTO;
import com.springboottests.practice_session.dto.response.ItemGetResponseDTO;
import com.springboottests.practice_session.entity.Customer;
import com.springboottests.practice_session.entity.Item;
import com.springboottests.practice_session.repo.ItemRepo;
import com.springboottests.practice_session.service.ItemService;
import com.springboottests.practice_session.util.exceptions.NotFoundException;
import com.springboottests.practice_session.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;


    @Override
    public String saveCustomer(ItemSaveRequestDTO itemSaveRequestDTO) {
        Item item = modelMapper.map(itemSaveRequestDTO, Item.class);
        item.setActiveState(true);
        if (!itemRepo.existsByItemName(itemSaveRequestDTO.getItemName())) {
            itemRepo.save(item);
            return "Item Added!";
        } else {
            throw new DuplicateKeyException("Already Added!");
        }
    }

    @Override
    public String updateCustomer(ItemSaveRequestDTO itemUpdateDTO) {
        return "";
    }

    @Override
    public CustomerDTO getCustomerById(int itemId) {
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return List.of();
    }

    @Override
    public String deleteCustomer(int itemId) {
        return "";
    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean status) {
        return List.of();
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName, true);
        if (!items.isEmpty()) {
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(items, new TypeToken<List<ItemGetResponseDTO>>() {
            }.getType());
            return itemGetResponseDTOS;
        } else {
            throw new RuntimeException("Not Found!");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String itemName) {
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName, true);
        if (!items.isEmpty()) {
            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDTOList(items);
            return itemGetResponseDTOS;
        } else {
            throw new RuntimeException("Not Found!");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus, int page, int size) {
        List<Item> items = itemRepo.findAllByActiveStateEquals(true);
        if(!items.isEmpty()){
            return itemMapper.entityListToDTOList(items);
        }else{
            throw new NotFoundException("Not Found!");
        }
    }

    @Override
    public PaginatedResponseItemDTO getItemByActiveStatusWithPaginated(boolean activeStatus, int page, int size) {
        Page<Item> items = itemRepo.findAllByActiveStateEquals(activeStatus, PageRequest.of(page, size));
        int count = itemRepo.countAllByActiveStateEquals(activeStatus);
        if(items.getSize() < 1){
            throw new NotFoundException("No Data");
        }
        PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                itemMapper.listDTOToPage(items), count
        );
        return paginatedResponseItemDTO;
    }

}
