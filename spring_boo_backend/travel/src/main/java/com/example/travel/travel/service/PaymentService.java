package com.example.travel.travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.travel.travel.entity.Payment;
import com.example.travel.travel.repository.PaymentRepository;

@Service
public class PaymentService {
	private final PaymentRepository paymentRepository;

	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	public Payment getPaymentById(Long paymentId) {
		return paymentRepository.findById(paymentId).orElse(null);
	}

	public Payment createPayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	public Payment updatePayment(Long paymentId, Payment updatedPayment) {
		updatedPayment.setPaymentId(paymentId);
		return paymentRepository.save(updatedPayment);
	}

	public void deletePayment(Long paymentId) {
		paymentRepository.deleteById(paymentId);
	}

	public List<Payment> getPaymentsByBooking(Long bookingId) {
		return paymentRepository.findByBookingBookingId(bookingId);
	}

	public Payment makePayment(Payment payment) {
		Payment save = paymentRepository.save(payment);
		return save;
	}
}
