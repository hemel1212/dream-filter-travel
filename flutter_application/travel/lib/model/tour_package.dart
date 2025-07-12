class TourPackage {
  final int? packageId;
  final String name;
  final String description;
  final double price;
  final int durationDays;
  final String destination;
  final DateTime createdAt;

  TourPackage({
    this.packageId,
    required this.name,
    required this.description,
    required this.price,
    required this.durationDays,
    required this.destination,
    required this.createdAt,
  });

  factory TourPackage.fromJson(Map<String, dynamic> json) {
    return TourPackage(
      packageId: json['packageId'] as int?,
      name: json['name'] as String? ?? '',
      description: json['description'] as String? ?? '',
      price: (json['price'] as num?)?.toDouble() ?? 0.0,
      durationDays: json['durationDays'] as int? ?? 0,
      destination: json['destination'] as String? ?? '',
      createdAt: DateTime.tryParse(json['createdAt'] ?? '') ?? DateTime.now(),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'packageId': packageId,
      'name': name,
      'description': description,
      'price': price,
      'durationDays': durationDays,
      'destination': destination,
      'createdAt': createdAt.toIso8601String(),
    };
  }
}
