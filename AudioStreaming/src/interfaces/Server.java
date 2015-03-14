package interfaces;

import java.net.DatagramSocket;
import java.util.ArrayList;

/**
 * 
 * interface representing a server
 * 
 * @author IAN
 *
 */
public interface Server {
	
	/**
	 * gets a unique id number to send to clients
	 * 
	 * @return the servers client id
	 */
	public int getUniqueID();
	/**
	 * gets a list of the servers clients
	 * 
	 * @return the ArrayList of the clients
	 */
	public ArrayList<Client> getClientList();
	
	/**
	 * 
	 * gets the datagram socket that this server uses to relay data
	 * 
	 * @return the servers datagram socket
	 */
	public DatagramSocket getSocket();
}