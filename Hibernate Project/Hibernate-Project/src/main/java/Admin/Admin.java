package Admin;

import javax.persistence.*;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	private String UserName;
	private String Email;
	private String Password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(int id, String userName, String email, String password) {
		super();
		this.id = id;
		UserName = userName;
		Email = email;
		Password = password;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", UserName=" + UserName + ", Email=" + Email + ", Password=" + Password + "]";
	}
}
