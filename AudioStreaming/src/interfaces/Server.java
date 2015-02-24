package interfaces;

import java.net.*;

public interface Server {
	
	public void clientListener();
	
	public Handler handleClient(Socket socket);
	
}