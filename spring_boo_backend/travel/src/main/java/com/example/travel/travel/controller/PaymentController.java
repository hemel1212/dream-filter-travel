package com.example.travel.travel.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.entity.Booking;
import com.example.travel.travel.entity.Payment;
import com.example.travel.travel.repository.BookingRepository;
import com.example.travel.travel.repository.PaymentRepository;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @PostMapping
    public Payment createPayment(@RequestBody Map<String, Object> payload) {
        Long bookingId = Long.valueOf(payload.get("bookingId").toString());
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(Double.valueOf(payload.get("amount").toString()));
        payment.setPaymentDate(new Date());
        payment.setPaymentMethod(payload.get("paymentMethod").toString());
        payment.setPaymentStatus(payload.get("paymentStatus").toString());

        return paymentRepository.save(payment);
    }
}
