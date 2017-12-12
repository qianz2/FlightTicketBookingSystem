package trainTicket;
import java.sql.*;

public class User_infoService implements User_infoDAO{
	
	public boolean delete_user_info(String user_account) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String deleteSQL = "DELETE FROM user_info WHERE user_account = ?";
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setString(1, user_account);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法删除该客户信息");
		}
		return false;
	}
		
	public boolean add_user_info(User_info info) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String addSQL = "INSERT INTO user_info VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(addSQL);
			pstmt.setString(1, info.getUser_account());
			pstmt.setString(2, info.getPassword());
			pstmt.setString(3, info.getUser_name());
			pstmt.setDouble(4, info.getPhone_number());
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法添加该客户信息");
		}
		return false;
	}
		
	public User_info find_user_info_byAccount(String user_account) { 
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String findSQL = "SELECT * FROM user_info WHERE user_account = ?";
			pstmt = conn.prepareStatement(findSQL);
			pstmt.setString(1, user_account);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String password = rs.getString(2);
			String user_name = rs.getString(3);
			double phone_number = rs.getDouble(4);
			User_info info = new User_info(user_account, password, user_name, phone_number);
			myConUtil.close(conn);
			return info;
		} catch(SQLException e) {
			System.out.println("无法查询该客户信息");
		}	
		return null;
	}
		
	public boolean update_user_password(String user_account, String password) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE user_info SET password = ? WHERE user_account = ?";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, password);
			pstmt.setString(2, user_account);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法更改该客户信息");
		}
		return false;
	}
	
	public boolean update_user_phone_number(String user_account, double phone_number) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE user_info SET phone_number = ? WHERE user_account = ?";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setDouble(1, phone_number);
			pstmt.setString(2, user_account);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法更改该客户信息");
		}
		return false;
	}
	
	public boolean update_user_name(String user_account, String user_name) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE user_info SET user_name = ? WHERE user_account = ?";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_account);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法更改该客户信息");
		}
		return false;
	}
	
}
