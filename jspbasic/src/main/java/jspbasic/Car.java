package jspbasic;

public class Car {
	private int yearNum;
	private String make;
	private String modelName;
	private int cc;
	private String color;
	private String price;
	
	public Car() {
		
	}
	
	

	public Car(int yearNum, String make, String modelName, int cc, String color, String price) {
		super();
		this.yearNum = yearNum;
		this.make = make;
		this.modelName = modelName;
		this.cc = cc;
		this.color = color;
		this.price = price;
	}



	public int getYearNum() {
		return yearNum;
	}

	public void setYearNum(int yearNum) {
		this.yearNum = yearNum;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [yearNum=" + yearNum + ", make=" + make + ", modelName=" + modelName + ", cc=" + cc + ", color="
				+ color + ", price=" + price + "]";
	}
	
	
}
