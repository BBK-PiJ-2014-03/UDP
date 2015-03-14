package interfaces;

import java.net.Socket;

public interface Client extends Runnable{

	/**
	 * 
	 * gets the clients IDnumber
	 * 
	 * @return unique int for the client
	 */
	public int getID();
	
	/**
	 * gets the clients role
	 * 
	 * @return String of the clients role
	 */
	public String getRole();
	
	/**
	 * returns the socket that this client is connected with
	 * 
	 * @return the clients socket
	 */
	public Socket getSocket();
	
}
