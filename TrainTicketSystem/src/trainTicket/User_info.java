package trainTicket;

public class User_info {
	private String user_account;
	private String password;
	private String user_name;
	private double phone_number;
	
	public User_info (String user_account, String password, String user_name, double phone_number) {
		this.user_account = user_account;
		this.password = password;
		this.user_name = user_name;
		this.phone_number = phone_number;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public double getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(double phone_number) {
		this.phone_number = phone_number;
	}
}
