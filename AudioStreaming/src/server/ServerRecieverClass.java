package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRecieverClass {

	public static void main(String[] args) {
		
		try {
			
			ServerSocket server2 = new ServerSocket(10011);
			
			Socket client2 = server2.accept();
			
			DataInputStream is = new DataInputStream(client2.getInputStream());
			
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			System.out.println(br.readLine());
			
			client2.close();
			
			server2.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
