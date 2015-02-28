package handler;

import interfaces.Handler;
import interfaces.Server;

public class HandlerImpl implements Handler {

	private Server server;
	
	public HandlerImpl(Server server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
