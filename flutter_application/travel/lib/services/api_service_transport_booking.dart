import 'dart:convert';
import 'package:http/http.dart' as http;
import '../model/transport_booking.dart';

class ApiServiceTransportBooking {
  static const String baseUrl = 'http://192.168.0.220:8080'; // replace with your backend address

  Future<List<TransportBooking>> getAllBookings() async {
    final response = await http.get(Uri.parse('$baseUrl/api/transports/all/bookings'));

    if (response.statusCode == 200) {
      List jsonList = jsonDecode(response.body);
      var list = jsonList.map((json) => TransportBooking.fromJson(json)).toList();
      print(list);
      return list;
    } else {
      throw Exception('Failed to load transport bookings');
    }
  }
}
