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
	
	Socket client;
	DatagramSocket udpClient;
	InetAddress host;
	
	
	DataInputStream is;
	DataOutputStream os;
	InputStreamReader isr;
	BufferedReader br;
	

	public static void main(String[] args) {
		
		ClientTest ct = new ClientTest();
		ct.run();
	}

	
	private void run() {
		
		try {
			client = new Socket("localhost", 10027);
			
			udpClient = new DatagramSocket(0);
			//udpClient.setSoTimeout(1000);
			host = InetAddress.getByName("localhost");
			
			is = new DataInputStream(client.getInputStream());
			os = new DataOutputStream(client.getOutputStream());
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
	
			
			while (true) {
				DatagramPacket request = new DatagramPacket(new byte[1024], 1024, host, 10028);
				byte[] data = new byte[1024];
				DatagramPacket response = new DatagramPacket(data, data.length);
				
				udpClient.send(request);
				udpClient.receive(response);
				
				String testString = new String(response.getData(), 0,
						response.getLength(), "US-ASCII");
				System.out.println(testString);
				
				System.out.println(br.readLine());
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
				udpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
