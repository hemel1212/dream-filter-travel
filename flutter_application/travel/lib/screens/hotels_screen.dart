import 'package:flutter/material.dart';

import '../model/hotels.dart';
import '../services/api_service_hotels.dart';
import 'add_edit_hotel_screen.dart';


class HotelScreen extends StatefulWidget {
  const HotelScreen({super.key});

  @override
  State<HotelScreen> createState() => _HotelScreenState();
}

class _HotelScreenState extends State<HotelScreen> {
  final ApiServiceHotels _apiServiceHotels = ApiServiceHotels();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Hotel Management')),
      body: FutureBuilder<List<Hotels>>(
        future: _apiServiceHotels.getAllHotels(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          }
          if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text('No hotels found'));
          }

          final hotels = snapshot.data!;
          return ListView.builder(
            itemCount: hotels.length,
            itemBuilder: (context, index) {
              final hotel = hotels[index];
              return ListTile(
                leading: const Icon(Icons.hotel, size: 40),
                title: Text(hotel.name),
                subtitle: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text('Location: ${hotel.location}'),
                    Text('Price: \$${hotel.pricePerNight.toStringAsFixed(2)}'),
                    Text('Rating: ${hotel.rating}★'),
                  ],
                ),
                trailing: IconButton(
                  icon: const Icon(Icons.delete),
                  onPressed: () async {
                    try {
                      await _apiServiceHotels.deleteHotel(hotel.hotelId!);
                      setState(() {}); // Refresh list
                    } catch (e) {
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text('Error: $e')),
                      );
                    }
                  },
                ),
                onTap: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => AddEditHotelScreen(hotel: hotel),
                    ),
                  ).then((_) => setState(() {}));
                },
              );
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => const AddEditHotelScreen(),
            ),
          ).then((_) => setState(() {}));
        },
        child: const Icon(Icons.add),
      ),
    );
  }
}
