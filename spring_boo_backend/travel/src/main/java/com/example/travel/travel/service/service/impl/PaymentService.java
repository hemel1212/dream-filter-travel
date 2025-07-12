package com.example.travel.travel.service.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.travel.travel.entity.Payment;

public interface PaymentService {
	List<Payment> getAllPayments();

	Optional<Payment> getPaymentById(Long paymentId);

	Payment createPayment(Payment payment);

	Payment updatePayment(Long paymentId, Payment updatedPayment);

	void deletePayment(Long paymentId);

	List<Payment> getPaymentsByBooking(Long bookingId);
}
