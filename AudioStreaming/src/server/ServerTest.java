package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerTest {

	ServerSocket server;
	Socket client;
	
	DatagramSocket udpServer;
	DataOutputStream os;
	
	public static void main(String[] args) {
		
		ServerTest st = new ServerTest();
		
		st.run();
	}
		
	private void run() {
		try {
			server = new ServerSocket(10027);
			client = server.accept();
			udpServer = new DatagramSocket(10028);
			DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
			
			while (true) {
				udpServer.receive(request);
				String testString = "Hello I am a string";
				byte[] data = testString.getBytes("US-ASCII");
				InetAddress host = request.getAddress();
				int port = request.getPort();
				DatagramPacket response = new DatagramPacket(data, data.length, host, port);
				udpServer.send(response);
				Thread.sleep(1000);
				
				DataInputStream is = new DataInputStream(
						client.getInputStream());
				os = new DataOutputStream(client.getOutputStream());
				os.writeUTF("Hello clients of the ServerTest");
			}
			
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
				
				os.close();
				server.close();
				udpServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
	}
}
