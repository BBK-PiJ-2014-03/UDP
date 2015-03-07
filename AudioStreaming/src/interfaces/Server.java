package interfaces;

import java.net.DatagramSocket;
import java.util.ArrayList;


public interface Server {
	
	public int getUniqueID();
	public ArrayList<Client> getClientList();
	public DatagramSocket getSocket();
}