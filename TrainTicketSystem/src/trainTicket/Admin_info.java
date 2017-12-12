package trainTicket;

public class Admin_info {
	private String admin_account;
	private String password;
	private String admin_name;
	
	public Admin_info(String admin_account, String password, String admin_name) {
		this.admin_account = admin_account;
		this.password = password;
		this.admin_name = admin_name;
	}

	public String getAdmin_account() {
		return admin_account;
	}

	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
}
