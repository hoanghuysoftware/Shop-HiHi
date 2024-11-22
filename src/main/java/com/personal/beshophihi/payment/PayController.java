package com.personal.beshophihi.payment;

import com.personal.beshophihi.model.Order;
import com.personal.beshophihi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PayController {
    private final VNPayConfig vnPayConfig;
    private final OrderService orderService;

    @GetMapping("/VNPay/{idOrder}")
    public String getPay(@PathVariable Long idOrder) throws UnsupportedEncodingException {
        Order order = orderService.getOrderById(idOrder);
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "ordertype";
        long amount = order.getTotalPrice().multiply(BigDecimal.valueOf(100)).longValue(); // so tien
        String bankCode = "NCB";

        // Generate random transaction reference
//        String vnp_TxnRef = vnPayConfig.getRandomNumber(8);
        String vnp_TxnRef = String.valueOf(order.getId());
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = vnPayConfig.getVnp_TmnCode();

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang: " + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", vnPayConfig.getVnp_ReturnUrl());
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                // Build hash data
                hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, "US-ASCII"));
                // Build query
                query.append(URLEncoder.encode(fieldName, "US-ASCII")).append('=')
                        .append(URLEncoder.encode(fieldValue, "US-ASCII"));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }

        String queryUrl = query.toString();
        String vnp_SecureHash = vnPayConfig.hmacSHA512(vnPayConfig.getSecretKey(), hashData.toString());
        return vnPayConfig.getVnp_PayUrl() + "?" + queryUrl + "&vnp_SecureHash=" + vnp_SecureHash;
    }
}
