package Serial;

public interface ISerialComm {

	
	public boolean connectToPort(String comPort, int baudRate);
	
	// sends string via byte form
	public void sendString(String data);
	
	// sends data with byte type
	public void sendBytes(byte data);
	
	// return null if there is no available data
	public String recieveMessage();
	
	public boolean closePort();
	
	
}
