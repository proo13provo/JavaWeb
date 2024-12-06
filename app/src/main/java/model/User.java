package model;

public class User {
	private int id;
	private String uName;
	private String uPassword;
	private String email;

	public User(int id, String uName, String uPassword, String email) {
		super();
		this.id = id;
		this.uName = uName;
		this.uPassword = uPassword;
		this.email = email;
	}

	public User() {
		super();
	}

	public String getuName() {
		return uName;
	}

	public String getuPassword() {
		return uPassword;
	}

	public String getEmail() {
		return email;

	}

	public int getId() {
		return id;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public void setEmail() {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", uName=" + uName + ", uPassword=" + uPassword + ", email=" + email + "]";
	}

	public void setId() {
		this.id = id;
	}
	
}
