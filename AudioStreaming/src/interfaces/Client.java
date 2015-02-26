package interfaces;

public interface Client extends Runnable{

	public void connect();
	
	public int getID();
	
	public boolean isFirst();
	
	public void getUDPConnection();
	
	
}
