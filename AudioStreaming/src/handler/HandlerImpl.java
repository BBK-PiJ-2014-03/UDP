package handler;

import interfaces.Client;
import interfaces.Handler;
import interfaces.Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import server.ServerImpl;
import client.ClientImpl;

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
	
	/**
	 * Constructor for the Handler
	 * handles the clients of the server.
	 * 
	 * 
	 * @param server
	 */
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
			// each time the loop runs the newest list of clients is gathered
			ArrayList<Client> clientList = server.getClientList();
			// if there are no clients for the server to handle
			// the handler thread goes to sleep
			if (clientList.isEmpty()) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// if the server has clients
			// the handler loops through the clients to discover if there is a SENDER client
			else {
				boolean hasSender = false;
				for (Client c : clientList) {
					if (c.getRole() == ClientImpl.SENDER) {
						hasSender = true;
					}
				}
				// if there isn't a sender the handler takes the next client
				// in the list and sets it to be SENDER
				if (hasSender == false) {
					for (Client c : clientList) {
						if (c != null) {
							try {
								DataOutputStream clientOutput = new DataOutputStream(c.getSocket().getOutputStream());
								clientOutput.writeBytes(ClientImpl.SENDER);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}

	}

}
