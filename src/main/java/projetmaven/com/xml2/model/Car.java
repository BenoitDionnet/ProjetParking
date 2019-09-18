package projetmaven.com.xml2.model;

import lombok.Data;

public @Data class Car {
	private int id;
	private String immat;
	private String model;
	private String color;
	
	public Car(int id, String immat, String model, String color) {
		super();
		this.id = id;
		this.immat = immat;
		this.model = model;
		this.color = color;
	}
	
	public Car() {
		
	}
	
}
