package com.example.travel.travel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    private Long hotelId;
    private String name;
    private String location;
    private Double pricePerNight;
    private Integer rating;
}
