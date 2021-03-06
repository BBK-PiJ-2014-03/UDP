package client;

import interfaces.Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 
 * Implementation of the Client.
 * 
 * Connects to a Server and either sends data to the server or receives data from the server
 * 
 * @author IAN
 *
 */
public class ClientImpl implements Client {

	private final String GROUP = "localhost";
	private final int PORT = 10000;
	private final int BYTELENGTH = 8192;
	
	private Socket client;
	private int ID;
	private String role;
	
	public static final String SENDER = "Sender";
	public static final String RECEIVER = "Receiver";
	
	private File sendFile;
	private File receiveFile;
	
	public ClientImpl(File file) {
		this.sendFile = file;
	}
	/**
	 * 
	 * Empty Constructor
	 * 
	 */
	public ClientImpl() {
		
	}
	
	public static void main(String[] args) {
		// constructs a new Client with the File object passed to the constructor
		try {
			Client newClient = new ClientImpl(new File(args[0]));
			newClient.run();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error: no file passed to Client.");
		}
		
	}
	
	
	/**
	 * 
	 * run() method for ClientImpl threads
	 * 
	 * establishes a connection to the server and then either sends or receives data
	 * 
	 */
	@Override
	public void run() {
		try {
			// establishes the client with the server
			client = new Socket(GROUP, PORT);
			DataInputStream is = new DataInputStream(
					client.getInputStream());
			BufferedReader br = new BufferedReader(
					new InputStreamReader(is));
			// asks the server for a unique ID.
			this.ID = Integer.parseInt(br.readLine());
			// asks the server whether it is the first to connect.
			this.role = br.readLine();

			// if statement checks if the user is the Sender
			if (role.equals(SENDER)) {
				DatagramSocket UDPSocket = new DatagramSocket();
				InetAddress address = client.getInetAddress();
				UDPSocket.connect(address, PORT);
				// converts the dataFile to a byte array
				int fileLength = (int) sendFile.length();
				FileInputStream fis = new FileInputStream(sendFile);
				byte[] fileArray = new byte[fileLength];
				fis.read(fileArray);
				// sends the fileArray to the server
				while (UDPSocket.isConnected()) {
					DatagramPacket sendPacket = new DatagramPacket(
							fileArray, fileArray.length, address, PORT);
					UDPSocket.send(sendPacket);
				}
				fis.close();
				UDPSocket.close();
			}
			// if the client is a receiver, a datagram socket is created
			// a DatagramPacket is created to receive the broadcast from the socket
			else if (role.equals(RECEIVER)) {
				DatagramSocket UDPSocket = new DatagramSocket();
				InetAddress address = client.getInetAddress();
				UDPSocket.connect(address, PORT);
				while (UDPSocket.isConnected()) {
					DatagramPacket response = new DatagramPacket(
							new byte[BYTELENGTH], BYTELENGTH);
					UDPSocket.receive(response);
					// bytes received from the server are written to a File
					FileOutputStream fos = new FileOutputStream(receiveFile);
					fos.write(response.getData());
				}
				UDPSocket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	@Override
	public int getID() {
		return ID;
	}
	@Override
	public String getRole() {
		return role;
	}
	
	@Override
	public Socket getSocket() {
		return client;
	}


}
