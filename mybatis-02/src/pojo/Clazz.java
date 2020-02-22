package pojo;

import java.util.List;

public class Clazz {
	private Integer id; private String name; private List<Student> stus;
	public Clazz(Integer id, String name, List<Student> stus) {
		super();
		this.id = id;
		this.name = name;
		this.stus = stus;
	}
	public Clazz() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Clazz [id=" + id + ", name=" + name + ", stus=" + stus + "]";
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
	public List<Student> getStus() {
		return stus;
	}
	public void setStus(List<Student> stus) {
		this.stus = stus;
	}
}
