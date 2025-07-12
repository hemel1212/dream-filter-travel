import 'dart:convert';
import 'package:http/http.dart' as http;

import '../model/LoginDTO.dart';
import '../model/registration.dart';

class ApiServiceCustomer {
  static const String baseUrl = 'http://192.168.0.220:8080';


  Future<Customer> login(LoginDTO loginDTO) async {
    final response = await http.post(
      Uri.parse('$baseUrl/api/customers/login'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(loginDTO.toJson()),
    );

    if (response.statusCode == 200) {
      return Customer.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Login failed. Please check your credentials.');
    }
  }
}
