package trainTicket;

import java.sql.*;

public class update {
	public static void main(String[] args) {
		 Connection con = myConUtil.getcon();
		 try {
		 Statement sta = con.createStatement();
		 String sql = "insert into passenger_info VALUES('stellaQian', 'qzx', 'CA3137', '天河国际机场T3','首都国际机场T2', '1007')";
		System.out.println(sta.executeUpdate(sql));
		 } catch (SQLException e) {
		 e.printStackTrace();
		 }

	}
}
