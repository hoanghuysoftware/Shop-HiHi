package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.OrderDTO;
import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.model.Order;
import com.personal.beshophihi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ResponseMessage> doGetAllOrders() { // Pagination is needed here
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched all orders successfully.")
                        .data(orderService.getAllOrders())
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetOrderById(@PathVariable Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched order with ID: " + id + " successfully.")
                        .data(orderService.getOrderById(id))
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseMessage> doGetOrderByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched order with user ID: " + id + " successfully.")
                        .data(orderService.getOrdersByUserId(id))
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> doCreateOrder(@RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Order created successfully.")
                        .data(orderService.createOrder(orderDTO))
                        .build(),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseMessage> doUpdateOrder(@PathVariable Long id, @RequestParam("status") Long idStatus) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Order with ID: " + id + " updated successfully.")
                        .data(orderService.updateOrder(id, idStatus))
                        .build(),
                HttpStatus.OK);
    }




}
