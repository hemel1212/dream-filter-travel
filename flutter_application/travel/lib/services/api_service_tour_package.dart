import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart' as http;
import 'package:path/path.dart';

import '../model/tour_package.dart';

class ApiServiceTourPackage {
  static const String baseUrl = 'http://192.168.0.220:8080';

  // Get all tour packages
  Future<List<TourPackage>> getAllTourPackages() async {
    final response = await http.get(Uri.parse('$baseUrl/api/tour-packages'));
    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((json) => TourPackage.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load tour packages');
    }
  }

  // Create a new tour package
  Future<TourPackage> saveTourPackage(TourPackage package) async {
    final response = await http.post(
      Uri.parse('$baseUrl/api/tour-packages'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(package.toJson()),
    );
    if (response.statusCode == 200 || response.statusCode == 201) {
      return TourPackage.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to save tour package');
    }
  }

  // Update an existing tour package
  Future<TourPackage> updateTourPackage(int id, TourPackage package) async {
    final response = await http.put(
      Uri.parse('$baseUrl/api/tour-packages/$id'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(package.toJson()),
    );
    if (response.statusCode == 200) {
      return TourPackage.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to update tour package');
    }
  }

  // Delete a tour package
  Future<void> deleteTourPackage(int id) async {
    final response = await http.delete(Uri.parse('$baseUrl/api/tour-packages/$id'));
    if (response.statusCode != 204) {
      throw Exception('Failed to delete tour package');
    }
  }

  // Upload image for a tour package (if supported by backend)
  Future<String> uploadImage(int packageId, File image) async {
    var request = http.MultipartRequest(
      'POST',
      Uri.parse('$baseUrl/tour-packages/$packageId/upload'),
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
      return jsonResponse['url']; // Assuming {"url": "..."}
    } else {
      throw Exception('Failed to upload image');
    }
  }
}
