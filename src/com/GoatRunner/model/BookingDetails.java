package com.GoatRunner.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class BookingDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7191278986779747863L;

	private int bookingId;

	private int cabId;

	private int driverId;

	private String driverName;

	private int studentId;

	private int distance;

	private String destination;

	private String source;

	private int notOfPassengers;

	private String estimatedTime;

	private Timestamp bookingTime;

	private Date bookingDate;

	private double sourceLatitude;

	private double sourceLongitude;

	private double destinationLatitude;

	private double destinationLongitude;
	
	private int bookingStatus;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getCabId() {
		return cabId;
	}

	public void setCabId(int cabId) {
		this.cabId = cabId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public int getStdentId() {
		return studentId;
	}

	public void setStdentId(int stdentId) {
		this.studentId = stdentId;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getNoofPassenges() {
		return notOfPassengers;
	}

	public void setNoofPassenges(int noofPassenges) {
		this.notOfPassengers = noofPassenges;
	}

	public String getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Timestamp getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Timestamp bookingTime) {
		this.bookingTime = bookingTime;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public double getSourceLatitude() {
		return sourceLatitude;
	}

	public void setSourceLatitude(double sourceLatitude) {
		this.sourceLatitude = sourceLatitude;
	}

	public double getSourceLongitude() {
		return sourceLongitude;
	}

	public void setSourceLongitude(double sourceLongitude) {
		this.sourceLongitude = sourceLongitude;
	}

	public double getDestinationLatitude() {
		return destinationLatitude;
	}

	public void setDestinationLatitude(double destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	public double getDestinationLongitude() {
		return destinationLongitude;
	}

	public void setDestinationLongitude(double destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}

	public int getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(int bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

}
