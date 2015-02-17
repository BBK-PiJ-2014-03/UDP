package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

		public static void main(String[] args) {
			try {
				ServerSocket server = new ServerSocket(10010);
				
				Socket client = server.accept();
				
				DataInputStream is = new DataInputStream(client.getInputStream());
				DataOutputStream os = new DataOutputStream(client.getOutputStream());
				
				os.writeUTF("Hello clients of the ServerTest");
				
				client.close();
				
				server.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
}
