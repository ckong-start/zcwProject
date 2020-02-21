package pojo;

public class Car {
	private String name;
	private String carNo;
	
	public String noStaticFun() {
		return "四轮车";
	}
	public static String staticFun() {
		return "五轮车";
	}
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Car(String name, String carNo) {
		super();
		this.name = name;
		this.carNo = carNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@Override
	public String toString() {
		return "Car [name=" + name + ", carNo=" + carNo + "]";
	}
	
}
