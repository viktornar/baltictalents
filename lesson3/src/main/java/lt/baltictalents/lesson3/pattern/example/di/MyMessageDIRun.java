package lt.baltictalents.lesson3.pattern.example.di;

import lt.baltictalents.lesson3.pattern.example.di.client.Client;
import lt.baltictalents.lesson3.pattern.example.di.injector.EmailServiceInjector;
import lt.baltictalents.lesson3.pattern.example.di.injector.MessageServiceInjector;
import lt.baltictalents.lesson3.pattern.example.di.injector.SMSServiceInjector;

public class MyMessageDIRun {

	public static void main(String[] args) {
		String msg = "Hi Viktor";
		String email = "viktor@abc.com";
		String phone = "00000000000";
		MessageServiceInjector injector = null;
		Client app = null;
		
		// Send email
		injector = new EmailServiceInjector();
		app = injector.getClient();
		app.processMessages(msg, email);
		
		// Send SMS
		injector = new SMSServiceInjector();
		app = injector.getClient();
		app.processMessages(msg, phone);
	}

}
