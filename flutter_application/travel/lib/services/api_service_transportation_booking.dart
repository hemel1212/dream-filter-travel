import 'dart:convert';

import 'package:http/http.dart' as http;

import '../model/transportation_booking.dart';

class ApiServiceTransportationBooking {
  static const String baseUrl = 'http://192.168.0.220:8080';

  Future<void> addTransportationBooking(
    TransportationBooking transportationBooking,
  ) async {
    final response = await http.post(
      Uri.parse('$baseUrl/api/transport-bookings'),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(transportationBooking.toJson()),
    );
    if (response.statusCode != 201) {
      throw Exception('Failed to add transportation');
    }
  }

  Future<String> fetchUniqueSeatPlanByTransportId(int transportId) async {
    final response = await http.get(
      Uri.parse('$baseUrl/api/transport-bookings/seat-plans/$transportId'),
      headers: {'Content-Type': 'application/json'},
    );

    if (response.statusCode == 200) {
      return response.body; // e.g. "A1-true,A3-true,B4-true"
    } else {
      throw Exception('Failed to fetch seat plan');
    }
  }

}
