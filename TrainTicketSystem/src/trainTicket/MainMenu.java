package trainTicket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;


public class MainMenu {
	private Socket socket;
	public MainMenu(Socket socket) {
		this.socket = socket;
	}
	public void LogInMenu (){
		JFrame f = new JFrame("���¼ϵͳ");
		JTextField user_account ;
		JPasswordField user_password;
		JLabel account,password;
		JPanel jp1,jp2,jp3;
		JButton submit,submit2,cancel; 
		 user_account = new JTextField(12);
		 user_password = new JPasswordField(12);
		 account = new JLabel("�û���");
		 password = new JLabel("����");
		 submit = new JButton("��ͨ�û���¼");
		 submit2 = new JButton("����Ա��¼");
		 cancel = new JButton("ȡ��");
		 jp1 = new JPanel();
		 jp2 = new JPanel();
		 jp3 = new JPanel();
		 f.setLayout(new GridLayout(3,1));
		 jp1.add(account); 
		 jp1.add(user_account);
		 jp2.add(password);
		 jp2.add(user_password);
		 
		 submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String user = "1," + user_account.getText() + "," + new String(user_password.getPassword());
					String message = sendToServer(user);
					if (message.equals("��¼�ɹ�")) {
						SecondMenu(user_account.getText());
						f.dispose();
					} else {
						JOptionPane.showMessageDialog(null,message);
					}
				}
		 });
		 submit2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String admin = "0," +  user_account.getText() + "," + new String(user_password.getPassword());
					String message = sendToServer(admin);
					if (message.equals("��¼�ɹ�")) {
						AdminMenu();
						f.dispose();
					} else {
						JOptionPane.showMessageDialog(null,message);
					}
				}
		 });
		 
			

		 cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					FirstMenu();
				}
		 });
		 
		 
		 jp3.add(submit);
		 jp3.add(submit2);
		 jp3.add(cancel);     
		 f.add(jp1);
		 f.add(jp2);
		 f.add(jp3);
		 f.setSize(500,500);
		 f.setBackground(Color.white);
		 f.setLocation(600,400);
		 f.setVisible(true);
	}
	
	public String sendToServer(String commands) {
		OutputStream out;
		InputStream in;
		try {
			out = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
			PrintWriter pw = new PrintWriter(osw,true);
			
			in = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(in,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			pw.println(commands);
			String message = br.readLine();
			return message;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "���ͽ��ܷ�����ʧ��";
	}
	
	public void SignUpMenu (){
		JFrame f = new JFrame("����дע����Ϣ");
		JTextField user_account = new JTextField(12);
		JTextField user_name = new JTextField(12);
		JTextField phone_number = new JTextField(12);
		JPasswordField user_password = new JPasswordField(12);
		
		JLabel account,password,phoneNumber,userName;
		JPanel jp1,jp2,jp3,jp4,jp5;
		JButton submit,cancel; 
		
		 account = new JLabel("�˺ţ�");
		 userName = new JLabel("�û�����");
		 phoneNumber = new JLabel("�绰���룺");
		 password = new JLabel("���룺");
		 
		 submit = new JButton("ע��");
		 cancel = new JButton("ȡ��");
		 
		 jp1 = new JPanel();
		 jp2 = new JPanel();
		 jp3 = new JPanel();
		 jp4 = new JPanel();
		 jp5 = new JPanel();
		 f.setLayout(new GridLayout(3,1));
		 jp1.add(account); 
		 jp1.add(user_account);
		 jp2.add(userName); 
		 jp2.add(user_name);
		 jp3.add(phoneNumber); 
		 jp3.add(phone_number);
		 
		 jp4.add(password);
		 jp4.add(user_password);
		 jp5.add(submit);
		 jp5.add(cancel);
		 submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String commands = "2," + user_account.getText() + "," +new String(user_password.getPassword()) + ","+ user_name.getText() + ","+phone_number.getText();
					String message = sendToServer(commands);
					JOptionPane.showMessageDialog(null,message);
					if (message.equals("ע��ɹ�")) {
						FirstMenu();
						f.dispose();
					} 
				}
		 });
		 cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					FirstMenu();
				}
		 });    
		 f.add(jp1);
		 f.add(jp2);
		 f.add(jp3);
		 f.add(jp4);
		 f.add(jp5);
		 f.setSize(500,500);
		 f.setBackground(Color.white);
		 f.setLocation(600,400);
		 f.setVisible(true);
	}
	
	public void FirstMenu () {
		JFrame f = new JFrame("��ӭ����һ���˵�");
		
		GridLayout thisLayout = new GridLayout(); 
		f.setLayout(thisLayout);
		JButton logIn = new JButton("��¼");
		JButton signUp = new JButton("ע��");
		JButton exit = new JButton("�˳�ϵͳ"); 
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInMenu();
				f.dispose();
			}
		});
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpMenu();
				f.dispose();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				if(socket!=null)
					try {
						socket.close();
						System.out.println("���˳�����");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		});
		f.add(logIn);
		f.add(signUp);
		f.add(exit);
		f.setSize(300,300);
		f.setBackground(Color.white);
		f.setLocation(600,400);
		f.setVisible(true);
	}
	public void SecondMenu(String user_account) {
			JFrame f = new JFrame("��ӭ��������˵�");
			GridLayout thisLayout = new GridLayout(); 
			f.setLayout(thisLayout);
			JButton purchase = new JButton("�鿴�ѹ���Ļ�Ʊ");
			JButton refund = new JButton("������Ʊ");
			JButton profile = new JButton("������Ϣ");
			JButton find = new JButton("��ѯ��Ʊ");
			JButton logOut = new JButton("�˳���¼");
			purchase.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String commands = "5," + user_account;
					String message = sendToServer(commands);
					JOptionPane.showMessageDialog(null,message);
				}
			});
			refund.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RefundMenu(user_account);
					f.dispose();
				}
			});
			profile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					ProfileMenu(user_account);
				}
			});
			find.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					findAllTicket(user_account);
					f.dispose();
				}
			});
			logOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					FirstMenu();
				}
			});
			f.add(purchase);
			f.add(refund);
			f.add(profile);
			f.add(find);
			f.add(logOut);
			f.setSize(500,500);
			f.setBackground(Color.white);
			f.setLocation(600,400);
			f.setVisible(true);
		
	}
	
	public void findAllTicket(String user_account) {
		JFrame f = new JFrame("��ѯ��Ʊ");
		JTextField start_station ;
		JTextField arrive_station;
		JTextField start_time;
		JTextArea results = new JTextArea(20,20);
		JLabel startstation,arrivestation,starttime;
		JPanel jp1,jp2,jp3,jp4,jp5;
		JButton submit,submit2,cancel; 
		 start_station = new JTextField(12);
		 arrive_station = new JTextField(12);
		 start_time = new JTextField(12);
		 startstation = new JLabel("�����ص�");
		 arrivestation = new JLabel("����ص�");
		 starttime = new JLabel("����ʱ��");
		 
		 submit = new JButton("��ʼ��ѯ");
		 cancel = new JButton("ȡ����ѯ");
		 jp1 = new JPanel();
		 jp2 = new JPanel();
		 jp3 = new JPanel();
		 jp4 = new JPanel();
		 jp5 = new JPanel();
		 f.setLayout(new GridLayout(3,1));
		 jp1.add(startstation); 
		 jp1.add(start_station);
		 jp2.add(arrivestation);
		 jp2.add(arrive_station);
		 jp3.add(starttime);
		 jp3.add(start_time);
		 
		 
		 submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String user = "17," + start_station.getText() + "," + arrive_station.getText() + "," + start_time.getText();
					String message = sendToServer(user);
					
					if (message.equals("û���������������Ļ�Ʊ")) {
						JOptionPane.showMessageDialog(null,message);
					} else {
						results.append(message);
					}
				}
		 });
		 
		 cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					SecondMenu(user_account);
				}
		 });
		 
		 
		 jp4.add(submit);
		 jp4.add(cancel);  
		 jp5.add(results);
		 f.add(jp1);
		 f.add(jp2);
		 f.add(jp3);
		 f.add(jp4);
		 f.add(jp5);
		 
		 f.setSize(500,500);
		 f.setBackground(Color.white);
		 f.setLocation(600,400);
		 f.setVisible(true);
	}
	
	
	public void AdminMenu() {
		JFrame f = new JFrame("��ӭ�������Ա�˵�");
		GridLayout thisLayout = new GridLayout(); 
		f.setLayout(thisLayout);
		JButton addTicket = new JButton("��ӻ�Ʊ");
		JButton deleteTicket = new JButton("ɾ����Ʊ");
		JButton ticketPrice = new JButton("���Ļ�Ʊ�۸�");
		JButton ticketTime = new JButton("���Ļ�Ʊʱ��");
		JButton ticketStation = new JButton("���Ļ�Ʊվ��");
		JButton ticketNumber = new JButton("���Ļ�ƱƱ��");
		JButton goBack = new JButton("�����ϼ�");
		addTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTicket();
				f.dispose();
			}
		});
		deleteTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteTicket();
				f.dispose();
			}
		});
		ticketPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeTicketPrice();
				f.dispose();
			}
		});
		ticketTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeTicketTime();
				f.dispose();
			}
		});
		ticketStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeTicketStation();
				f.dispose();
			}
		});
		ticketNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeTicketNumber();
				f.dispose();
			}
		});
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FirstMenu();
				f.dispose();
			}
		});
		f.add(addTicket);
		f.add(deleteTicket);
		f.add(ticketPrice);
		f.add(ticketTime);
		f.add(ticketStation);
		f.add(ticketNumber);
		f.add(goBack);
		f.setSize(800,800);
		f.setBackground(Color.white);
		f.setLocation(500,300);
		f.setVisible(true);
	
}
	public void ChangeTicketNumber (){
		JFrame f = new JFrame("���ĺ���Ʊ��");
		JPanel jp1,jp2,jp3,jp4,jp5;
		
		JButton submit,cancel; 
		
		JLabel flight_ = new JLabel("��������Ҫ���ĵĺ���ţ�");
		JTextField flight = new JTextField(12);
		JLabel starttime_ = new JLabel("��������Ҫ���ĵĺ������ʱ�䣺");
		JTextField starttime = new JTextField(12);
		JLabel newstarttime_ = new JLabel("�������µ���Ʊ��Ŀ��");
		JTextField newstarttime = new JTextField(12);
		JLabel newarrivetime_ = new JLabel("�������µ���վƱ��Ŀ��");
		JTextField newarrivetime = new JTextField(12);
		 submit = new JButton("�ύ");
		 cancel = new JButton("ȡ��");
		 jp1 = new JPanel();
		 jp2 = new JPanel();
		 jp3 = new JPanel();
		 jp4 = new JPanel();
		 jp5 = new JPanel();
		 f.setLayout(new GridLayout(3,1));
		 jp1.add(flight_); 
		 jp1.add(flight);
		 jp2.add(starttime_); 
		 jp2.add(starttime);
		 jp3.add(newstarttime_);
		 jp3.add(newstarttime);
		 jp4.add(newarrivetime_);
		 jp4.add(newarrivetime);
		 jp5.add(submit);
		 jp5.add(cancel);
		 submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String result1 = "18," + flight.getText() + "," + starttime.getText() + "," + newstarttime.getText();
					String message1 = sendToServer(result1);
					String result2 = "19," + flight.getText() + "," + starttime.getText() + "," + newarrivetime.getText();
					String message2 = sendToServer(result2);
					if (message1.equals("���Ļ�Ʊ�����ɹ�") && message2.equals("����վƱ�����ɹ�")) {
						JOptionPane.showMessageDialog(null,message1+message2);
						f.dispose();
						AdminMenu();
					} else {
						JOptionPane.showMessageDialog(null,message1+message2);
					}
				}
		 });
		 cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					AdminMenu();
				}
		 });   
		 f.add(jp1);
		 f.add(jp2);
		 f.add(jp3);
		 f.add(jp4);
		 f.add(jp5);
		 f.setSize(500,500);
		 f.setBackground(Color.white);
		 f.setLocation(600,400);
		 f.setVisible(true);
	}
	
	public void AddTicket (){
		JFrame f = new JFrame("����д��������Ϣ");
		JTextField flight = new JTextField(12);
		JTextField startstation = new JTextField(12);
		JTextField arrivestation = new JTextField(12);
		JTextField price = new JTextField(12);
		JTextField starttime = new JTextField(12);
		JTextField arrivetime = new JTextField(12);
		JTextField total = new JTextField(12);
		JTextField standby = new JTextField(12);
		
		
		JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8,jp9;
		JButton submit,cancel; 
		
		 JLabel flight_ = new JLabel("����ţ�");
		 JLabel startstation_ = new JLabel("��ʼվ��");
		 JLabel arrivestation_ = new JLabel("�յ�վ��");
		 JLabel price_ = new JLabel("�۸�");
		 JLabel starttime_ = new JLabel("����ʱ�䣺");
		 JLabel arrivetime_ = new JLabel("����ʱ�䣺");
		 JLabel total_ = new JLabel("��Ʊ����");
		 JLabel standby_ = new JLabel("��վƱ����");
		 
		 submit = new JButton("����");
		 cancel = new JButton("ȡ��");
		 
		 jp1 = new JPanel();
		 jp2 = new JPanel();
		 jp3 = new JPanel();
		 jp4 = new JPanel();
		 jp5 = new JPanel();
		 jp6 = new JPanel();
		 jp7 = new JPanel();
		 jp8 = new JPanel();
		 jp9 = new JPanel();
		 f.setLayout(new GridLayout(3,1));
		 jp1.add(flight_); 
		 jp1.add(flight);
		 jp2.add(startstation_); 
		 jp2.add(startstation);
		 jp3.add(arrivestation_); 
		 jp3.add(arrivestation);
		 jp4.add(price_);
		 jp4.add(price);
		 jp5.add(starttime_); 
		 jp5.add(starttime);
		 jp6.add(arrivetime_); 
		 jp6.add(arrivetime);
		 jp7.add(total_);
		 jp7.add(total);
		 jp8.add(standby_); 
		 jp8.add(standby);
		 jp9.add(submit);
		 jp9.add(cancel);
		 submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String commands = "11," + flight.getText()+"," +startstation.getText()+ ","+ arrivestation.getText() + "," + price.getText() + "," + starttime.getText() + "," + arrivetime.getText() + "," + total.getText() + "," + standby.getText();
					String message = sendToServer(commands);
					JOptionPane.showMessageDialog(null,message);
					if (message.equals("��Ӻ���ɹ�")) {
						AdminMenu();
						f.dispose();
					} 
				}
		 });
		 cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					AdminMenu();
				}
		 });    
		 f.add(jp1);
		 f.add(jp2);
		 f.add(jp3);
		 f.add(jp4);
		 f.add(jp5);
		 f.add(jp6);
		 f.add(jp7);
		 f.add(jp8);
		 f.add(jp9);
		 f.setSize(600,600);
		 f.setBackground(Color.white);
		 f.setLocation(600,400);
		 f.setVisible(true);
	}
	
	public void RefundMenu (String user_account){
		JFrame f = new JFrame("��Ʊϵͳ");
		JTextField flight;
		JLabel flight_num;
		JPanel jp1,jp2;
		JButton submit,cancel; 
		 flight = new JTextField(12);
		 flight_num = new JLabel("��������Ҫ������Ʊ�ĺ���ţ�");
		 submit = new JButton("�ύ");
		 cancel = new JButton("ȡ��");
		 jp1 = new JPanel();
		 jp2 = new JPanel();
		 f.setLayout(new GridLayout(3,1));
		 jp1.add(flight_num); 
		 jp1.add(flight);
		 jp2.add(submit);
		 jp2.add(cancel);
		 submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String result = "4," + user_account + "," + flight.getText();
					String message = sendToServer(result);
					if (message.equals("��Ʊ�ɹ�")) {
						JOptionPane.showMessageDialog(null,message);
						f.dispose();
						SecondMenu(user_account);
					} else {
						JOptionPane.showMessageDialog(null,message);
					}
				}
		 });
		 cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					SecondMenu(user_account);
				}
		 });   
		 f.add(jp1);
		 f.add(jp2);
		 f.setSize(500,500);
		 f.setBackground(Color.white);
		 f.setLocation(600,400);
		 f.setVisible(true);
	}
	public void PhoneMenu (String user_account){
		JFrame f = new JFrame("���ĵ绰");
		JTextField phone_number;
		JLabel phone;
		JPanel jp1,jp2;
		JButton submit,cancel; 
		 phone_number = new JTextField(12);
		 phone = new JLabel("�������µ绰��");
		 submit = new JButton("�ύ");
		 cancel = new JButton("ȡ��");
		 jp1 = new JPanel();
		 jp2 = new JPanel();
		 f.setLayout(new GridLayout(3,1));
		 jp1.add(phone); 
		 jp1.add(phone_number);
		 jp2.add(submit);
		 jp2.add(cancel);
		 submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String result = "8," + user_account + "," + phone_number.getText();
					String message = sendToServer(result);
					if (message.equals("���ĵ绰�ɹ�")) {
						JOptionPane.showMessageDialog(null,message);
						f.dispose();
						ProfileMenu(user_account);
					} else {
						JOptionPane.showMessageDialog(null,message);
					}
				}
		 });
		 cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					ProfileMenu(user_account);
				}
		 });   
		 f.add(jp1);
		 f.add(jp2);
		 f.setSize(500,500);
		 f.setBackground(Color.white);
		 f.setLocation(600,400);
		 f.setVisible(true);
	}
	public void DeleteTicket (){
		JFrame f = new JFrame("ɾ������");
		JPanel jp1,jp2,jp3;
		
		JButton submit,cancel; 
		
		JLabel flight_ = new JLabel("��������Ҫɾ���ĺ���ţ�");
		JTextField flight = new JTextField(12);
		JLabel starttime_ = new JLabel("��������Ҫɾ���ĺ������ʱ�䣺");
		JTextField starttime = new JTextField(12);
		 submit = new JButton("�ύ");
		 cancel = new JButton("ȡ��");
		 jp1 = new JPanel();
		 jp2 = new JPanel();
		 jp3 = new JPanel();
		 f.setLayout(new GridLayout(3,1));
		 jp1.add(flight_); 
		 jp1.add(flight);
		 jp2.add(starttime_); 
		 jp2.add(starttime);
		 jp3.add(submit);
		 jp3.add(cancel);
		 submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String result = "12," + flight.getText() + "," + starttime.getText();
					String message = sendToServer(result);
					if (message.equals("ɾ������ɹ�")) {
						JOptionPane.showMessageDialog(null,message);
						f.dispose();
						AdminMenu();
					} else {
						JOptionPane.showMessageDialog(null,message);
					}
				}
		 });
		 cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					AdminMenu();
				}
		 });   
		 f.add(jp1);
		 f.add(jp2);
		 f.add(jp3);
		 f.setSize(500,500);
		 f.setBackground(Color.white);
		 f.setLocation(600,400);
		 f.setVisible(true);
	}
	public void ChangeTicketStation (){
		JFrame f = new JFrame("���ĺ���վ��");
		JPanel jp1,jp2,jp3,jp4,jp5;
		
		JButton submit,cancel; 
		
		JLabel flight_ = new JLabel("��������Ҫ���ĵĺ���ţ�");
		JTextField flight = new JTextField(12);
		JLabel starttime_ = new JLabel("��������Ҫ���ĵĺ������ʱ�䣺");
		JTextField starttime = new JTextField(12);
		JLabel newstartstation_ = new JLabel("�������µĳ���վ�㣺");
		JTextField newstartstation = new JTextField(12);
		JLabel newarrivestation_ = new JLabel("�������µĵ���վ�㣺");
		JTextField newarrivestation = new JTextField(12);
		 submit = new JButton("�ύ");
		 cancel = new JButton("ȡ��");
		 jp1 = new JPanel();
		 jp2 = new JPanel();
		 jp3 = new JPanel();
		 jp4 = new JPanel();
		 jp5 = new JPanel();
		 f.setLayout(new GridLayout(3,1));
		 jp1.add(flight_); 
		 jp1.add(flight);
		 jp2.add(starttime_); 
		 jp2.add(starttime);
		 jp3.add(newstartstation_);
		 jp3.add(newstartstation);
		 jp4.add(newarrivestation_);
		 jp4.add(newarrivestation);
		 jp5.add(submit);
		 jp5.add(cancel);
		 submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String result = "16," + flight.getText() + "," + starttime.getText() + "," + newstartstation.getText() + "," + newarrivestation.getText();
					String message = sendToServer(result);
					if (message.equals("���Ļ�Ʊվ��ɹ�")) {
						JOptionPane.showMessageDialog(null,message);
						f.dispose();
						AdminMenu();
					} else {
						JOptionPane.showMessageDialog(null,message);
					}
				}
		 });
		 cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					AdminMenu();
				}
		 });   
		 f.add(jp1);
		 f.add(jp2);
		 f.add(jp3);
		 f.add(jp4);
		 f.add(jp5);
		 f.setSize(500,500);
		 f.setBackground(Color.white);
		 f.setLocation(600,400);
		 f.setVisible(true);
	}
	
	public void ChangeTicketTime (){
		JFrame f = new JFrame("���ĺ���ʱ��");
		JPanel jp1,jp2,jp3,jp4,jp5;
		
		JButton submit,cancel; 
		
		JLabel flight_ = new JLabel("��������Ҫ���ĵĺ���ţ�");
		JTextField flight = new JTextField(12);
		JLabel starttime_ = new JLabel("��������Ҫ���ĵĺ������ʱ�䣺");
		JTextField starttime = new JTextField(12);
		JLabel newstarttime_ = new JLabel("�������µĳ���ʱ�䣺");
		JTextField newstarttime = new JTextField(12);
		JLabel newarrivetime_ = new JLabel("�������µĵ���ʱ�䣺");
		JTextField newarrivetime = new JTextField(12);
		 submit = new JButton("�ύ");
		 cancel = new JButton("ȡ��");
		 jp1 = new JPanel();
		 jp2 = new JPanel();
		 jp3 = new JPanel();
		 jp4 = new JPanel();
		 jp5 = new JPanel();
		 f.setLayout(new GridLayout(3,1));
		 jp1.add(flight_); 
		 jp1.add(flight);
		 jp2.add(starttime_); 
		 jp2.add(starttime);
		 jp3.add(newstarttime_);
		 jp3.add(newstarttime);
		 jp4.add(newarrivetime_);
		 jp4.add(newarrivetime);
		 jp5.add(submit);
		 jp5.add(cancel);
		 submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String result1 = "14," + flight.getText() + "," + starttime.getText() + "," + newstarttime.getText();
					String message1 = sendToServer(result1);
					String result2 = "15," + flight.getText() + "," + starttime.getText() + "," + newarrivetime.getText();
					String message2 = sendToServer(result2);
					if (message1.equals("���Ļ�Ʊ����ʱ��ɹ�") && message2.equals("���Ļ�Ʊ����ʱ��ɹ�")) {
						JOptionPane.showMessageDialog(null,message1+message2);
						f.dispose();
						AdminMenu();
					} else {
						JOptionPane.showMessageDialog(null,message1+message2);
					}
				}
		 });
		 cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					AdminMenu();
				}
		 });   
		 f.add(jp1);
		 f.add(jp2);
		 f.add(jp3);
		 f.add(jp4);
		 f.add(jp5);
		 f.setSize(500,500);
		 f.setBackground(Color.white);
		 f.setLocation(600,400);
		 f.setVisible(true);
	}
	
	public void ChangeTicketPrice (){
		JFrame f = new JFrame("���ĺ���۸�");
		JPanel jp1,jp2,jp3,jp4;
		
		JButton submit,cancel; 
		
		JLabel flight_ = new JLabel("��������Ҫ���ĵĺ���ţ�");
		JTextField flight = new JTextField(12);
		JLabel starttime_ = new JLabel("��������Ҫ���ĵĺ������ʱ�䣺");
		JTextField starttime = new JTextField(12);
		JLabel price_ = new JLabel("�������µĻ�Ʊ�۸�");
		JTextField price = new JTextField(12);
		 submit = new JButton("�ύ");
		 cancel = new JButton("ȡ��");
		 jp1 = new JPanel();
		 jp2 = new JPanel();
		 jp3 = new JPanel();
		 jp4 = new JPanel();
		 f.setLayout(new GridLayout(3,1));
		 jp1.add(flight_); 
		 jp1.add(flight);
		 jp2.add(starttime_); 
		 jp2.add(starttime);
		 jp3.add(price_);
		 jp3.add(price);
		 jp4.add(submit);
		 jp4.add(cancel);
		 submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String result = "13," + flight.getText() + "," + starttime.getText() + "," + price.getText();
					String message = sendToServer(result);
					if (message.equals("���Ļ�Ʊ�۸�ɹ�")) {
						JOptionPane.showMessageDialog(null,message);
						f.dispose();
						AdminMenu();
					} else {
						JOptionPane.showMessageDialog(null,message);
					}
				}
		 });
		 cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					AdminMenu();
				}
		 });   
		 f.add(jp1);
		 f.add(jp2);
		 f.add(jp3);
		 f.add(jp4);
		 f.setSize(500,500);
		 f.setBackground(Color.white);
		 f.setLocation(600,400);
		 f.setVisible(true);
	}
	
	public void PasswordMenu (String user_account){
		JFrame f = new JFrame("�����û�����");
		JTextField phone_number;
		JLabel phone;
		JPanel jp1,jp2;
		JButton submit,cancel; 
		 phone_number = new JTextField(12);
		 phone = new JLabel("�����������룺");
		 submit = new JButton("�ύ");
		 cancel = new JButton("ȡ��");
		 jp1 = new JPanel();
		 jp2 = new JPanel();
		 f.setLayout(new GridLayout(3,1));
		 jp1.add(phone); 
		 jp1.add(phone_number);
		 jp2.add(submit);
		 jp2.add(cancel);
		 submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String result = "7," + user_account + "," + phone_number.getText();
					String message = sendToServer(result);
					if (message.equals("��������ɹ�")) {
						JOptionPane.showMessageDialog(null,message);
						f.dispose();
						ProfileMenu(user_account);
					} else {
						JOptionPane.showMessageDialog(null,message);
					}
				}
		 });
		 cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					ProfileMenu(user_account);
				}
		 });   
		 f.add(jp1);
		 f.add(jp2);
		 f.setSize(500,500);
		 f.setBackground(Color.white);
		 f.setLocation(600,400);
		 f.setVisible(true);
	}
	public void UserNameMenu (String user_account){
		JFrame f = new JFrame("�����û���");
		JTextField phone_number;
		JLabel phone;
		JPanel jp1,jp2;
		JButton submit,cancel; 
		 phone_number = new JTextField(12);
		 phone = new JLabel("���������û�����");
		 submit = new JButton("�ύ");
		 cancel = new JButton("ȡ��");
		 jp1 = new JPanel();
		 jp2 = new JPanel();
		 f.setLayout(new GridLayout(3,1));
		 jp1.add(phone); 
		 jp1.add(phone_number);
		 jp2.add(submit);
		 jp2.add(cancel);
		 submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String result = "9," + user_account + "," + phone_number.getText();
					String message = sendToServer(result);
					if (message.equals("�����û����ɹ�")) {
						JOptionPane.showMessageDialog(null,message);
						f.dispose();
						ProfileMenu(user_account);
					} else {
						JOptionPane.showMessageDialog(null,message);
					}
				}
		 });
		 cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					ProfileMenu(user_account);
				}
		 });   
		 f.add(jp1);
		 f.add(jp2);
		 f.setSize(500,500);
		 f.setBackground(Color.white);
		 f.setLocation(600,400);
		 f.setVisible(true);
	}
	
	
	public void ProfileMenu (String user_account) {
			JFrame f = new JFrame("��ӭ���������Ϣ�˵�");
			GridLayout thisLayout = new GridLayout(); 
			f.setLayout(thisLayout);
			JButton findInfo = new JButton("��ѯ������Ϣ");
			JButton changePhone_number = new JButton("�����ֻ���");
			JButton changeUser_name = new JButton("�����û���");
			JButton changePassword = new JButton("��������");
			JButton deleteAccount = new JButton("ɾ���˺�");
			JButton goBack = new JButton("�����ϼ��˵�");
			findInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String commands = "10," + user_account;
					String message = sendToServer(commands);
					JOptionPane.showMessageDialog(null,message);
				}
			});
			changePhone_number.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PhoneMenu(user_account);
					f.dispose();
				}
			});
			changeUser_name.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UserNameMenu(user_account);
					f.dispose();
				}
			});
			changePassword.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PasswordMenu(user_account);
					f.dispose();
				}
			});
			deleteAccount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String commands = "6," + user_account;
					String message = sendToServer(commands);
					JOptionPane.showMessageDialog(null,message);
					FirstMenu();
					f.dispose();
				}
			});
			goBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SecondMenu(user_account);
					f.dispose();
				}
			});
			f.add(findInfo);
			f.add(changeUser_name);
			f.add(changePhone_number);
			f.add(changePassword);
			f.add(deleteAccount);
			f.add(goBack);
			f.setSize(600,600);
			f.setBackground(Color.white);
			f.setLocation(600,400);
			f.setVisible(true);
	}
	
}
