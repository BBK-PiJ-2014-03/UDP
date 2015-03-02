package server;

import handler.HandlerImpl;
import interfaces.Client;
import interfaces.Handler;
import interfaces.Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import client.ClientImpl;

/**
 * 
 * ServerImpl class, an implementation of the Server Interface
 * 
 * Accepts client connections and relays audio Datagram packets between a sender and multiple receivers.
 * 
 * @author IAN
 *
 */
public class ServerImpl implements Server {
	
	private final String GROUP = "localhost";
	private final int PORT = 10000;
	
	private ServerSocket serverTCPSocket;
	private DatagramSocket serverUDPSocket;
	
	private ArrayList<Client> clientList;
	private Handler handler;

	private static AtomicInteger uniqueID;
	
	/**
	 * 
	 * constructor initialises the ServerSocket and DataramSocket
	 * also the List for holding the servers clients and an AtomicInteger object for unique ID numbers
	 * 
	 */
	public ServerImpl() {
		
		try {
			
			serverTCPSocket = new ServerSocket(PORT);
			serverUDPSocket = new DatagramSocket(PORT);
			
			clientList = new ArrayList<Client>();
			
			uniqueID = new AtomicInteger();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main method, creates a new server and calls its start method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		ServerImpl server = new ServerImpl();
		
		server.start();
		
	}
	
	/**
	 * 
	 * Start method initialises a new Handler object for the server
	 * to manage which client is the sender
	 * 
	 * starts looping and listening for client connections
	 * enters a synchronized block where the new client is created '
	 * and the clients unique ID is sent via a DataOutputStream to the client.
	 * 
	 */
	private void start() {
		
		handler = new HandlerImpl(this);
		new Thread(handler);
		
		int clientID;
		
		while(true) {
			try {
				// loop sets the serverTCPSocket into its accepting state.
				Socket client = serverTCPSocket.accept();
				
				// when a connection is made it enters a synchronised block
				synchronized (client) {
					clientID = this.getUniqueID();
					Client newClient = new ClientImpl();
					clientList.add(newClient);
					new Thread(newClient).start();
					// sends the UniqueID to the client
					DataOutputStream clientOutput = new DataOutputStream(client.getOutputStream());
					clientOutput.writeBytes(Integer.toString(clientID));
					// sends whether or not the client is sender or receiver.
					if (clientList.size() > 1) {
						clientOutput.writeBytes("Receiver");
					}
					else {
						clientOutput.writeBytes("Sender");
					}
					
					
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Override
	public synchronized int getUniqueID() {
		return (int) uniqueID.getAndIncrement();
	}
	
	public ArrayList<Client> getClientList() {
		return clientList;
	}

}
