package lt.baltictalents.lesson3.service;

import org.springframework.stereotype.Service;

@Service
public class SMSServiceImpl implements MessageService {
	@Override
	public void sendMessage(String msg, String rec) {
		// Logic to send SMS
		System.out.println("SMS sent to "+rec+ " with Message="+msg);
	}

	@Override
	public String getMessage(String msg, String rec) {
		return "SMS sent to " + rec + " with Message=" + msg;
	}

}
