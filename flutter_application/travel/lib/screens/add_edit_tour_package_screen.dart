import 'package:flutter/material.dart';

import '../model/tour_package.dart';
import '../services/api_service_tour_package.dart';

class AddEditTourPackageScreen extends StatefulWidget {
  final TourPackage? package;

  const AddEditTourPackageScreen({super.key, this.package});

  @override
  State<AddEditTourPackageScreen> createState() => _AddEditTourPackageScreenState();
}

class _AddEditTourPackageScreenState extends State<AddEditTourPackageScreen> {
  final _formKey = GlobalKey<FormState>();

  final _nameController = TextEditingController();
  final _descriptionController = TextEditingController();
  final _priceController = TextEditingController();
  final _durationController = TextEditingController();
  final _destinationController = TextEditingController();

  final ApiServiceTourPackage _apiServiceTourPackage = ApiServiceTourPackage();

  @override
  void initState() {
    super.initState();
    if (widget.package != null) {
      _nameController.text = widget.package!.name;
      _descriptionController.text = widget.package!.description;
      _priceController.text = widget.package!.price.toString();
      _durationController.text = widget.package!.durationDays.toString();
      _destinationController.text = widget.package!.destination;
    }
  }

  @override
  void dispose() {
    _nameController.dispose();
    _descriptionController.dispose();
    _priceController.dispose();
    _durationController.dispose();
    _destinationController.dispose();
    super.dispose();
  }

  Future<void> _savePackage() async {
    if (_formKey.currentState!.validate()) {
      final package = TourPackage(
        packageId: widget.package?.packageId,
        name: _nameController.text,
        description: _descriptionController.text,
        price: double.parse(_priceController.text),
        durationDays: int.parse(_durationController.text),
        destination: _destinationController.text,
        createdAt: widget.package?.createdAt ?? DateTime.now(),
      );

      try {
        if (widget.package == null) {
          await _apiServiceTourPackage.saveTourPackage(package);
        } else {
          await _apiServiceTourPackage.updateTourPackage(widget.package!.packageId!, package);
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
    final isEditing = widget.package != null;

    return Scaffold(
      appBar: AppBar(
        title: Text(isEditing ? 'Edit Tour Package' : 'Add Tour Package'),
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
                  decoration: const InputDecoration(labelText: 'Package Name'),
                  validator: (value) => value!.isEmpty ? 'Enter package name' : null,
                ),
                TextFormField(
                  controller: _descriptionController,
                  decoration: const InputDecoration(labelText: 'Description'),
                  validator: (value) => value!.isEmpty ? 'Enter description' : null,
                ),
                TextFormField(
                  controller: _priceController,
                  decoration: const InputDecoration(labelText: 'Price'),
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
                  controller: _durationController,
                  decoration: const InputDecoration(labelText: 'Duration (in days)'),
                  keyboardType: TextInputType.number,
                  validator: (value) {
                    if (value!.isEmpty) return 'Enter duration';
                    if (int.tryParse(value) == null) {
                      return 'Enter a valid number of days';
                    }
                    return null;
                  },
                ),
                TextFormField(
                  controller: _destinationController,
                  decoration: const InputDecoration(labelText: 'Destination'),
                  validator: (value) => value!.isEmpty ? 'Enter destination' : null,
                ),
                const SizedBox(height: 20),
                ElevatedButton(
                  onPressed: _savePackage,
                  child: Text(isEditing ? 'Update Package' : 'Add Package'),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
