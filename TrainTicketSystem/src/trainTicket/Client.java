package trainTicket;

import java.net.Socket;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

public class Client {
	private Socket socket;
	public Client(){
		try {
			socket = new Socket("localhost",8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//客户端的工作方法
	public void start(){
		MainMenu menu = new MainMenu(socket);
		menu.FirstMenu();

	}
	public void closeSocket() {
		if(socket!=null)
			try {
				socket.close();
				System.out.println("已退出程序");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}
}
