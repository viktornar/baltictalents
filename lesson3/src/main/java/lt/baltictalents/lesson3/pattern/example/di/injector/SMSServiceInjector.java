package lt.baltictalents.lesson3.pattern.example.di.injector;

import lt.baltictalents.lesson3.pattern.example.di.client.Client;
import lt.baltictalents.lesson3.pattern.example.di.client.MyDIApplication;
import lt.baltictalents.lesson3.pattern.example.di.service.SMSServiceImpl;

public class SMSServiceInjector implements MessageServiceInjector {

	@Override
	public Client getClient() {
		return new MyDIApplication(new SMSServiceImpl());
	}

}
