package trainTicket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Passenger_infoService implements Passenger_infoDAO{
	//增添
	public boolean add_passenger_info(Passenger_info info) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String addSQL = "INSERT INTO passenger_info VALUES(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(addSQL);
			pstmt.setString(1, info.getUser_account());
			pstmt.setString(2, info.getName());
			pstmt.setString(3, info.getFlight());
			pstmt.setString(4, info.getStar_station());
			pstmt.setString(5, info.getArrived_station());
			pstmt.setDouble(6, info.getId_number());
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法添加该客户购票信息");
		}
		return false;
	}
	//删除
	public boolean delete_passenger_info(String user_account, String flight) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String deleteSQL = "DELETE FROM passenger_info WHERE user_account = ? AND flight = ?";
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setString(1, user_account);
			pstmt.setString(2, flight);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法删除该客户购票信息");
		}
		return false;
	}
	//查询
	public Passenger_info[] find_passenger_byAccount(String user_account) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String findSQL = "SELECT * FROM passenger_info WHERE user_account = ?";
			pstmt = conn.prepareStatement(findSQL);
			pstmt.setString(1, user_account);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			rs.last();
			int length = rs.getRow();
			rs.beforeFirst();
			Passenger_info[] infoSet = new Passenger_info[length];
			while(rs.next()) {
				String name = rs.getString(2);
				String flight = rs.getString(3);
				String star_station = rs.getString(4);
				String arrived_station = rs.getString(5);
				double id_number = rs.getDouble(6);
				Passenger_info info = new Passenger_info(user_account, name, flight, star_station, arrived_station, id_number);
				infoSet[count] = info;
				count++;
			}
			myConUtil.close(conn);
			return infoSet;
		} catch(SQLException e) {
			System.out.println("无法查询该客户购票信息");
		}	
		return null;
		
	}
	//更新
	public void update_passenger_name(String user_account, String name) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE passenger_info SET name = ? WHERE user_account = ?";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, name);
			pstmt.setString(2, user_account);
			pstmt.executeUpdate();
			myConUtil.close(conn);
		} catch(SQLException e) {
			System.out.println("无法更改该客户购票信息");
		}
	}
	
	public void update_passenger_id_number(String user_account, double id)  {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE passenger_info SET id_number = ? WHERE user_account = ?";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setDouble(1, id);
			pstmt.setString(2, user_account);
			pstmt.executeUpdate();
			myConUtil.close(conn);
		} catch(SQLException e) {
			System.out.println("无法更改该客户购票信息");
		}
	}
	
	public void update_passenger_flight(String user_account, String flight)  {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE passenger_info SET flight = ? WHERE user_account = ?";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, flight);
			pstmt.setString(2, user_account);
			pstmt.executeUpdate();
			myConUtil.close(conn);
		} catch(SQLException e) {
			System.out.println("无法更改该客户购票信息");
		}
	}
	
	public void update_passenger_start_station(String user_account, String start_station)  {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE passenger_info SET star_station = ? WHERE user_account = ?";
			pstmt = conn.prepareStatement(updateSQL);
			
			pstmt.setString(1, start_station);
			pstmt.setString(2, user_account);
			pstmt.executeUpdate();
			myConUtil.close(conn);
		} catch(SQLException e) {
			System.out.println("无法更改该客户购票信息");
		}
	}
	
	public void update_passenger_arrived_station(String user_account, String arrived_station)  {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE passenger_info SET arrived_station = ? WHERE user_account = ?";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, arrived_station);
			pstmt.setString(2, user_account);
			pstmt.executeUpdate();
			myConUtil.close(conn);
		} catch(SQLException e) {
			System.out.println("无法更改该客户购票信息");
		}
	}
}
