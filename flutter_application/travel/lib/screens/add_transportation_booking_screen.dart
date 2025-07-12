import 'package:flutter/material.dart';
import 'package:travel/services/api_service_transportation_booking.dart';

import '../model/transportation_booking.dart';

class AddTransportationBookingScreen extends StatefulWidget {
  final TransportationBooking? booking;
  final String type;
  final int transportId;

  const AddTransportationBookingScreen({
    super.key,
    this.booking,
    required this.type,
    required this.transportId,
  });

  @override
  State<AddTransportationBookingScreen> createState() =>
      _AddTransportationBookingScreenState();
}

class _AddTransportationBookingScreenState
    extends State<AddTransportationBookingScreen> {
  final _formKey = GlobalKey<FormState>();
  final ApiServiceTransportationBooking _transportationBooking =
      ApiServiceTransportationBooking();

  late TextEditingController _bookingIdController;
  late TextEditingController _transportIdController;
  late TextEditingController _numSeatsController;
  late TextEditingController _statusController;

  DateTime? _departureDate;
  DateTime? _arrivalDate;

  final List<String> seatLabels = [
    'A1',
    'A2',
    'A3',
    'A4',
    'B1',
    'B2',
    'B3',
    'B4',
    'C1',
    'C2',
    'C3',
    'C4',
    'D1',
    'D2',
    'D3',
    'D4',
    'E1',
    'E2',
    'E3',
    'E4',
    'F1',
    'F2',
    'F3',
    'F4',
    'G1',
    'G2',
    'G3',
    'G4',
  ];

  final Set<String> selectedSeats = {};
  Set<String> bookedSeats = {};

  String get selectedSeatsString =>
      selectedSeats.map((seat) => '$seat-true').join(',');

  Future<void> fetchBookedSeats() async {
    try {
      final seats = await _transportationBooking
          .fetchUniqueSeatPlanByTransportId(
            widget.transportId,
          ); // must be non-null
      setState(() {
        bookedSeats = seats
            .split(",")
            .map((e) => e.trim().toUpperCase())
            .toSet();
      });
    } catch (e) {
      print('Failed to load booked seats: $e');
    }
  }

  @override
  void initState() {
    super.initState();
    fetchBookedSeats();

    _bookingIdController = TextEditingController(
      text: widget.booking?.bookingId?.toString() ?? '',
    );

    _transportIdController = TextEditingController(
      text: widget.transportId.toString(),
    );

    _numSeatsController = TextEditingController(
      text: widget.booking?.numSeats?.toString() ?? '',
    );

    _statusController = TextEditingController(
      text: widget.booking?.status ?? '',
    );

    _departureDate = widget.booking?.departureDate;
    _arrivalDate = widget.booking?.arrivalDate;
  }

  @override
  void dispose() {
    _bookingIdController.dispose();
    _transportIdController.dispose();
    _numSeatsController.dispose();
    _statusController.dispose();
    super.dispose();
  }

  Future<void> _selectDate(BuildContext context, bool isDeparture) async {
    final picked = await showDatePicker(
      context: context,
      initialDate: isDeparture
          ? (_departureDate ?? DateTime.now())
          : (_arrivalDate ?? DateTime.now()),
      firstDate: DateTime(2020),
      lastDate: DateTime(2100),
    );

    if (picked != null) {
      setState(() {
        if (isDeparture) {
          _departureDate = picked;
        } else {
          _arrivalDate = picked;
        }
      });
    }
  }

  void _saveForm() {
    if (_formKey.currentState!.validate()) {
      final newBooking = TransportationBooking(
        transportBookingId: widget.booking?.transportBookingId,
        bookingId: null,
        transportId: int.tryParse(_transportIdController.text),
        departureDate: _departureDate,
        arrivalDate: _arrivalDate,
        numSeats: int.tryParse(_numSeatsController.text),
        status: _statusController.text.trim(),
        seatPlan: selectedSeatsString,
      );

      _transportationBooking.addTransportationBooking(newBooking);

      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Booking saved successfully!'),
          backgroundColor: Colors.green,
          duration: Duration(seconds: 2),
        ),
      );
      Navigator.pop(context, newBooking);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          widget.booking == null
              ? 'Add Transport Booking'
              : 'Edit Transport Booking',
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: ListView(
            children: [
              TextFormField(
                controller: _bookingIdController,
                decoration: const InputDecoration(labelText: 'Booking ID'),
                keyboardType: TextInputType.number,
                validator: (value) =>
                    value!.isEmpty ? 'Please enter Booking ID' : null,
              ),
              TextFormField(
                controller: _transportIdController,
                decoration: const InputDecoration(labelText: 'Transport ID'),
                keyboardType: TextInputType.number,
                validator: (value) =>
                    value!.isEmpty ? 'Please enter Transport ID' : null,
              ),
              const SizedBox(height: 16),
              Row(
                children: [
                  Expanded(
                    child: Text(
                      _departureDate == null
                          ? 'Select Departure Date'
                          : 'Departure: ${_departureDate!.toLocal().toString().split(' ')[0]}',
                    ),
                  ),
                  IconButton(
                    icon: const Icon(Icons.calendar_today),
                    onPressed: () => _selectDate(context, true),
                  ),
                ],
              ),
              Row(
                children: [
                  Expanded(
                    child: Text(
                      _arrivalDate == null
                          ? 'Select Arrival Date'
                          : 'Arrival: ${_arrivalDate!.toLocal().toString().split(' ')[0]}',
                    ),
                  ),
                  IconButton(
                    icon: const Icon(Icons.calendar_today),
                    onPressed: () => _selectDate(context, false),
                  ),
                ],
              ),
              TextFormField(
                controller: _numSeatsController,
                decoration: const InputDecoration(labelText: 'Number of Seats'),
                keyboardType: TextInputType.number,
                validator: (value) =>
                    value!.isEmpty ? 'Please enter seat count' : null,
              ),
              if (widget.type.toLowerCase() == 'bus') ...[
                const SizedBox(height: 20),
                Text(
                  'Select Seats:',
                  style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                ),
                const SizedBox(height: 10),
                GridView.builder(
                  shrinkWrap: true,
                  physics: NeverScrollableScrollPhysics(),
                  itemCount: seatLabels.length,
                  gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                    crossAxisCount: 4,
                    mainAxisSpacing: 8,
                    crossAxisSpacing: 8,
                    childAspectRatio: 1.5,
                  ),
                  itemBuilder: (context, index) {
                    final seat = seatLabels[index];
                    final isSelected = selectedSeats.contains(seat);
                    final isBooked = bookedSeats.contains("$seat-TRUE");

                    return GestureDetector(
                      onTap: isBooked
                          ? null // ❌ Disable booking if seat is already booked
                          : () {
                              setState(() {
                                if (isSelected) {
                                  selectedSeats.remove(seat);
                                } else {
                                  selectedSeats.add(seat);
                                }
                              });
                            },
                      child: Container(
                        alignment: Alignment.center,
                        decoration: BoxDecoration(
                          color: isBooked
                              ? Colors.red[300]
                              : isSelected
                              ? Colors.green
                              : Colors.grey[300],
                          borderRadius: BorderRadius.circular(8),
                          border: Border.all(color: Colors.black),
                        ),
                        child: Text(
                          seat,
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            color: isBooked
                                ? Colors.white
                                : isSelected
                                ? Colors.white
                                : Colors.black,
                          ),
                        ),
                      ),
                    );
                  },
                ),
                const SizedBox(height: 16),
                Text(
                  'Selected: $selectedSeatsString',
                  style: const TextStyle(
                    fontSize: 14,
                    fontStyle: FontStyle.italic,
                  ),
                ),
              ],
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: _saveForm,
                child: const Text('Save Booking'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
