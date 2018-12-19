package lt.baltictalents.lesson3.pattern.example.di.client;

import lt.baltictalents.lesson3.pattern.example.di.service.MessageService;

public class MyDIApplication implements Client {

	private MessageService service;
	
	public MyDIApplication(MessageService service){
		this.service = service;
	}
	
	public MyDIApplication(){}
	
	public void setService(MessageService service) {
		this.service = service;
	}

	@Override
	public void processMessages(String msg, String rec){
		// Do some msg validation, manipulation logic etc
		this.service.sendMessage(msg, rec);
	}

}
