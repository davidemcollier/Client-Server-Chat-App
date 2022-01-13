package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerChat {

	ServerSocket ss;
	Socket socket;
	String username;

	
	
	ServerChat(String username) {
		this.username = username;
		
	}

	
	
	public void chat() {

		while (true) {
			BufferedReader br;
			PrintWriter pw;
			Scanner scanner = new Scanner(System.in);
			try {
				ss = new ServerSocket(5000);
				System.out.println(this.username + " you are now online.");
				System.out.println("Awaiting client to connect to chatroom...");

				socket = ss.accept();
				System.out.println("A client has connected!");

				pw = new PrintWriter(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				try {
					// SENDING
					new Thread(new Runnable() {
						@Override 
						public void run() {
							while (true) {
								String	msgToClient = scanner.nextLine(); 
								if (msgToClient.equals("\\q")) {
									break;
								} else {
									pw.println(username + ": " + msgToClient);
									pw.flush();
								}
							}

							try {
								pw.close();
								ss.close();
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
								String msgFromClient = br.readLine();

								while (msgFromClient != null) {
									System.out.println(msgFromClient);
									msgFromClient = br.readLine();
								}

								System.out.println("Client has gone offline.");

								pw.close();
								socket.close();
								ss.close();
							} catch (IOException e) {
								
							}
						}
					}).start();
				} catch (Exception e) {

				}
			} catch (Exception e) {

			}

		}

	}

	public static void main(String[] args) {
		System.out.print("Enter your username:");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		ServerChat sc = new ServerChat(username);
		sc.chat();
	}
}