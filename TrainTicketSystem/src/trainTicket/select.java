package trainTicket;

import java.sql.*;

public class select {
	public static void main(String[] args) {	
		Connection con = myConUtil.getcon();
		try {
			Statement sta = con.createStatement();
			String str = "select * from ";
			String tableName = "airplane";
			ResultSet res = null;
			res = sta.executeQuery(str+tableName);
			ResultSetMetaData rsmd = res.getMetaData();
			int colNum = rsmd.getColumnCount();
			String[] s = new String[colNum];
			for(int i = 1;i<=colNum;i++){
				s[i-1] = rsmd.getColumnName(i);
				System.out.printf("%-20s",s[i-1]);
			}
			System.out.println();
			while(res.next()){
				for(int i = 0;i<colNum;i++)
					System.out.printf("%-20s",res.getString(s[i]));
				System.out.println();
			}
			myConUtil.close(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
