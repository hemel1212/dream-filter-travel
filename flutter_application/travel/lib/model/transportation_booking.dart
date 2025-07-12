class TransportationBooking {
  final int? transportBookingId;
  final int? bookingId;
  final int? transportId;
  final DateTime? departureDate;
  final DateTime? arrivalDate;
  final int? numSeats;
  final String? status;
  final String? seatPlan;

  TransportationBooking({
    this.transportBookingId,
    required this.bookingId,
    required this.transportId,
    required this.departureDate,
    required this.arrivalDate,
    required this.numSeats,
    required this.status,
    required this.seatPlan,
  });

  factory TransportationBooking.fromJson(Map<String, dynamic> json) {
    return TransportationBooking(
      transportBookingId: json['transportBookingId'] as int?,
      bookingId: json['bookingId'] as int?,
      transportId: json['transportId'] as int?,
      departureDate: json['departureDate'] != null
          ? DateTime.parse(json['departureDate'])
          : null,
      arrivalDate: json['arrivalDate'] != null
          ? DateTime.parse(json['arrivalDate'])
          : null,
      numSeats: json['numSeats'] as int?,
      status: json['status'] as String?,
      seatPlan: json['seatPlan'] as String?,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'transportBookingId': transportBookingId,
      'bookingId': bookingId,
      'transportId': transportId,
      'departureDate': departureDate?.toIso8601String(),
      'arrivalDate': arrivalDate?.toIso8601String(),
      'numSeats': numSeats,
      'status': status,
      'seatPlan': seatPlan,
    };
  }
}
