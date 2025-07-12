import 'package:flutter/material.dart';
import 'package:travel/screens/add_edit_transportations_screen.dart';
import '../model/transportations.dart';
import '../services/api_service_transportations.dart';


class TransportationsScreen extends StatefulWidget {
  const TransportationsScreen({super.key});

  @override
  State<TransportationsScreen> createState() => _TransportationsScreenState();
}

class _TransportationsScreenState extends State<TransportationsScreen> {
  final ApiServiceTransportations _apiServiceTransportations = ApiServiceTransportations();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Transportation Management')),
      body: FutureBuilder<List<Transportations>>(
        future: _apiServiceTransportations.getAllTransportations(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          }
          if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text('No transportations found'));
          }

          final transportations = snapshot.data!;
          return ListView.builder(
            itemCount: transportations.length,
            itemBuilder: (context, index) {
              final transportation = transportations[index];
              return ListTile(
                leading: const Icon(Icons.directions_bus, size: 40),
                title: Text(transportation.type),
                subtitle: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text('Provider: ${transportation.provider}'),
                    Text('Seats: ${transportation.availableSeat}'),
                    Text('Price: \$${transportation.price.toStringAsFixed(2)}'),
                  ],
                ),
                trailing: IconButton(
                  icon: const Icon(Icons.delete),
                  onPressed: () async {
                    try {
                      await _apiServiceTransportations.deleteTransportation(transportation.transportId!);
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
                      builder: (context) => AddEditTransportationsScreen(transportation: transportation),
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
              builder: (context) => const AddEditTransportationsScreen(),
            ),
          ).then((_) => setState(() {}));
        },
        child: const Icon(Icons.add),
      ),
    );
  }
}
