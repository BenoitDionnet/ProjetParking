package projetmaven.com.xml2.model;

import java.util.ArrayList;
import java.util.List;
import projetmaven.com.xml2.model.Car;

import lombok.Data;

public @Data class Parking {
	
	private int id;
	private String name;
	private List<Car> cars = new ArrayList<Car>();
	
	public Parking(int id, String name, List<Car> cars) {
		super();
		this.id = id;
		this.name = name;
		this.cars = cars;
	}
	public Parking(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Parking() {
		
	}
	
	public void addCar(Car c) {
		this.cars.add(c);
	}
	
	
}