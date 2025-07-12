import 'package:flutter/material.dart';

import '../model/tour_package.dart';
import '../services/api_service_tour_package.dart';
import 'add_edit_tour_package_screen.dart';

class TourPackageScreen extends StatefulWidget {
  const TourPackageScreen({super.key});

  @override
  State<TourPackageScreen> createState() => _TourPackageScreenState();
}

class _TourPackageScreenState extends State<TourPackageScreen> {
  final ApiServiceTourPackage _apiServiceTourPackage = ApiServiceTourPackage();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Tour Package Management')),
      body: FutureBuilder<List<TourPackage>>(
        future: _apiServiceTourPackage.getAllTourPackages(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          }
          if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text('No tour packages found'));
          }

          final packages = snapshot.data!;
          return ListView.builder(
            itemCount: packages.length,
            itemBuilder: (context, index) {
              final pkg = packages[index];
              return ListTile(
                leading: const Icon(Icons.card_travel, size: 40),
                title: Text(pkg.name),
                subtitle: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text('Destination: ${pkg.destination}'),
                    Text('Price: \$${pkg.price.toStringAsFixed(2)}'),
                    Text('Duration: ${pkg.durationDays} days'),
                  ],
                ),
                trailing: IconButton(
                  icon: const Icon(Icons.delete),
                  onPressed: () async {
                    try {
                      await _apiServiceTourPackage.deleteTourPackage(pkg.packageId!);
                      setState(() {}); // Refresh after deletion
                    } catch (e) {
                      ScaffoldMessenger.of(
                        context,
                      ).showSnackBar(SnackBar(content: Text('Error: $e')));
                    }
                  },
                ),
                onTap: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) =>
                          AddEditTourPackageScreen(package: pkg),
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
              builder: (context) => const AddEditTourPackageScreen(),
            ),
          ).then((_) => setState(() {}));
        },
        child: const Icon(Icons.add),
      ),
    );
  }
}
