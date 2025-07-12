import 'package:flutter/material.dart';
import '../model/transport_booking.dart';
import '../services/api_service_transport_booking.dart';

class TransportBookingScreen extends StatefulWidget {
  const TransportBookingScreen({super.key});

  @override
  State<TransportBookingScreen> createState() => _TransportBookingScreenState();
}

class _TransportBookingScreenState extends State<TransportBookingScreen> {
  final ApiServiceTransportBooking _apiService = ApiServiceTransportBooking();
  late Future<List<TransportBooking>> _bookingFuture;

  @override
  void initState() {
    super.initState();
    _bookingFuture = _apiService.getAllBookings();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Transport Bookings')),
      body: FutureBuilder<List<TransportBooking>>(
        future: _bookingFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text('No transport bookings found.'));
          }

          final bookings = snapshot.data!;
          return ListView.builder(
            itemCount: bookings.length,
            itemBuilder: (context, index) {
              final booking = bookings[index];
              return Card(
                margin: const EdgeInsets.all(10),
                child: ListTile(
                  //title: Text('Booking ID: ${booking.transportBookingId}'),
                  subtitle: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text('Seats: ${booking.numSeats}'),
                      Text('Status: ${booking.status}'),
                      Text('Departure: ${booking.departureDate?.toLocal()}'),
                      Text('Arrival: ${booking.arrivalDate?.toLocal()}'),
                      Text('Seat Plan: ${booking.seatPlan ?? "No seat plan"}'),
                    ],
                  ),
                  trailing: IconButton(
                    icon: const Icon(Icons.download),
                    tooltip: 'Download Seat Plan',
                    onPressed: () {
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(
                          content: Text('Downloading seat plan for booking ID: ${booking.transportBookingId}'),
                          duration: const Duration(seconds: 2),
                        ),
                      );

                      // TODO: Add actual download logic if needed
                    },
                  ),
                ),
              );
            },
          );
        },
      ),
    );
  }
}
