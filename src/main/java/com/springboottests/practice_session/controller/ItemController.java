package com.springboottests.practice_session.controller;

import com.springboottests.practice_session.dto.CustomerDTO;
import com.springboottests.practice_session.dto.pagination.PaginatedResponseItemDTO;
import com.springboottests.practice_session.dto.request.ItemSaveRequestDTO;
import com.springboottests.practice_session.dto.response.ItemGetResponseDTO;
import com.springboottests.practice_session.service.ItemService;
import com.springboottests.practice_session.util.response.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
@CrossOrigin
@Tag(name = "Item API", description = "Operations related to items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Operation(summary = "item save")
    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String response = itemService.saveCustomer(itemSaveRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        200,
                        "Item Added Successfully!",
                        response
                ),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "item get by name and status")
    @GetMapping(path = "/get-by-name", params = "name")
    public List<ItemGetResponseDTO> getItemByNameAndStatus(@RequestParam(value = "name") String itemName) {
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatus(itemName);
        return itemDTOS;
    }

    @Operation(summary = "item get by name and status")
    @GetMapping(path = "/get-by-name-with-mapstruct", params = "name")
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(@RequestParam(value = "name") String itemName) {
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatusByMapstruct(itemName);
        return itemDTOS;
    }

    @Operation(summary = "item get by name and status")
    @GetMapping(path = "/get-all-item-by-status", params = {"activeStatus","page","size"})
    public ResponseEntity<StandardResponse> getItemByActiveStatus(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ) {

        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getItemByActiveStatusWithPaginated(activeStatus,page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        200,
                        "Successfully!",
                        paginatedResponseItemDTO
                ),
                HttpStatus.OK
        );
    }
}
