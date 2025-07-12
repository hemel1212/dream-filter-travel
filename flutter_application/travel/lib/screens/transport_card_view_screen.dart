import 'package:flutter/material.dart';
import 'package:travel/screens/add_transportation_booking_screen.dart';

import '../model/transportations.dart';

class TransportCardViewScreen extends StatelessWidget {
  final Transportations transport;

  const TransportCardViewScreen({super.key, required this.transport});

  @override
  Widget build(BuildContext context) {
    return Card(
      clipBehavior: Clip.antiAlias,
      elevation: 10,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          Container(
            height: 180,
            decoration: BoxDecoration(
              color: Colors.blue.shade50,
              borderRadius: const BorderRadius.only(
                topLeft: Radius.circular(20),
                topRight: Radius.circular(20),
              ),
            ),
            child: Center(
              child: Icon(
                Icons.directions_bus_filled,
                size: 100,
                color: Colors.blue.shade400,
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 16.0, vertical: 12),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,

              children: [
                Text(
                  transport.type,
                  style: const TextStyle(
                    fontSize: 22,
                    fontWeight: FontWeight.bold,
                    color: Colors.black87,
                  ),
                ),
                const SizedBox(height: 8),

                Row(
                  children: [

                    const Icon(Icons.business, size: 18, color: Colors.grey),
                    const SizedBox(width: 6),
                    Text(
                      'Provider: ${transport.provider}',
                      style: const TextStyle(fontSize: 16),
                    ),
                  ],
                ),
                const SizedBox(height: 6),
                Row(
                  children: [
                    const Icon(Icons.event_seat, size: 18, color: Colors.grey),
                    const SizedBox(width: 6),
                    Text(
                      'Seats: ${transport.availableSeat}',
                      style: const TextStyle(fontSize: 16),
                    ),
                  ],
                ),
                const SizedBox(height: 6),
                Row(
                  children: [
                    const Icon(
                      Icons.attach_money,
                      size: 18,
                      color: Colors.grey,
                    ),
                    const SizedBox(width: 6),
                    Text(
                      'Price: ${transport.price.toStringAsFixed(2)} Tk',
                      style: const TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.w500,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
          const Divider(thickness: 1),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 16.0, vertical: 10),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                OutlinedButton.icon(
                  icon: const Icon(Icons.info_outline),
                  label: const Text('Details'),
                  // onPressed: () => _onBookPressed(context),
                  onPressed: () {},
                ),
                ElevatedButton.icon(
                  icon: const Icon(Icons.flight_takeoff),
                  label: const Text('Book Now'),
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => AddTransportationBookingScreen(
                          type: transport.type,
                          transportId: transport.transportId ?? 0,
                        ),
                      ),
                    );
                  },
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.green.shade600,
                    padding: const EdgeInsets.symmetric(
                      horizontal: 20,
                      vertical: 12,
                    ),
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
