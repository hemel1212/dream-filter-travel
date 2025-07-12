import 'package:flutter/material.dart';
import 'package:travel/model/transportations.dart';
import 'package:travel/screens/customer_login_screen.dart';
import 'package:travel/screens/registration_screen.dart';
import 'package:travel/screens/tour_package_card_view_screen.dart';
import 'package:travel/screens/tour_package_screen.dart';
import 'package:travel/screens/transport_booking_screen.dart';
import 'package:travel/screens/transport_card_view_screen.dart';
import 'package:travel/screens/tour_package_list.dart';
import 'package:travel/screens/transportations_screen.dart';
import 'package:travel/services/api_service_transportations.dart';

import 'hotel_list_screen.dart';
import 'hotels_screen.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final ApiServiceTransportations apiServiceTransportations =
      ApiServiceTransportations();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Travel Apps', style: TextStyle(fontSize: 30)),
        backgroundColor: Colors.teal,
      ),
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: 0,
        selectedItemColor: Colors.blue,
        // color of the selected item
        unselectedItemColor: Colors.grey,
        // color of unselected items
        backgroundColor: Colors.white,
        // background color of the bar
        type: BottomNavigationBarType.fixed,
        // to show all labels even with 4+ items
        items: [
          BottomNavigationBarItem(
            icon: Icon(Icons.car_crash_sharp),
            label: "Transportation",
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.tour_sharp),
            label: "Tour Package",
          ),
          BottomNavigationBarItem(icon: Icon(Icons.hotel), label: "Hotels"),
          BottomNavigationBarItem(icon: Icon(Icons.bookmark_add_outlined), label: "T.Bookings"),
          BottomNavigationBarItem(
            icon: Icon(Icons.app_registration),
            label: "Registration",
          ),
        ],
        onTap: (int index) {
          if (index == 0) {
             Navigator.push(context, MaterialPageRoute(builder: (context) => HomeScreen()));
          }
          if (index == 1) {
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => const TourPackageCardScreen(),
              ),
            );
          }

          if (index == 2) {
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => const HotelCardScreen(),
              ),
            );

            // Add navigation here
          }
          if (index == 3) {
            Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => const TransportBookingScreen(),
                ),
            );
          }
          if (index == 4) {
            // Add navigation here
          }
        },

      ),
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: [
            const DrawerHeader(
              decoration: BoxDecoration(color: Colors.blue),
              child: Text(
                'Menu',
                style: TextStyle(color: Colors.white, fontSize: 24),
              ),
            ),
            ListTile(
              leading: const Icon(Icons.tour_sharp),
              title: const Text('Tour Package'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => const TourPackageScreen(),
                  ),
                );
              },
            ),

            ListTile(
              leading: const Icon(Icons.emoji_transportation),
              title: const Text('Transportations'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => const TransportationsScreen(),
                  ),
                );
              },
            ),

            ListTile(
              leading: const Icon(Icons.hotel),
              title: const Text('Hotels'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => const HotelScreen()),
                );
              },
            ),

            ListTile(
              leading: const Icon(Icons.login),
              title: const Text('Log-in'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => const CustomerLoginScreen(),
                  ),
                );
              },
            ),

            ListTile(
              leading: const Icon(Icons.app_registration),
              title: const Text('Registration'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => const CustomersScreen(),
                  ),
                );
              },
            ),
          ],
        ),
      ),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: FutureBuilder<List<Transportations>>(
          future: apiServiceTransportations.getAllTransportations(),
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.waiting) {
              return const Center(child: CircularProgressIndicator());
            }
            if (snapshot.hasError) {
              return Center(child: Text('Error ${snapshot.error}'));
            }
            if (!snapshot.hasData) {
              return const Center(child: Text('No  data found'));
            }
            final transportations = snapshot.data!;
            return ListView.builder(
              itemCount: transportations.length,
              itemBuilder: (context, index) {
                final transport = transportations[index];
                return TransportCardViewScreen(transport: transport);
              },
            );
          },
        ),
      ),
    );
  }
}
