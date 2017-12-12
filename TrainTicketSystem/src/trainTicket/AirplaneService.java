package trainTicket;

import java.sql.*;
import java.text.SimpleDateFormat;

public class AirplaneService implements AirplaneDAO{
	public boolean update_airplane_flight_price(String flight, String start_time, int price) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE airplane SET flight_price = ? WHERE start_time=to_date(?, 'yyyy-mm-dd') AND flight = ?";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setInt(1, price);
			pstmt.setString(2, start_time);
			pstmt.setString(3, flight);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法更改该航班信息");
		}
		return false;
	}
	
	public boolean update_airplane_flight_start_time(String flight, String start_time, String new_start_time) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE airplane SET start_time=to_date(?, 'yyyy-mm-dd') WHERE flight = ? AND start_time=to_date(?, 'yyyy-mm-dd')";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, new_start_time);
			pstmt.setString(2, flight);
			pstmt.setString(3, start_time);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法更改该航班信息");
		}
		return false;
	}
	
	public boolean update_airplane_flight_station(String flight, String start_time, String start_station, String arrived_station) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE airplane SET start_station = ?, arrive_station = ? WHERE flight = ? AND start_time=to_date(?, 'yyyy-mm-dd')";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, start_station);
			pstmt.setString(2, arrived_station);
			pstmt.setString(3, flight);
			pstmt.setString(4, start_time);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法更改该航班信息");
		}
		return false;
	}
	
	
	
	public boolean update_airplane_flight_total_ticket(String flight, String start_time, int ticket) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE airplane SET total_ticket = ? WHERE flight = ? AND start_time=to_date(?, 'yyyy-mm-dd')";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setInt(1, ticket);
			pstmt.setString(2, flight);
			pstmt.setString(3, start_time);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法更改该航班信息");
		}
		return false;
	}
	
	public boolean update_airplane_flight_standby_ticket(String flight, String start_time, int ticket) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE airplane SET standby_ticket = ? WHERE flight = ? AND start_time=to_date(?, 'yyyy-mm-dd')";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setInt(1, ticket);
			pstmt.setString(2, flight);
			pstmt.setString(3, start_time);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法更改该航班信息");
		}
		return false;
	}
	
	public boolean update_airplane_flight_arrived_time(String flight, String start_time, String arrived_time) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String updateSQL = "UPDATE airplane SET arrive_time=to_date(?, 'yyyy-mm-dd') WHERE flight = ? AND start_time=to_date(?, 'yyyy-mm-dd')";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, arrived_time);
			pstmt.setString(2, flight);
			pstmt.setString(3, start_time);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法更改该航班信息");
		}
		return false;
	}
	
	
	public boolean add_airplane(Airplane airplane) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String addSQL = "INSERT INTO airplane VALUES(?,?,?,?,to_date(?, 'yyyy-mm-dd'),to_date(?, 'yyyy-mm-dd'),?,?)";
			pstmt = conn.prepareStatement(addSQL);
			pstmt.setString(1, airplane.getFlight());
			pstmt.setString(2, airplane.getStart_station());
			pstmt.setString(3, airplane.getArrive_station());
			pstmt.setInt(4, airplane.getFlight_price());
			pstmt.setString(5, airplane.getStart_time());
			pstmt.setString(6, airplane.getArrive_time());
			pstmt.setInt(7, airplane.getTotal_ticket());
			pstmt.setInt(8, airplane.getStandby_ticket());
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法添加该航班信息");
		}
		return false;
	}
	
	public boolean delete_airplane(String flight, String start_time) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			String deleteSQL = "DELETE FROM airplane WHERE flight=? AND start_time=to_date(?, 'yyyy-mm-dd')";
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setString(1, flight);
			pstmt.setString(2, start_time);
			pstmt.executeUpdate();
			myConUtil.close(conn);
			return true;
		} catch(SQLException e) {
			System.out.println("无法删除该航班信息");
		}
		return false;
	}
	
	public Airplane[] find_airplane(String start_station, String arrive_station, String start_time) {
		Connection conn;
		try{
			conn = myConUtil.getcon();
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			String findSQL = "SELECT * FROM airplane WHERE start_station=? AND arrive_station = ? AND start_time = to_date(?, 'yyyy-mm-dd')";
			pstmt = conn.prepareStatement(findSQL);
			pstmt.setString(1, start_station);
			pstmt.setString(2, arrive_station);
			pstmt.setString(3, start_time);
			rs = pstmt.executeQuery();
			//进入结果集第一行 假设是唯一航班
			int count = 0;
			rs.last();
			int length = rs.getRow();
			rs.beforeFirst();
			Airplane[] result = new Airplane[length];
			System.out.println(length);//
			while(rs.next()){
				String flight = rs.getString(1);
				int flight_price = rs.getInt(4);
				String arrive_time = new SimpleDateFormat("yyyy-mm-dd").format(rs.getDate(6));
				int total_ticket = rs.getInt(7);
				int standby_ticket = rs.getInt(8);
				Airplane airPlane = new Airplane(flight, start_station, arrive_station, flight_price, start_time, arrive_time, total_ticket, standby_ticket);
				
				result[count] = airPlane;
				count++;
			}
			myConUtil.close(conn);
			return result;
			
		} catch(SQLException e) {
			System.out.println("无法查询该航班信息");
		}
		return null;
	}
}
