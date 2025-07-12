import 'package:flutter/material.dart';
import '../model/registration.dart';
import '../services/api_service_registration.dart';

class AddEditCustomerScreen extends StatefulWidget {
  final Customer? customer;

  const AddEditCustomerScreen({super.key, this.customer});

  @override
  State<AddEditCustomerScreen> createState() => _AddEditCustomerScreenState();
}

class _AddEditCustomerScreenState extends State<AddEditCustomerScreen> {
  final _formKey = GlobalKey<FormState>();

  final _nameController = TextEditingController();
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();
  final _phoneController = TextEditingController();
  final _addressController = TextEditingController();

  final ApiServiceCustomers _apiServiceCustomers = ApiServiceCustomers();

  @override
  void initState() {
    super.initState();
    if (widget.customer != null) {
      _nameController.text = widget.customer!.name;
      _emailController.text = widget.customer!.email;
      _passwordController.text = widget.customer!.passwordHash;
      _phoneController.text = widget.customer!.phone;
      _addressController.text = widget.customer!.address;
    }
  }

  @override
  void dispose() {
    _nameController.dispose();
    _emailController.dispose();
    _passwordController.dispose();
    _phoneController.dispose();
    _addressController.dispose();
    super.dispose();
  }

  Future<void> _saveCustomer() async {
    if (_formKey.currentState!.validate()) {
      final customer = Customer(
        customerId: widget.customer?.customerId,
        name: _nameController.text,
        email: _emailController.text,
        passwordHash: _passwordController.text,
        phone: _phoneController.text,
        address: _addressController.text,
        createdAt: widget.customer?.createdAt, // preserve if already exists
      );

      try {
        if (widget.customer == null) {
          await _apiServiceCustomers.saveCustomer(customer);
        } else {
          await _apiServiceCustomers.updateCustomer(customer);
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
    final isEdit = widget.customer != null;

    return Scaffold(
      appBar: AppBar(
        title: Text(isEdit ? 'Edit Customer' : 'Add Customer'),
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
                  decoration: const InputDecoration(labelText: 'Name'),
                  validator: (value) =>
                  value!.isEmpty ? 'Enter name' : null,
                ),
                TextFormField(
                  controller: _emailController,
                  decoration: const InputDecoration(labelText: 'Email'),
                  validator: (value) =>
                  value!.isEmpty ? 'Enter email' : null,
                ),
                TextFormField(
                  controller: _passwordController,
                  decoration: const InputDecoration(labelText: 'Password'),
                  obscureText: true,
                  validator: (value) =>
                  value!.isEmpty ? 'Enter password' : null,
                ),
                TextFormField(
                  controller: _phoneController,
                  decoration: const InputDecoration(labelText: 'Phone'),
                  validator: (value) =>
                  value!.isEmpty ? 'Enter phone number' : null,
                ),
                TextFormField(
                  controller: _addressController,
                  decoration: const InputDecoration(labelText: 'Address'),
                  validator: (value) =>
                  value!.isEmpty ? 'Enter address' : null,
                ),
                if (isEdit && widget.customer?.createdAt != null)
                  Padding(
                    padding: const EdgeInsets.only(top: 16.0),
                    child: Text(
                      'Created At: ${widget.customer!.createdAt!.toLocal().toIso8601String().split('T').first}',
                      style: const TextStyle(fontSize: 14, color: Colors.grey),
                    ),
                  ),
                const SizedBox(height: 20),
                ElevatedButton(
                  onPressed: _saveCustomer,
                  child: Text(isEdit ? 'Update Customer' : 'Add Customer'),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
