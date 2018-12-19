package lt.baltictalents.lesson3.pattern.example.di.service;

public class EmailServiceImpl implements MessageService {

	@Override
	public void sendMessage(String msg, String rec) {
		// Logic to send email
		System.out.println("Email sent to "+rec+ " with Message="+msg);
	}

}
