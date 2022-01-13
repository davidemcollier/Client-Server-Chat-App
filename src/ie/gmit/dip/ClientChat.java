package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {

	Socket socket; 
	String username;
	
	ClientChat(String username){
		this.username = username;
	}
	
	
	
	
	public void chat() {

		BufferedReader br; 
		PrintWriter pw; 
		Scanner scanner = new Scanner(System.in); 

		try {
			socket = new Socket("localhost", 5000);
			System.out.println("You have now entered the chatroom...");
			pw = new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// SENDING
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						String msgToServer = scanner.nextLine();
						if (msgToServer.equals("\\q")) {
							break;
						} else {
							pw.println(username + ": " + msgToServer);
							pw.flush();
						}
					}

					try {
						pw.close();
						socket.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}).start();

			// LISTENING
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String msgFromServer = br.readLine();
						while (msgFromServer != null) {
							System.out.println(msgFromServer);
							msgFromServer = br.readLine();
						}
						System.out.println("Server has disconnected from the chat.");
						pw.close();
						socket.close();
					} catch (Exception e) {

					}
				}
			}).start();

		} catch (Exception e) {
			System.out.println("[INFO] Connection failed.");
		}
	}

	public static void main(String[] args) {
		System.out.print("Enter your username:");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		ClientChat cc = new ClientChat(username);
		cc.chat();

	}
}
