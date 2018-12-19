package lt.baltictalents.lesson3.pattern.example.di.service;

public class SMSServiceImpl implements MessageService {

	@Override
	public void sendMessage(String msg, String rec) {
		// Logic to send SMS
		System.out.println("SMS sent to "+rec+ " with Message="+msg);
	}

}
