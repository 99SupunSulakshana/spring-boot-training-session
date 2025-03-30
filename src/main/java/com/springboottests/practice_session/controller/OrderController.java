package com.springboottests.practice_session.controller;

import com.springboottests.practice_session.dto.pagination.PaginatedResponseOrderDetailsDTO;
import com.springboottests.practice_session.dto.request.ItemSaveRequestDTO;
import com.springboottests.practice_session.dto.request.RequestOrderSaveDTO;
import com.springboottests.practice_session.service.OrderService;
import com.springboottests.practice_session.util.response.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Max;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "order save")
    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveOrder(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {
        String response = orderService.saveCustomer(requestOrderSaveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        200,
                        "Item Added Successfully!",
                        response
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
            params = {"page", "size"}, path = {"/get-order-details"}
    )
    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ) {
        PaginatedResponseOrderDetailsDTO paginatedResponseOrderDetailsDTO = orderService.getAllOrderDetails(page, size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        200,
                        "Orders Fetch Successfully!",
                        paginatedResponseOrderDetailsDTO
                ),
                HttpStatus.OK
        );
    }
}
