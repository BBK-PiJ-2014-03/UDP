package interfaces;

import java.util.ArrayList;


public interface Server {
	
	public int getUniqueID();
	public ArrayList<Client> getClientList();
}