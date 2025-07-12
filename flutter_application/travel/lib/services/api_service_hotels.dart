import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart' as http;
import 'package:path/path.dart';

import '../model/hotels.dart';

class ApiServiceHotels {
  static const String baseUrl = 'http://192.168.0.220:8080';

  // Get all hotels
  Future<List<Hotels>> getAllHotels() async {
    final response = await http.get(Uri.parse('$baseUrl/api/hotels'));
    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((json) => Hotels.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load hotels');
    }
  }

  // Create new hotel
  Future<Hotels> saveHotel(Hotels hotel) async {
    final response = await http.post(
      Uri.parse('$baseUrl/api/hotels'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(hotel.toJson()),
    );
    if (response.statusCode == 200 || response.statusCode == 201) {
      return Hotels.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to save hotel');
    }
  }

  // Update hotel
  Future<Hotels> updateHotel(int id, Hotels hotel) async {
    final response = await http.put(
      Uri.parse('$baseUrl/api/hotels/$id'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(hotel.toJson()),
    );
    if (response.statusCode == 200) {
      return Hotels.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to update hotel');
    }
  }

  // Delete hotel
  Future<void> deleteHotel(int id) async {
    final response = await http.delete(Uri.parse('$baseUrl/api/hotels/$id'));
    if (response.statusCode != 200) {
      throw Exception('Failed to delete hotel');
    }
  }

  // Upload hotel image (if backend supports this endpoint)
  Future<String> uploadImage(int hotelId, File image) async {
    var request = http.MultipartRequest(
      'POST',
      Uri.parse('$baseUrl/api/hotels/$hotelId/upload'),
    );

    request.files.add(
      await http.MultipartFile.fromPath(
        'file',
        image.path,
        filename: basename(image.path),
      ),
    );

    final response = await request.send();
    if (response.statusCode == 200) {
      final responseBody = await response.stream.bytesToString();
      final jsonResponse = jsonDecode(responseBody);
      return jsonResponse['url']; // Assumes server returns {"url": "..."}
    } else {
      throw Exception('Failed to upload image');
    }
  }
}
