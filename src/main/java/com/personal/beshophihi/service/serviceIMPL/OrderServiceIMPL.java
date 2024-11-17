package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.dto.OrderDTO;
import com.personal.beshophihi.dto.OrderDetailDTO;
import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.model.*;
import com.personal.beshophihi.repository.OrderDetailRepo;
import com.personal.beshophihi.repository.OrderRepo;
import com.personal.beshophihi.repository.ProductRepo;
import com.personal.beshophihi.service.AddressService;
import com.personal.beshophihi.service.OrderService;
import com.personal.beshophihi.service.StatusService;
import com.personal.beshophihi.service.UserService;
import com.personal.beshophihi.utils.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceIMPL implements OrderService {
    private final OrderRepo orderRepo;
    private final UserService userService;
    private final ProductRepo productRepo;
    private final StatusService statusService;
    private final AddressService addressService;

    @Override
    public List<Order> getAllOrders() { // need div pages
        return orderRepo.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found order with id: " + id)
        );
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepo.getOrdersByUserId(userId);
    }

    @Override
    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        boolean statusPayment = PaymentMethod.valueOf(orderDTO.getPaymentMethod()) == PaymentMethod.ONLINE;
        Status status = statusService.getStatusById(orderDTO.getStatusId());
        User user = userService.getUserById(orderDTO.getUserId());
        BigDecimal totalPrice = BigDecimal.valueOf(0.0);
        Address address = addressService.getAddressById(orderDTO.getDeliveryAddressID());

        Order order = Order.builder()
                .note(orderDTO.getNote())
                .deliveryAddress(address.getNameAddress())
                .orderDate(LocalDateTime.now())
                .paymentMethod(PaymentMethod.valueOf(orderDTO.getPaymentMethod()))
                .statusPayment(statusPayment)
                .status(status)
                .user(user)
                .build();

        List<OrderDetail> orderDetails = new ArrayList<>();
        for(OrderDetailDTO orderDetail : orderDTO.getOrderDetails()){
            Product product = productRepo.findById(orderDetail.getProductId()).orElseThrow(
                    () -> new EntityNotFound("Not found product with id: "+ orderDetail.getProductId())
            );
            product.setAvailableQuantity(product.getAvailableQuantity() - orderDetail.getQuantity());

            BigDecimal totalPriceItem = product.getSalePrice()
                    .multiply(BigDecimal.valueOf(orderDetail.getQuantity())); // total price item
            totalPrice = totalPrice.add(totalPriceItem); // update total price of order

            OrderDetail orderDetailItem = OrderDetail.builder()
                    .quantity(orderDetail.getQuantity())
                    .unitPrice(product.getSalePrice())
                    .totalPrice(totalPriceItem)
                    .product(product)
                    .order(order)
                    .build();
            orderDetails.add(orderDetailItem);
        }

        order.setOrderDetails(orderDetails);
        order.setTotalPrice(totalPrice);

        return orderRepo.save(order);
    }

//  for user/admin update status of order
    @Override
    public Order updateOrder(Long id, Long idStatus) {
        Order order = getOrderById(id);
        Status status = statusService.getStatusById(idStatus);

        order.setStatus(status);
        return orderRepo.save(order);
    }
}
