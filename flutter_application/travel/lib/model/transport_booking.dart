class TransportBooking {
  final int? transportBookingId;
  final int? bookingId;
  final int? transportId;
  final DateTime? departureDate;
  final DateTime? arrivalDate;
  final int? numSeats;
  final String? status;
  final String? seatPlan;

  TransportBooking({
    this.transportBookingId,
    this.bookingId,
    this.transportId,
    this.departureDate,
    this.arrivalDate,
    this.numSeats,
    this.status,
    this.seatPlan,
  });

  factory TransportBooking.fromJson(Map<String, dynamic> json) {
    return TransportBooking(
      transportBookingId: json['transportBookingId'],
      bookingId: json['booking']?['bookingId'], // nested JSON
      transportId: json['transport']?['transportId'],
      departureDate: json['departureDate'] != null ? DateTime.parse(json['departureDate']) : null,
      arrivalDate: json['arrivalDate'] != null ? DateTime.parse(json['arrivalDate']) : null,
      numSeats: json['numSeats'],
      status: json['status'],
      seatPlan: json['seatPlan'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'transportBookingId': transportBookingId,
      'booking': {'bookingId': bookingId},
      'transport': {'transportId': transportId},
      'departureDate': departureDate?.toIso8601String(),
      'arrivalDate': arrivalDate?.toIso8601String(),
      'numSeats': numSeats,
      'status': status,
      'seatPlan': seatPlan,
    };
  }
}
