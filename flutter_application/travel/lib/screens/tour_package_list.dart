import 'package:flutter/material.dart';
import '../model/tour_package.dart';
import '../services/api_service_tour_package.dart';

class TourPackageCardScreen extends StatefulWidget {
  const TourPackageCardScreen({super.key});

  @override
  State<TourPackageCardScreen> createState() => _TourPackageCardScreenState();
}

class _TourPackageCardScreenState extends State<TourPackageCardScreen> {
  final ApiServiceTourPackage _apiServiceTourPackage = ApiServiceTourPackage();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Tour Packages'),
        backgroundColor: Colors.teal,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: FutureBuilder<List<TourPackage>>(
          future: _apiServiceTourPackage.getAllTourPackages(),
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.waiting) {
              return const Center(child: CircularProgressIndicator());
            }
            if (snapshot.hasError) {
              return Center(child: Text('Error: ${snapshot.error}'));
            }
            if (!snapshot.hasData || snapshot.data!.isEmpty) {
              return const Center(child: Text('No tour packages found'));
            }

            final packages = snapshot.data!;
            return ListView.builder(
              itemCount: packages.length,
              itemBuilder: (context, index) {
                final pkg = packages[index];
                return Card(
                  clipBehavior: Clip.antiAlias,
                  elevation: 10,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(20),
                  ),
                  margin: const EdgeInsets.only(bottom: 16),
                  child: Column(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      Container(
                        height: 180,
                        decoration: BoxDecoration(
                          color: Colors.orange.shade50,
                          borderRadius: const BorderRadius.only(
                            topLeft: Radius.circular(20),
                            topRight: Radius.circular(20),
                          ),
                        ),
                        child: Center(
                          child: Icon(
                            Icons.card_travel,
                            size: 100,
                            color: Colors.orange.shade400,
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.symmetric(
                            horizontal: 16.0, vertical: 12),
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(
                              pkg.name,
                              style: const TextStyle(
                                fontSize: 22,
                                fontWeight: FontWeight.bold,
                                color: Colors.black87,
                              ),
                            ),
                            const SizedBox(height: 8),
                            Row(
                              children: [
                                const Icon(Icons.location_on,
                                    size: 18, color: Colors.grey),
                                const SizedBox(width: 6),
                                Text(
                                  'Destination: ${pkg.destination}',
                                  style: const TextStyle(fontSize: 16),
                                ),
                              ],
                            ),
                            const SizedBox(height: 6),
                            Row(
                              children: [
                                const Icon(Icons.calendar_today,
                                    size: 18, color: Colors.grey),
                                const SizedBox(width: 6),
                                Text(
                                  'Duration: ${pkg.durationDays} days',
                                  style: const TextStyle(fontSize: 16),
                                ),
                              ],
                            ),
                            const SizedBox(height: 6),
                            Row(
                              children: [
                                const Icon(Icons.attach_money,
                                    size: 18, color: Colors.grey),
                                const SizedBox(width: 6),
                                Text(
                                  'Price: ${pkg.price.toStringAsFixed(2)} Tk',
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
                        padding: const EdgeInsets.symmetric(
                            horizontal: 16.0, vertical: 10),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.end,
                          children: [
                            ElevatedButton.icon(
                              icon: const Icon(Icons.book_online),
                              label: const Text('Book Now'),
                              onPressed: () {
                                ScaffoldMessenger.of(context).showSnackBar(
                                  SnackBar(
                                    content: Text(
                                        'Booking for ${pkg.name} started...'),
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
              },
            );
          },
        ),
      ),
    );
  }
}
