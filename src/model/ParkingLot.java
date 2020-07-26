package model;

import java.util.*;

import DAO.Database;

public class ParkingLot {
	String name;
	int idOwner;
	String district;
	String city;
	int maxCapacity;
	int capacity;
	int active;

	public ParkingLot(int idOwner, String name, String district, String city, int maxCapacity, int active) {
		this.idOwner = idOwner;
		this.name = name;
		this.district = district;
		this.city = city;
		this.maxCapacity = maxCapacity;
		this.active = active;
	}
	
	public void storeCarParkinglot(String state) {
		Database.storeCarParkinglot(state, this.name);
	}

	public boolean checkSlot() {
		if (capacity < this.getMaxCapacity()) {
			return true;
		}
		return false;
	}

	public void increaseCapacỉty() {
		this.capacity++;
	}

	public int getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getCapacity() {
		return capacity;
	}

}
