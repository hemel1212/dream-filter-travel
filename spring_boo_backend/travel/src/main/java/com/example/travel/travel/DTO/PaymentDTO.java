package com.example.travel.travel.DTO;

import java.util.Date;

import lombok.Data;

@Data

public class PaymentDTO {

	private Long paymentId;
	private Long bookingId;
	private Date paymentDate;
	private Double amount;
	private String paymentMethod;
	private String paymentStatus;
}
