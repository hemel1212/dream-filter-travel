package com.example.travel.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travel.travel.entity.Itinerary;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long>  {

}
