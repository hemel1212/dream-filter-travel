import 'package:flutter/material.dart';
import '../model/transportations.dart';
import '../services/api_service_transportations.dart';

class AddEditTransportationsScreen extends StatefulWidget {
  final Transportations? transportation;

  const AddEditTransportationsScreen({super.key, this.transportation});

  @override
  State<AddEditTransportationsScreen> createState() => _AddEditTransportationScreenState();
}

class _AddEditTransportationScreenState extends State<AddEditTransportationsScreen> {
  final _formKey = GlobalKey<FormState>();
  late TextEditingController _typeController;
  late TextEditingController _providerController;
  late TextEditingController _seatController;
  late TextEditingController _priceController;

  final ApiServiceTransportations _apiService = ApiServiceTransportations();

  @override
  void initState() {
    super.initState();
    _typeController = TextEditingController(text: widget.transportation?.type ?? '');
    _providerController = TextEditingController(text: widget.transportation?.provider ?? '');
    _seatController = TextEditingController(text: widget.transportation?.availableSeat.toString() ?? '0');
    _priceController = TextEditingController(text: widget.transportation?.price.toString() ?? '0.0');
  }

  @override
  void dispose() {
    _typeController.dispose();
    _providerController.dispose();
    _seatController.dispose();
    _priceController.dispose();
    super.dispose();
  }

  Future<void> _saveTransportation() async {
    if (_formKey.currentState!.validate()) {
      final transportation = Transportations(
        transportId: widget.transportation?.transportId,
        type: _typeController.text.trim(),
        provider: _providerController.text.trim(),
        availableSeat: int.tryParse(_seatController.text.trim()) ?? 0,
        price: double.tryParse(_priceController.text.trim()) ?? 0.0,
      );

      try {
        if (widget.transportation == null) {
          await _apiService.addTransportation(transportation);
        } else {
          await _apiService.updateTransportation(transportation);
        }
        Navigator.pop(context);
      } catch (e) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Error: $e')),
        );
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.transportation == null ? 'Add Transportation' : 'Edit Transportation'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: ListView(
            children: [
              TextFormField(
                controller: _typeController,
                decoration: const InputDecoration(labelText: 'Type'),
                validator: (value) => value!.isEmpty ? 'Enter type' : null,
              ),
              TextFormField(
                controller: _providerController,
                decoration: const InputDecoration(labelText: 'Provider'),
                validator: (value) => value!.isEmpty ? 'Enter provider' : null,
              ),
              TextFormField(
                controller: _seatController,
                keyboardType: TextInputType.number,
                decoration: const InputDecoration(labelText: 'Available Seats'),
                validator: (value) => value!.isEmpty ? 'Enter seat count' : null,
              ),
              TextFormField(
                controller: _priceController,
                keyboardType: TextInputType.number,
                decoration: const InputDecoration(labelText: 'Price'),
                validator: (value) => value!.isEmpty ? 'Enter price' : null,
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: _saveTransportation,
                child: const Text('Save'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
