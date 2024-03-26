package Login;

import javax.persistence.*;

@Entity
public class Login {

	    @Id
	    @OneToOne
		private int email;
		private int password;
		public int getEmail() {
			return email;
		}
		public void setEmail(int email) {
			this.email = email;
		}
		public int getPassword() {
			return password;
		}
		public void setPassword(int password) {
			this.password = password;
		}
		
		public Login() {
			super();
		}
		
		public Login(int email, int password) {
			super();
			this.email = email;
			this.password = password;
		}
		@Override
		public String toString() {
			return "Login [email=" + email + ", password=" + password + "]";
		}
		public Object Password() {
			// TODO Auto-generated method stub
			return null;
		}
}
