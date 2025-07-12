package com.example.travel.travel.DTO;

import lombok.Data;

@Data
public class ItineraryDTO {
    private Long packageId;
    private Integer dayNumber;
    private String description;
    private String location;
	public Long getPackageId() {
		return packageId;
	}
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	public Integer getDayNumber() {
		return dayNumber;
	}
	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
    
    
    
}
