import 'package:flutter/material.dart';
import '../model/registration.dart';
import '../services/api_service_registration.dart';
import 'add_edit_registration_screen.dart';

class CustomersScreen extends StatefulWidget {
  const CustomersScreen({super.key});

  @override
  State<CustomersScreen> createState() => _CustomersScreenState();
}

class _CustomersScreenState extends State<CustomersScreen> {
  final ApiServiceCustomers _apiServiceCustomers = ApiServiceCustomers();

  Future<void> _refreshCustomers() async {
    setState(() {});
  }

  Future<void> _deleteCustomer(int? customerId) async {
    if (customerId == null) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Invalid customer ID')),
      );
      return;
    }

    try {
      await _apiServiceCustomers.deleteCustomer(customerId);
      _refreshCustomers();
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Error: $e')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Customer Management')),
      body: FutureBuilder<List<Customer>>(
        future: _apiServiceCustomers.getAllCustomers(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          }
          if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          }
          if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text('No customers found'));
          }

          final customers = snapshot.data!;
          return RefreshIndicator(
            onRefresh: _refreshCustomers,
            child: ListView.builder(
              itemCount: customers.length,
              itemBuilder: (context, index) {
                final customer = customers[index];
                return Card(
                  margin: const EdgeInsets.symmetric(horizontal: 12, vertical: 6),
                  child: ListTile(
                    leading: const Icon(Icons.person, size: 40),
                    title: Text(customer.name),
                    subtitle: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text('Email: ${customer.email}'),
                        Text('Phone: ${customer.phone}'),
                        Text('Address: ${customer.address}'),
                        if (customer.createdAt != null)
                          Text(
                            'Created: ${customer.createdAt!.toLocal().toIso8601String().split("T").first}',
                            style: const TextStyle(fontSize: 12, color: Colors.grey),
                          ),
                      ],
                    ),
                    isThreeLine: true,
                    trailing: IconButton(
                      icon: const Icon(Icons.delete, color: Colors.red),
                      onPressed: () => _deleteCustomer(customer.customerId),
                    ),
                    onTap: () async {
                      await Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => AddEditCustomerScreen(customer: customer),
                        ),
                      );
                      _refreshCustomers();
                    },
                  ),
                );
              },
            ),
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          await Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => const AddEditCustomerScreen(),
            ),
          );
          _refreshCustomers();
        },
        child: const Icon(Icons.add),
      ),
    );
  }
}
