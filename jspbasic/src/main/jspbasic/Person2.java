package jspbasic;

public class Person2 {
	private String name;
	private int age;
	private int mp;
	private String email;
	
	public Person2() {
		
	}

	public Person2(String name, int age, int mp, String email) {
		super();
		this.name = name;
		this.age = age;
		this.mp = mp;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person2 [name=" + name + ", age=" + age + ", mp=" + mp + ", email=" + email + "]";
	}
	
	
}
