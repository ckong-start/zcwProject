package pojo;

import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Person {
	private Integer id;
	private String name;
	private Car car;
	private List<String> list;
	private Map<String, Object> map;
	private Properties pros;
	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public Properties getPros() {
		return pros;
	}
	public void setPros(Properties pros) {
		this.pros = pros;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public Person(Integer id, String name, Car car) {
		super();
		this.id = id;
		this.name = name;
		this.car = car;
	}
	public Person(Car car) {
		
		this.car = car;
	}
	public Person(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", car=" + car + ", list=" + list + ", map=" + map + ", pros="
				+ pros + "]";
	}
	
	
}
