package handler;

import interfaces.Client;
import interfaces.Handler;
import interfaces.Server;

import java.util.ArrayList;

/**
 * 
 * Handler Implementation
 * 
 * Manages the Clients of the Server field.
 * 
 * @author IAN
 *
 */
public class HandlerImpl implements Handler {

	private Server server;
	
	public HandlerImpl(Server server) {
		this.server = server;
	}
	
	/**
	 * 
	 * Run method for the HandlerImpl thread
	 * 
	 */
	@Override
	public void run() {
		// loop runs
		while (true) {
				// gets the current clients from the Server
				ArrayList<Client> clientList = server.getClientList();
				if (clientList[0] = null) {
					
				}
				
		}

	}

}
