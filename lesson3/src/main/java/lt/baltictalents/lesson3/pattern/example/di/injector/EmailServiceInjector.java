package lt.baltictalents.lesson3.pattern.example.di.injector;

import lt.baltictalents.lesson3.pattern.example.di.client.Client;
import lt.baltictalents.lesson3.pattern.example.di.client.MyDIApplication;
import lt.baltictalents.lesson3.pattern.example.di.service.EmailServiceImpl;

public class EmailServiceInjector implements MessageServiceInjector {

	@Override
	public Client getClient() {
		MyDIApplication app = new MyDIApplication();
		app.setService(new EmailServiceImpl());
		return app;
	}

}
