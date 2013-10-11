package com.example.medicinereminder;

public class Time {
	
	private int hour;
	private int minute;
	
	public Time(int hour, int minute){
		this.hour = hour;
		this.minute = minute;
	}
	
	public void setHour(int hour){
		this.hour = hour;
	}
	
	public int getHour(){
		return hour;
	}
	
	public void setMinute(int minute){
		this.minute = minute;
	}
	
	public int getMinute(){
		return minute;
	}
	
	public String toString(){
		return hour + ":" + minute;
	}
}
