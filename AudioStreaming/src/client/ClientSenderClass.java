package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSenderClass {

	public static void main(String[] args) {
		
		try {
			
			Socket client2 = new Socket("localhost", 10011);
			
			DataOutputStream os = new DataOutputStream(client2.getOutputStream());
			
			os.writeUTF("Hello Server");
			
			client2.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
