package lt.baltictalents.lesson3.service;

public interface MessageService {
	void sendMessage(String msg, String rec);
	String getMessage(String msg, String rec);
}
