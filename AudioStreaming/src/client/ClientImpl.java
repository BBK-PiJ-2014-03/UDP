package client;

import interfaces.Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ClientImpl implements Client {

	private final String GROUP = "localhost";
	private final int PORT = 10000;
	
	private Socket client;
	private int ID;
	private String role;
	
	public ClientImpl() {
		
	}
	
	public static void main(String[] args) {
		Client newClient = new ClientImpl();
		newClient.run();
		
	}
	
	@Override
	public void run() {
		try {
			client = new Socket(GROUP, PORT);
			DataInputStream is = new DataInputStream(client.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			// asks the server for a unique ID.
			this.ID = Integer.parseInt(br.readLine());
			// asks the server whether it is the first to connect.
			this.role = br.readLine();
			
			// if statement checks if the user is the Sender
			if (role.equals("Sender")) {
				DatagramSocket UDPSocket = new DatagramSocket();
				InetAddress address = client.getInetAddress();
				UDPSocket.connect(address, PORT);
				
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void connect() {

	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public boolean isFirst() {
		return false;
	}

	@Override
	public void getUDPConnection() {
		// TODO Auto-generated method stub

	}

}
