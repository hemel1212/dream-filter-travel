class Customer {
  final int? customerId;
  final String name;
  final String email;
  final String passwordHash;
  final String phone;
  final String address;
  final DateTime? createdAt;

  // Optionally include booking list if needed
  // final List<Booking>? bookings;

  Customer({
    this.customerId,
    required this.name,
    required this.email,
    required this.passwordHash,
    required this.phone,
    required this.address,
    this.createdAt,
    // this.bookings,
  });

  factory Customer.fromJson(Map<String, dynamic> json) {
    return Customer(
      customerId: json['customerId'] as int?,
      name: json['name'] as String? ?? '',
      email: json['email'] as String? ?? '',
      passwordHash: json['passwordHash'] as String? ?? '',
      phone: json['phone'] as String? ?? '',
      address: json['address'] as String? ?? '',
      createdAt: json['createdAt'] != null
          ? DateTime.parse(json['createdAt'])
          : null,
      // bookings: json['bookings'] != null
      //     ? (json['bookings'] as List)
      //         .map((b) => Booking.fromJson(b))
      //         .toList()
      //     : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'customerId': customerId,
      'name': name,
      'email': email,
      'passwordHash': passwordHash,
      'phone': phone,
      'address': address,
      'createdAt': createdAt?.toIso8601String(),
      // 'bookings': bookings?.map((b) => b.toJson()).toList(),
    };
  }
}
