package lt.baltictalents.lesson3.service;

import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements MessageService {
	@Override
	public void sendMessage(String msg, String rec) {
		// Logic to send email
		System.out.println("Email sent to "+rec+ " with Message="+msg);
	}

	@Override
	public String getMessage(String msg, String rec) {
		// Logic to send email
		return "Email sent to " + rec + " with Message=" + msg;
	}
}
