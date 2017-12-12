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

	//���пͻ��˵������
	public Server() {
		try {
			server = new ServerSocket(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//�߳��壬���ڲ�������ͬ�ͻ��˵Ľ���
		private class ClientHandler implements Runnable {
			//����ͻ��˵�����
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
					//�ͻ��˵������
					OutputStream out = socket.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
					pw = new PrintWriter(osw,true);
					
					//��ȡ�ͻ��˷��͵���Ϣ,����Ϣ���ͻ�
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
					pw.println("�����ڸù���Ա");
				} else if (!info.getPassword().equals(commands[2])) {
					pw.println("�������");
				} else {
					pw.println("��¼�ɹ�");
				}
			}
			private void login(PrintWriter pw, String[] commands) {
				User_info info = user_info.find_user_info_byAccount(commands[1]);
				if (info == null) {
					pw.println("�����ڸ��˺�");
				} else if (!info.getPassword().equals(commands[2])) {
					pw.println("�������");
				} else {
					pw.println("��¼�ɹ�");
				}
			}
			
			private void signup(PrintWriter pw, String[] commands) {
				User_info info = new User_info(commands[1], commands[2], commands[3], Double.parseDouble(commands[4]));
				boolean success = user_info.add_user_info(info);
				if (success)
					pw.println("ע��ɹ�"); 
				else
					pw.println("ע��ʧ��");
			}
			
			private void buyTicket(PrintWriter pw, String[] commands) {
				Passenger_info info = new Passenger_info(commands[1], commands[2], commands[3],commands[4], commands[5], Double.parseDouble(commands[6]));
				boolean success = passenger_info.add_passenger_info(info);
				if (success)
					pw.println("�����Ʊ�ɹ�");
				else
					pw.println("�����Ʊʧ��");
			}
			private void refundTicket(PrintWriter pw, String[] commands) {
				boolean success = passenger_info.delete_passenger_info(commands[1], commands[2]);
				if (success)
					pw.println("��Ʊ�ɹ�");
				else
					pw.println("��Ʊʧ��");
			}
			
			private void findTicket(PrintWriter pw, String[] commands) {
				Passenger_info[] infoSet = passenger_info.find_passenger_byAccount(commands[1]);
				if (infoSet == null)
					pw.println("���˻�û�й����κλ�Ʊ");
				else
					
					for (Passenger_info info: infoSet) {
						pw.println(info.getUser_account() + "/" + info.getName()+ "/"  +info.getFlight()+ "/"  +info.getStar_station()+ "/"  +info.getArrived_station() + "/" + info.getId_number());
					}
			}
			
			private void findAllTicket(PrintWriter pw, String[] commands) {
				Airplane[] infoSet = airplane_info.find_airplane(commands[1], commands[2], commands[3]);
				if (infoSet == null)
					pw.println("û���������������Ļ�Ʊ");
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
					pw.println("ɾ���˺ųɹ�");
				else
					pw.println("ɾ���˺�ʧ��");
			}
			
			private void changePassword(PrintWriter pw, String[] commands) {
				boolean success = user_info.update_user_password(commands[1], commands[2]);
				if (success)
					pw.println("��������ɹ�");
				else
					pw.println("��������ʧ��");
				
			}
			
			private void changePhone(PrintWriter pw, String[] commands) {
				boolean success = user_info.update_user_phone_number(commands[1], Double.parseDouble(commands[2]));
				if (success)
					pw.println( "���ĵ绰�ɹ�");
				else
					pw.println( "���ĵ绰ʧ��");
			}
			
			private void changeUserName(PrintWriter pw, String[] commands) {
				boolean success = user_info.update_user_name(commands[1], commands[2]);
				if (success)
					pw.println("�����û����ɹ�");
				else
					pw.println( "�����û���ʧ��");
			}
			
			private void findUserInfo(PrintWriter pw, String[] commands) {
				User_info info = user_info.find_user_info_byAccount(commands[1]);
				pw.println("�˺ţ�"+info.getUser_account() + "/" + "�û�����"+info.getUser_name()+ "/" + "�绰���룺"+info.getPhone_number());
			}
			
			private void addAirplane(PrintWriter pw, String[] commands) {
				Airplane info = new Airplane(commands[1], commands[2], commands[3], Integer.parseInt(commands[4]),commands[5], commands[6], Integer.parseInt(commands[7]), Integer.parseInt(commands[8])); 
				boolean success = airplane_info.add_airplane(info);
				if (success)
					pw.println( "��Ӻ���ɹ�");
				else
					pw.println( "��Ӻ���ʧ��");
			}
			
			private void deleteAirplane(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.delete_airplane(commands[1], commands[2]);
				if (success)
					pw.println( "ɾ������ɹ�");
				else
					pw.println( "ɾ������ʧ��");
			}
			private void changePrice(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.update_airplane_flight_price(commands[1], commands[2],Integer.parseInt(commands[3]));
				if (success)
					pw.println( "���Ļ�Ʊ�۸�ɹ�");
				else
					pw.println( "���Ļ�Ʊ�۸�ʧ��");
			}
			
			private void changeStartTime(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.update_airplane_flight_start_time(commands[1], commands[2],commands[3]);
				if (success)
					pw.println( "���Ļ�Ʊ����ʱ��ɹ�");
				else
					pw.println( "���Ļ�Ʊ����ʱ��ʧ��");
			}
			
			private void changeArriveTime(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.update_airplane_flight_arrived_time(commands[1], commands[2],commands[3]);
				if (success)
					pw.println("���Ļ�Ʊ����ʱ��ɹ�");
				else
					pw.println( "���Ļ�Ʊ����ʱ��ʧ��");
			}
			private void changeStation(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.update_airplane_flight_station(commands[1], commands[2],commands[3],commands[4]);
				if (success)
					pw.println("���Ļ�Ʊվ��ɹ�");
				else
					pw.println("���Ļ�Ʊվ��ʧ��");
			}
			
		
			
			private void changeTotalTicket(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.update_airplane_flight_total_ticket(commands[1], commands[2],Integer.parseInt(commands[3]));
				if (success)
					pw.println("���Ļ�Ʊ�����ɹ�");
				else
					pw.println( "���Ļ�Ʊ����ʧ��");
			}
			private void changeStandbyTicket(PrintWriter pw, String[] commands) {
				boolean success = airplane_info.update_airplane_flight_standby_ticket(commands[1], commands[2],Integer.parseInt(commands[3]));
				if (success)
					pw.println( "����վƱ�����ɹ�");
				else
					pw.println( "����վƱ����ʧ��");
			}
			
		}
		
		//�����������ķ���
		//��дstart������ѭ�������ȴ�����û������ӡ������û�����ʱ���������ڲ����߳���������Ӧ������
		public void start() {
			//ѭ�������ͻ��˵�����
			while(true) {
				System.out.println("�ȴ��û�����....");
				//�����û�����
				try {
					Socket socket = server.accept();
					System.out.println("���û���������.....");
					//����һ���߳����Ըÿͻ��˽��н���
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
