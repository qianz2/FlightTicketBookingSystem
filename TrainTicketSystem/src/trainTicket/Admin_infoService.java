package trainTicket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin_infoService implements AdminDAO{
	//ɾ������Ա��Ϣ
	public boolean delete_admin_info(String admin_account) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String deleteSQL = "DELETE FROM admin_info WHERE admin_account = ?";
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setString(1, admin_account);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("�޷�ɾ���ù���Ա��Ϣ");
		}
		return false;
	}
	//��ӹ���Ա��Ϣ
	public boolean add_admin_info(Admin_info info) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String addSQL = "INSERT INTO admin_info VALUES(?,?,?)";
			pstmt = conn.prepareStatement(addSQL);
			pstmt.setString(1, info.getAdmin_account());
			pstmt.setString(2, info.getPassword());
			pstmt.setString(3, info.getAdmin_name());
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("�޷���Ӹù���Ա��Ϣ");
		}
		return false;
	}
	//��ѯ����Ա��Ϣ (������ѯ)
	public Admin_info find_admin_info_byAccount(String admin_account) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String findSQL = "SELECT * FROM admin_info WHERE admin_account = ?";
			pstmt = conn.prepareStatement(findSQL);
			pstmt.setString(1, admin_account);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String password = rs.getString(2);
			String admin_name = rs.getString(3);
			Admin_info info = new Admin_info(admin_account, password, admin_name);
			myConUtil.close(conn);
			return info;
		} catch(SQLException e) {
			System.out.println("�޷���ѯ�ù���Ա��Ϣ");
		}	
		return null;
		
	}
	//��ѯ����Ա��Ϣ(�߼���ѯ)
			
	//���¹���Ա��Ϣ
	public boolean update_Admin_password(String admin_account, String password) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE admin_info SET password = ? WHERE admin_account = ?";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, password);
			pstmt.setString(2, admin_account);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("�޷����¸ù���Ա��Ϣ");
		}
		return false;
	}
	//���¹���Ա��Ϣ
		public boolean update_Admin_name(String admin_account, String name) {
			Connection conn;
			try{
				conn = myConUtil.getcon();
				PreparedStatement pstmt= null;
				String updateSQL = "UPDATE admin_info SET admin_name = ? WHERE admin_account = ?";
				pstmt = conn.prepareStatement(updateSQL);
				pstmt.setString(1, name);
				pstmt.setString(2, admin_account);
				pstmt.executeUpdate();
				myConUtil.close(conn);
				return true;
			} catch(SQLException e) {
				System.out.println("�޷����¸ù���Ա��Ϣ");
			}
			return false;
		}
}
