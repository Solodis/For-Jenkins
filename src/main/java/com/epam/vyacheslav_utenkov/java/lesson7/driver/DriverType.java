package com.epam.vyacheslav_utenkov.java.lesson7.driver;

public enum DriverType {
	FIREFOX("firefox"), 
	IE("internet explorer");
	
	private String driverName;
	DriverType (String driverName){
		this.driverName = driverName;
	}
	
	String getDriverName(){
		return driverName;
	}
}
