package trainTicket;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.apache.commons.dbcp.BasicDataSource;

public class myConUtil {
	private static BasicDataSource bds = null;
	private static void connect(){
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("src//trainTicket//oracleConnection"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String sql = p.getProperty("sql");
		String url = p.getProperty("url");
		String name = p.getProperty("username");
		String pass = p.getProperty("password");
		
		String init = p.getProperty("init");
		String max = p.getProperty("maxidle");
		String min = p.getProperty("minidle");
		String maxa = p.getProperty("maxactive");
		String wait = p.getProperty("wait");
		
		bds = new BasicDataSource();
		bds.setDriverClassName(sql);
		bds.setUrl(url);
		bds.setUsername(name);
		bds.setPassword(pass);
		bds.setInitialSize(Integer.parseInt(init));
		bds.setMaxIdle(Integer.parseInt(max));
		bds.setMaxActive(Integer.parseInt(maxa));
		bds.setMinIdle(Integer.parseInt(min));
		bds.setMaxWait(Integer.parseInt(wait));	
	}
	public static synchronized Connection getcon(){
		Connection con = null;
		if(bds==null)
			connect();
		if(bds!=null)
			try {
				con = bds.getConnection();
				System.out.println("获取connection成功");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return con;
	}
	public static void close(Connection con){
		if(con!=null)
			try {
				con.close();
				System.out.println("关闭connection成功");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
