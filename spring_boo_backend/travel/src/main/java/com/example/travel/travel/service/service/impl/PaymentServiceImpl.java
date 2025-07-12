package com.example.travel.travel.service.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.travel.travel.entity.Payment;
import com.example.travel.travel.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	private final PaymentRepository paymentRepository;

	public PaymentServiceImpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	@Override
	public Optional<Payment> getPaymentById(Long paymentId) {
		return paymentRepository.findById(paymentId);
	}

	@Override
	public Payment createPayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Override
	public Payment updatePayment(Long paymentId, Payment updatedPayment) {
		updatedPayment.setPaymentId(paymentId);
		return paymentRepository.save(updatedPayment);
	}

	@Override
	public void deletePayment(Long paymentId) {
		paymentRepository.deleteById(paymentId);
	}

	@Override
	public List<Payment> getPaymentsByBooking(Long bookingId) {
		return paymentRepository.findByBookingBookingId(bookingId);
	}
}
