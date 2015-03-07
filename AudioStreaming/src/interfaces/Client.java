package interfaces;

import java.net.Socket;

public interface Client extends Runnable{

	
	public int getID();
	
	public String getRole();
	
	public Socket getSocket();
	
}
