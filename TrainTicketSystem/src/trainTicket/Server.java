package trainTicket;
import java.io.*;

import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
	private ServerSocket server;
	private static ExecutorService pool;
	static {
		pool = Executors.newFixedThreadPool(100);
		//pool.execute(command);
	}

	//所有客户端的输出流
	public Server() {
		try {
			server = new ServerSocket(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//线程体，用于并发处理不同客户端的交互
		private class ClientHandler implements Runnable {
			//处理客户端的请求
			private Socket socket;
			private User_infoService user_info = new User_infoService();
			private Admin_infoService admin_info = new Admin_infoService();
			private Passenger_infoService passenger_info = new Passenger_infoService();
			private AirplaneService airplane_info = new AirplaneService();
			
			public ClientHandler(Socket socket) {
				this.socket = socket;
			}
			
			@Override
			public void run() {
				PrintWriter pw = null;
				try {
					//客户端的输出流
					OutputStream out = socket.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
					pw = new PrintWriter(osw,true);
					
					//获取客户端发送的消息,将消息给客户
					InputStream in = socket.getInputStream();
					InputStreamReader isr = new InputStreamReader(in,"UTF-8");
					BufferedReader br = new BufferedReader(isr);
					String message = null;
					while((message = br.readLine()) != null){
						
						String[] commands = message.split(",");
						int commandType = Integer.parseInt(commands[0]);
						switch(commandType) {
						case 0:
							admin(pw,commands);
							break;
						case 1:
							login(pw,commands);
							break;
						case 2:
							signup(pw,commands);
							break;
						case 3:
							buyTicket(pw,commands);
							break;
						case 4:
							refundTicket(pw,commands);
							break;
						case 5:
							findTicket(pw, commands);
							break;
						case 6:
							deleteAccount(pw,commands);
							break;
						case 7:
							changePassword(pw,commands);
							break;
						case 8:
							changePhone(pw,commands);
							break;
						case 9:
							changeUserName(pw,commands);
							break;
						case 10:
							findUserInfo(pw, commands);
							break;
						case 11:
							addAirplane(pw,commands);
							break;
						case 12:
							deleteAirplane(pw,commands);
							break;
						case 13:
							changePrice(pw,commands);
							break;
						case 14:
							changeStartTime(pw,commands);
							break;
						case 15:
							changeArriveTime(pw,commands);
							break;
						case 16:
							changeStation(pw,commands);
							break;
						case 17:
							findAllTicket(pw,commands);
							break;
						case 18:
							changeTotalTicket(pw,commands);
							break;
						case 19:
							changeStandbyTicket(pw,commands);
							break;
						}
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				} finally {
					if(socket!=null){
						try{
							socket.close();
						}catch(IOException e){
							e.printStackTrace();
						}
					}
				}
			}
			private void admin(PrintWriter pw, String[] commands) {
				Admin_info info = admin_info.find_admin_info_byAccount(commands[1]);
				if (info == null) {
					pw.println("不存在该管理员");
				} else if (!info.getPassword().equals(commands[2])) {
					pw.println("密码错误");
				} else {
					pw.println("登录成功");
				}
			}
			private void login(PrintWriter pw, String[] commands) {
				User_info info = user_info.find_user_info_byAccount(commands[1]);
				if (info == null) {
					pw.println("不存在该账号");
				} else if (!info.getPassword().equals(commands[2])) {
					pw.println("密码错误");
				} else {
					pw.println("登录成功");
				}
			}
			
			private void signup(PrintWriter pw, String[] commands) {
				User_info info = new User_info(commands[1], commands[2], commands[3], Double.parseDouble(commands[4]));
				boolean success = user_info.add_user_info(info);
				if (success)
					pw.println("注册成功"); 
				else
					pw.println("注册失败");
			}
			
			private void buyTicket(PrintWriter pw, String[] commands) {
				Passenger_info info = new Passenger_info(commands[1], commands[2], commands[3],commands[4], commands[5], Double.parseDouble(commands[6]));
				boolean success = passenger_info.add_passenger_info(info);
				if (success)
					pw.println("购买机票成功");
				else
					pw.println("购买机票失败");
			}
			private void refundTicket(PrintWriter pw, String[] commands) {
				boolean success = passenger_info.delete_passenger_info(commands[1], commands[2]);
				if (success)
					pw.println("退票成功");
				else
					pw.println("退票失败");
			}
			
			private void findTicket(PrintWriter pw, String[] commands) {
				Passenger_info[] infoSet = passenger_info.find_passenger_byAccount(commands[1]);
				if (infoSet == null)
					pw.println("该账户没有购买任何机票");
				else
					
					for (Passenger_info info: infoSet) {
						pw.println(info.getUser_account() + "/" + info.getName()+ "/"  +info.getFlight()+ "/"  +info.getStar_station()+ "/"  +info.getArrived_station() + "/" + info.getId_number());
					}
			}
			
			private void findAllTicket(PrintWriter pw, String[] commands) {
				Airplane[] infoSet = airplane_info.find_airplane(commands[1], commands[2], commands[3]);
				if (infoSet == null)
					pw.println("没有满足以上条件的机票");
				else {
					String result = "";
					for (Airplane info: infoSet) {
						result += info.getFlight() + "/" + info.getStart_station()+ "/"  +info.getArrive_station()+ "/"  +info.getFlight_price()+ "/"  +info.getStart_time() + "/" + info.getArrive_time()+ "/"  +info.getTotal_ticket() + "/" + info.getStandby_ticket();
					}
					pw.println(result);
				}
			}
			
			private void deleteAccount(PrintWriter pw, String[] commands) {
				boolean success = user_info.delete_user_info(commands[1]);
				if (success)
					pw.println("删除账号成功");
				else
					pw.println("删除账号失败");
			}
			
			private void changePassword(PrintWriter pw, String[] commands) {
				boolean success = user_info.update_user_password(commands[1], commands[2]);
				if (success)
					pw.println("更改密码成功");
				else
					pw.println("更改密码失败");
				
			}
			
			private void changePhone(PrintWriter pw, String[] commands) {
				boolean success = user_info.update_user_phone_number(commands[1], Double.parseDouble(commands[2]));
				if (success)
					pw.println( "更改电话成功");
				else
					pw.println( "更改电话失败");
			}
			
			private void changeUserName(PrintWriter pw, String[] commands) {
				boolean success = user_info.update_user_name(commands[1], commands[2]);
				if (success)
					pw.println("更改用户名成功");
				else
					pw.println( "更改用户名失败");
			}
			
			private void findUserInfo(PrintWriter pw, String[] commands) {
				User_info info = user_info.find_user_info_byAccount(commands[1]);
				pw.println("账号："+info.getUser_account() + "/" + "用户名："+info.getUser_name()+ "/" + "电话号码："+info.getPhone_number());
			}
			
			private void addAirplane(PrintWriter pw, String[] commands) {
				Airplane info = new Airplane(commands[1], commands[2], commands[3], Integer.parseInt(commands[4]),commands[5], commands[6], Integer.parseInt(commands[7]), Integer.parseInt(commands[8])); 
				boolean success = airplane_info.add_airplane(info);
				if (success)
					pw.println( "添加航班成功");
				else
					pw.println( "添加航班失败");
			}
			
			private void deleteAirplane(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.delete_airplane(commands[1], commands[2]);
				if (success)
					pw.println( "删除航班成功");
				else
					pw.println( "删除航班失败");
			}
			private void changePrice(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.update_airplane_flight_price(commands[1], commands[2],Integer.parseInt(commands[3]));
				if (success)
					pw.println( "更改机票价格成功");
				else
					pw.println( "更改机票价格失败");
			}
			
			private void changeStartTime(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.update_airplane_flight_start_time(commands[1], commands[2],commands[3]);
				if (success)
					pw.println( "更改机票出发时间成功");
				else
					pw.println( "更改机票出发时间失败");
			}
			
			private void changeArriveTime(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.update_airplane_flight_arrived_time(commands[1], commands[2],commands[3]);
				if (success)
					pw.println("更改机票到达时间成功");
				else
					pw.println( "更改机票到达时间失败");
			}
			private void changeStation(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.update_airplane_flight_station(commands[1], commands[2],commands[3],commands[4]);
				if (success)
					pw.println("更改机票站点成功");
				else
					pw.println("更改机票站点失败");
			}
			
		
			
			private void changeTotalTicket(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.update_airplane_flight_total_ticket(commands[1], commands[2],Integer.parseInt(commands[3]));
				if (success)
					pw.println("更改机票总数成功");
				else
					pw.println( "更改机票总数失败");
			}
			private void changeStandbyTicket(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.update_airplane_flight_standby_ticket(commands[1], commands[2],Integer.parseInt(commands[3]));
				if (success)
					pw.println( "更改站票总数成功");
				else
					pw.println( "更改站票总数失败");
			}
			
		}
		
		//启动服务器的方法
		//改写start方法，循环阻塞等待多个用户的连接。当有用户连接时，就启动内部类线程来处理相应的任务。
		public void start() {
			//循环监听客户端的连接
			while(true) {
				System.out.println("等待用户连接....");
				//监听用户连接
				try {
					Socket socket = server.accept();
					System.out.println("有用户连接上来.....");
					//启动一个线程来对该客户端进行交互
					ClientHandler handler = new ClientHandler(socket);
					pool.execute(handler);
					//new Thread(handler).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		public static void main(String[] args) {
			Server server = new Server();
			server.start();
		}

}
