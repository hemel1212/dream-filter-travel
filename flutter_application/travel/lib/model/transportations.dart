class Transportations {
  final int? transportId;
  final String type; // Bus, Train, Flight.....
  final String provider;
  final int availableSeat;
  final double price;

  Transportations({
    this.transportId,
    required this.type,
    required this.provider,
    required this.availableSeat,
    required this.price,
  });

  factory Transportations.fromJson(Map<String, dynamic> json) {
    return Transportations(
      transportId: json['transportId'] as int?,
      type: json['type'] as String? ?? '',
      provider: json['provider'] as String? ?? '',
      availableSeat: json['availableSeat'] as int? ?? 0,
      price: (json['price'] as num?)?.toDouble() ?? 0.0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'transportId': transportId,
      'type': type,
      'provider': provider,
      'availableSeat': availableSeat,
      'price': price,
    };
  }
}
