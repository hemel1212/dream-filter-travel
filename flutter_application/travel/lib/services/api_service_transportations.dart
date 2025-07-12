import 'dart:convert';
import 'package:http/http.dart' as http;
import '../model/transportations.dart';

class ApiServiceTransportations {
  static const String baseUrl = 'http://192.168.0.220:8080';

  Future<List<Transportations>> getAllTransportations() async {
    final response = await http.get(Uri.parse('$baseUrl/api/transports'));
    if (response.statusCode == 200) {
      final List data = json.decode(response.body);
      return data.map((e) => Transportations.fromJson(e)).toList();
    } else {
      throw Exception('Failed to load transportations');
    }
  }

  Future<void> addTransportation(Transportations transportation) async {
    final response = await http.post(
      Uri.parse('$baseUrl/api/transports'),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(transportation.toJson()),
    );
    if (response.statusCode != 200) {
      throw Exception('Failed to add transportation');
    }
  }

  Future<void> updateTransportation(Transportations transportation) async {
    final response = await http.put(
      Uri.parse('$baseUrl/api/transports/${transportation.transportId}'),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(transportation.toJson()),
    );
    if (response.statusCode != 200) {
      throw Exception('Failed to update transportation');
    }
  }

  Future<void> deleteTransportation(int id) async {
    final response = await http.delete(
      Uri.parse('$baseUrl/api/transports/$id'),
    );
    if (response.statusCode != 204) {
      throw Exception('Failed to delete transportation');
    }
  }
}
