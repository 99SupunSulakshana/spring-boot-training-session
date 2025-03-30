package com.springboottests.practice_session.service.impl;

import com.springboottests.practice_session.dto.pagination.PaginatedResponseOrderDetailsDTO;
import com.springboottests.practice_session.dto.queryinterfaces.OrderDetailsInterface;
import com.springboottests.practice_session.dto.request.RequestOrderSaveDTO;
import com.springboottests.practice_session.dto.response.OrderDetailsGetResponseDTO;
import com.springboottests.practice_session.entity.Order;
import com.springboottests.practice_session.entity.OrderDetails;
import com.springboottests.practice_session.repo.CustomerRepo;
import com.springboottests.practice_session.repo.ItemRepo;
import com.springboottests.practice_session.repo.OrderDetailsRepo;
import com.springboottests.practice_session.repo.OrderRepo;
import com.springboottests.practice_session.service.OrderService;
import com.springboottests.practice_session.util.mappers.OrderMapper;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    @Transactional
    public String saveCustomer(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order = new Order(
                customerRepo.getReferenceByCustomerId(requestOrderSaveDTO.getCustomer()),
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal()
        );
        orderRepo.save(order);

        if (orderRepo.existsById(order.getOrderId())) {
            List<OrderDetails> orderDetails = modelMapper.map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>() {
            }.getType());
            for (int i = 0; i < orderDetails.size(); i++) {
                orderDetails.get(i).setCustomer(customerRepo.getReferenceByCustomerId(requestOrderSaveDTO.getCustomer()));
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItem(itemRepo.getReferenceById(requestOrderSaveDTO.getOrderDetails().get(i).getItem()));
            }
            if (!orderDetails.isEmpty()) {
                orderDetailsRepo.saveAll(orderDetails);
            }
            return "saved!";
        }
        return null;
    }

    @Override
    public PaginatedResponseOrderDetailsDTO getAllOrderDetails(int page, int size) {
//        List<OrderDetailsInterface> orderDetailsDTO = orderRepo.getAllOrderDetails(PageRequest.of(page, size));
//        List<OrderDetailsGetResponseDTO> list = new ArrayList<>();
//        for (OrderDetailsInterface o : orderDetailsDTO) {
//            OrderDetailsGetResponseDTO r = getOrderDetailsGetResponseDTO(o);
//            list.add(r);
//        }
//        return new PaginatedResponseOrderDetailsDTO(
//                list, orderRepo.count()
//        );

        // Create a pageable object to handle pagination
        Pageable pageable = PageRequest.of(page, size);

        // Fetch paginated data from the repository
        Page<OrderDetailsInterface> orderDetailsPage = orderRepo.getAllOrderDetails(pageable);

        // Convert the results to DTO objects
        List<OrderDetailsGetResponseDTO> orderDetailsList = new ArrayList<>();
        for (OrderDetailsInterface orderDetails : orderDetailsPage.getContent()) {
            // Mapping result from OrderDetailsInterface to OrderDetailsGetResponseDTO
            OrderDetailsGetResponseDTO responseDTO = new OrderDetailsGetResponseDTO(
                    orderDetails.getCustomerName(),
                    orderDetails.getCustomerAddress(),
                    Arrays.asList(orderDetails.getContactNumber().split(",")),  // Splitting contact numbers
                    orderDetails.getDate(),
                    orderDetails.getTotal()
            );
            orderDetailsList.add(responseDTO);
        }

        // Return paginated response DTO
        return new PaginatedResponseOrderDetailsDTO(
                orderDetailsList,
                orderDetailsPage.getTotalElements()  // Total records for pagination
        );
    }

//    private static OrderDetailsGetResponseDTO getOrderDetailsGetResponseDTO(OrderDetailsInterface o) {
////        String contactNumber = String.valueOf(o.getContactNumber());
////        String modifiedContactNumber = contactNumber.replace(",", "");
////        ArrayList<String> contactNumbers = new ArrayList<>(List.of(modifiedContactNumber));
//        OrderDetailsGetResponseDTO r = new OrderDetailsGetResponseDTO(
//                o.getCustomerName(),
//                o.getCustomerAddress(),
//
//                o.getDate(),
//                o.getTotal()
//        );
//        return r;
//    }
}
