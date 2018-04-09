package com.GoatRunner.model;

public class Cab {
	private int cabno;
	private int driverid;
	private int current_capacity;
	private double latitude;
	private double longitude;

	public int getCabno() {
		return cabno;
	}

	public void setCabno(int cabno) {
		this.cabno = cabno;
	}

	public int getDriverid() {
		return driverid;
	}

	public void setDriverid(int driverid) {
		this.driverid = driverid;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getCurrent_capacity() {
		return current_capacity;
	}

	public void setCurrent_capacity(int current_capacity) {
		this.current_capacity = current_capacity;
	}

}
