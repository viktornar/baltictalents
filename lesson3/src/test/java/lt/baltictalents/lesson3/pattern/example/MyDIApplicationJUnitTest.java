package lt.baltictalents.lesson3.pattern.example;

import lt.baltictalents.lesson3.pattern.example.di.client.Client;
import lt.baltictalents.lesson3.pattern.example.di.client.MyDIApplication;
import lt.baltictalents.lesson3.pattern.example.di.injector.MessageServiceInjector;
import lt.baltictalents.lesson3.pattern.example.di.service.MessageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyDIApplicationJUnitTest {

	private MessageServiceInjector injector;

	@Before
	public void setUp(){
		//mock the injector with anonymous class
		injector = new MessageServiceInjector() {
			
			@Override
			public Client getClient() {
				//mock the message service
				return new MyDIApplication(new MessageService() {
					
					@Override
					public void sendMessage(String msg, String rec) {
						System.out.println("Mock Message Service implementation");
						
					}
				});
			}
		};
	}
	
	@Test
	public void test() {
		Client client = injector.getClient();
		client.processMessages("Hi Viktor", "viktor@abc.com");
	}
	
	@After
	public void tear(){
		injector = null;
	}

}
