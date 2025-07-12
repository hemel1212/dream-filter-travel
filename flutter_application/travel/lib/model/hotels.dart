class Hotels {
  final int? hotelId;
  final String name;
  final String location;
  final double pricePerNight;
  final int rating;

  Hotels({
    this.hotelId,
    required this.name,
    required this.location,
    required this.pricePerNight,
    required this.rating,
  });

  factory Hotels.fromJson(Map<String, dynamic> json) {
    return Hotels(
      hotelId: json['hotelId'] as int?,
      name: json['name'] as String? ?? '',
      location: json['location'] as String? ?? '',
      pricePerNight: (json['pricePerNight'] as num?)?.toDouble() ?? 0.0,
      rating: json['rating'] as int? ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'hotelId': hotelId,
      'name': name,
      'location': location,
      'pricePerNight': pricePerNight,
      'rating': rating,
    };
  }
}
