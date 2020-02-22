package pojo;

public class PersonFactory {
	public static Person createPerson() {
		return new Person(13, "catalina", new Car(11123, "GTR"));
	}
	public Person createPerson2() {
		return new Person(14, "selia", new Car(45353, "ralo"));
	}
}
