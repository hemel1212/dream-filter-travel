import 'dart:convert';
import 'package:http/http.dart' as http;
import '../model/registration.dart';

class ApiServiceCustomers {
  static const String baseUrl = 'http://192.168.0.220:8080';

  // Get all customers
  Future<List<Customer>> getAllCustomers() async {
    final response = await http.get(Uri.parse('$baseUrl/api/customers'));
    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((json) => Customer.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load customers');
    }
  }

  // Create new customer
  Future<Customer> saveCustomer(Customer customer) async {
    final response = await http.post(
      Uri.parse('$baseUrl/api/customers/register'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(customer.toJson()),
    );
    if (response.statusCode == 200 || response.statusCode == 201) {
      return Customer.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to save customer');
    }
  }

  // Update customer
  Future<Customer> updateCustomer(Customer customer) async {
    final response = await http.put(
      Uri.parse('$baseUrl/api/customers/register'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(customer.toJson()),
    );
    if (response.statusCode == 200) {
      return Customer.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to update customer');
    }
  }

  // Delete customer
  Future<void> deleteCustomer(int id) async {
    final response = await http.delete(Uri.parse('$baseUrl/api/customers/$id'));
    if (response.statusCode != 204) {
      throw Exception('Failed to delete customer');
    }
  }
}

