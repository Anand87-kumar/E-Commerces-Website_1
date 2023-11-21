package com.anand.ServiceImpl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anand.model.Payment;
import com.anand.repository.PaymentRepository;
import com.anand.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;


import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${razorpay.keyId}")
    private String keyId;

    @Value("${razorpay.keySecret}")
    private String keySecret;

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(BigDecimal amount) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(keyId, keySecret);

            JSONObject orderRequest = createOrderRequest(amount);

            Order order = razorpayClient.orders.create(orderRequest);

            Payment payment = new Payment();
            payment.setAmount(amount);
            payment.setOrderId(order.get("id").toString());
            return paymentRepository.save(payment);
        } catch (RazorpayException e) {
            e.printStackTrace(); 
            return null; 
        }
    }

    private JSONObject createOrderRequest(BigDecimal amount) {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount.multiply(new BigDecimal(100)).intValue());
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_123456");
        return orderRequest;
    }
}
