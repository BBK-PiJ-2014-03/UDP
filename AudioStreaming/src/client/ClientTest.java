package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClientTest {

	public static void main(String[] args) {
		
		try {
			
			Socket client = new Socket("localhost", 10007);
			
			DataInputStream is = new DataInputStream(client.getInputStream());
			DataOutputStream os = new DataOutputStream(client.getOutputStream());
			
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			System.out.println(br.readLine());
			
			client.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
