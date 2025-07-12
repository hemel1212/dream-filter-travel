import 'package:flutter/material.dart';

import '../model/hotels.dart';
import '../services/api_service_hotels.dart';

class AddEditHotelScreen extends StatefulWidget {
  final Hotels? hotel;

  const AddEditHotelScreen({super.key, this.hotel});

  @override
  State<AddEditHotelScreen> createState() => _AddEditHotelScreenState();
}

class _AddEditHotelScreenState extends State<AddEditHotelScreen> {
  final _formKey = GlobalKey<FormState>();

  final _nameController = TextEditingController();
  final _locationController = TextEditingController();
  final _priceController = TextEditingController();
  final _ratingController = TextEditingController();

  final ApiServiceHotels _apiServiceHotels = ApiServiceHotels();

  @override
  void initState() {
    super.initState();
    if (widget.hotel != null) {
      _nameController.text = widget.hotel!.name;
      _locationController.text = widget.hotel!.location;
      _priceController.text = widget.hotel!.pricePerNight.toString();
      _ratingController.text = widget.hotel!.rating.toString();
    }
  }

  @override
  void dispose() {
    _nameController.dispose();
    _locationController.dispose();
    _priceController.dispose();
    _ratingController.dispose();
    super.dispose();
  }

  Future<void> _saveHotel() async {
    if (_formKey.currentState!.validate()) {
      final hotel = Hotels(
        hotelId: widget.hotel?.hotelId,
        name: _nameController.text,
        location: _locationController.text,
        pricePerNight: double.parse(_priceController.text),
        rating: int.parse(_ratingController.text),
      );

      try {
        if (widget.hotel == null) {
          await _apiServiceHotels.saveHotel(hotel);
        } else {
          await _apiServiceHotels.updateHotel(widget.hotel!.hotelId!, hotel);
        }

        Navigator.pop(context, true);
      } catch (e) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Error: ${e.toString()}')),
        );
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.hotel == null ? 'Add Hotel' : 'Edit Hotel'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                TextFormField(
                  controller: _nameController,
                  decoration: const InputDecoration(labelText: 'Hotel Name'),
                  validator: (value) =>
                  value!.isEmpty ? 'Enter hotel name' : null,
                ),
                TextFormField(
                  controller: _locationController,
                  decoration: const InputDecoration(labelText: 'Location'),
                  validator: (value) =>
                  value!.isEmpty ? 'Enter location' : null,
                ),
                TextFormField(
                  controller: _priceController,
                  decoration:
                  const InputDecoration(labelText: 'Price per Night'),
                  keyboardType: TextInputType.number,
                  validator: (value) {
                    if (value!.isEmpty) return 'Enter price';
                    if (double.tryParse(value) == null) {
                      return 'Enter a valid number';
                    }
                    return null;
                  },
                ),
                TextFormField(
                  controller: _ratingController,
                  decoration: const InputDecoration(labelText: 'Rating (0–5)'),
                  keyboardType: TextInputType.number,
                  validator: (value) {
                    if (value!.isEmpty) return 'Enter rating';
                    final rating = int.tryParse(value);
                    if (rating == null || rating < 0 || rating > 5) {
                      return 'Enter a rating between 0 and 5';
                    }
                    return null;
                  },
                ),
                const SizedBox(height: 20),
                ElevatedButton(
                  onPressed: _saveHotel,
                  child:
                  Text(widget.hotel == null ? 'Add Hotel' : 'Update Hotel'),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
