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
	
	private ServerSocket TCPSocket;
	private Socket client;
	private DatagramSocket UDPSocket;
	
	private ArrayList<Client> clientList;

	public static void main(String[] args) {
		
		ServerImpl server = new ServerImpl();
		
		server.run();
		
	}
	
	private void run() {
		
		try {
			
			TCPSocket = new ServerSocket(10000);
			client = TCPSocket.accept();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void clientListener() {
		
	}

	@Override
	public Handler handleClient(Socket socket) {
		return null;
	}

}
