package server;

import interfaces.Client;
import interfaces.Handler;
import interfaces.Server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerImpl implements Server {
	
	private final String GROUP = "localhost";
	private final int PORT = 10000;
	
	private ServerSocket serverTCPSocket;
	private Socket client;
	private DatagramSocket serverUDPSocket;
	
	private ArrayList<Client> clientList;

	
	public ServerImpl() {
		
		try {
			
			serverTCPSocket = new ServerSocket(PORT);
			serverUDPSocket = new DatagramSocket(PORT);
			
			clientList = new ArrayList<Client>();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		ServerImpl server = new ServerImpl();
		
		server.start();
		
	}
	
	private void start() {
		
		while(true) {
			clientListener();
		}
		
		
	}
	
	@Override
	public synchronized void clientListener() {
		
	}

	@Override
	public Handler handleClient(Socket socket) {
		return null;
	}

}
