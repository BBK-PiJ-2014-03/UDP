package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;


public class ClientTest {

	public static void main(String[] args) {
		
		try {
			
			Socket client = new Socket("localhost", 10013);
			
			DatagramSocket udpClient = new DatagramSocket(0);
			udpClient.setSoTimeout(1000);
			
			InetAddress host = InetAddress.getByName("localhost");
			DatagramPacket request = new DatagramPacket(new byte[1024], 1024, host, 10014);
			
			byte[] data = new byte[1024];
			DatagramPacket response = new DatagramPacket(data, data.length);
			
			udpClient.send(request);
			udpClient.receive(response);
			
			String testString = new String(response.getData(), 0, response.getLength(), "US-ASCII");
			
			System.out.println(testString);
			
			
			
			DataInputStream is = new DataInputStream(client.getInputStream());
			DataOutputStream os = new DataOutputStream(client.getOutputStream());
			
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			System.out.println(br.readLine());
			
			client.close();
			udpClient.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
