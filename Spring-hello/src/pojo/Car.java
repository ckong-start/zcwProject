package pojo;

public class Car {
	private Integer CarNo;
	private String  CarName;
	
	public void init() {
		System.out.println("对象被创建之后，初始化了 init()");
	}
	public void destory() {
		System.out.println("对象被销毁，destory()");
	}
	
	public Car(Integer carNo, String carName) {
		super();
		CarNo = carNo;
		CarName = carName;
	}
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCarNo() {
		return CarNo;
	}
	public void setCarNo(Integer carNo) {
		CarNo = carNo;
	}
	public String getCarName() {
		return CarName;
	}
	public void setCarName(String carName) {
		CarName = carName;
	}
	@Override
	public String toString() {
		return "Car [CarNo=" + CarNo + ", CarName=" + CarName + "]";
	}
	
}
