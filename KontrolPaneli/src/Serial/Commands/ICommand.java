package Serial.Commands;

public interface ICommand {
	
	void setParameter(String parameter);
	void execute();
}
