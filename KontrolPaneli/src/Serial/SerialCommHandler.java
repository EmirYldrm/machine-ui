package Serial;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import Model.MachineInfo;
import Serial.Commands.CommandHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class SerialCommHandler  implements SerialPortDataListener, ISerialComm{
	
	
	private static SerialCommHandler instance;
	private SerialPort sp;
	private OutputStream outputStream1;
	public  InputStream input;
	private CommandHandler comHandler;
	

	// bu obje ile controller arasinda bind işlemi gerçekleşecek.
	private StringProperty command = new SimpleStringProperty();

	// Singleton Constructor.
	private SerialCommHandler() {
        // Private constructor to prevent instantiation from outside the class
    }
	
	public static synchronized SerialCommHandler getInstance() {
        if (instance == null) {
            instance = new SerialCommHandler();
        }
        return instance;
    }
	
	
	@Override
	public boolean connectToPort(String comPort, int baudRate) {
		sp = SerialPort.getCommPort(comPort); 		// Seri port Combobox'ı ile seçilen portun getirilmesi
		sp.setComPortParameters(baudRate, 8, 1, 0);	//String şeklinde bulunan baudrate integer'a parse edildi.
		sp.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0);
		sp.addDataListener(this);
		
		// portu açmaya çaliş 
		if(sp.openPort()) {
			outputStream1 = sp.getOutputStream();
			input = sp.getInputStream();
			return true;
		}
		else {	// port açılmazsa false döndür.
			return false;
		}
		
	}

	@Override
	public void sendString(String data) {
		String message = data;
    	System.out.println("GİDEN KOMUT : " + message);
		if(sp.isOpen() == true) {
			try {
				
				message = message + "\r\n";
				

				outputStream1.write(message.getBytes());
				outputStream1.flush();
				//messageArea.appendText(String.valueOf(input.read()));
			}
			catch(Exception e) {
				System.out.println("sendmessage");
				e.printStackTrace();

			}
		}
		else {
			System.out.println("port Seçilmedi");
		}
		
	}

	@Override
	public void sendBytes(byte data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String recieveMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override 
	public boolean closePort() {
		
		return this.sp.closePort();
	}
	
// 	
	
	@Override
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; 
	}

	@Override
	public void serialEvent(SerialPortEvent arg0) {
		// Gelen data varsa message area kısmına yazdırıyoruz.
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while (sp.bytesAvailable() > 0)
		{
          byte[] readBuffer = new byte[sp.bytesAvailable()];
          int numRead = sp.readBytes(readBuffer, readBuffer.length);
          String commands = new String(readBuffer);
          System.out.println(commands);
          String[] commandArray = commands.split("\\n");
          //System.out.println(commandArray[0]);
          List<String> currentCommandList = Arrays.asList(commandArray);// hata var
          //System.out.println(currentCommandList);
          setCommand(commands);
          this.comHandler.executeCommand(currentCommandList);
        }
		
	}

	
	public void setCommand(String command) {
		this.command.set(command);
	}
	
	public String getCommand() {
		return command.get();
	}
	
	public StringProperty commandProtperty() {
		return this.command;
	}
	
	public CommandHandler getComHandler() {
		return comHandler;
	}

	public void setComHandler(CommandHandler comHandler) {
		this.comHandler = comHandler;
	}
	
	
	
	public SerialPort getSp() {
		return sp;
	}


	public void setSp(SerialPort sp) {
		this.sp = sp;
	}


	public OutputStream getOutputStream1() {
		return outputStream1;
	}


	public void setOutputStream1(OutputStream outputStream1) {
		this.outputStream1 = outputStream1;
	}


	public InputStream getInput() {
		return input;
	}


	public void setInput(InputStream input) {
		this.input = input;
	}

}
