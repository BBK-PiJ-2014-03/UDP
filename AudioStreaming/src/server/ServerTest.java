package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sound.*;

public class ServerTest {

		public static void main(String[] args) {
			try {
				ServerSocket server = new ServerSocket(10013);
				Socket client = server.accept();
				
				DatagramSocket udpServer = new DatagramSocket(10014);
				DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
				udpServer.receive(request);
				
				String testString = "Hello I am a string";
				byte[] data = testString.getBytes("US-ASCII");
				InetAddress host = request.getAddress();
				int port = request.getPort();
				DatagramPacket response = new DatagramPacket(data, data.length, host, port);
				
				udpServer.send(response);
				
				DataInputStream is = new DataInputStream(client.getInputStream());
				DataOutputStream os = new DataOutputStream(client.getOutputStream());
				
				os.writeUTF("Hello clients of the ServerTest");
				
				client.close();
				
				os.close();
				server.close();
				udpServer.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
}
