package mybatisfirst.user;

public class User {
	private String id;
	private String email;
	private String name;
	private int passwd;

	public User() {
	}

	public User(String id, String email, String name, int passwd) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.passwd = passwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPasswd() {
		return passwd;
	}

	public void setPasswd(int passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", passwd=" + passwd + "]";
	}
	
	
	
}
