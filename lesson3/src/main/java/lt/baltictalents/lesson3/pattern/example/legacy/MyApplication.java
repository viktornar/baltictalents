package lt.baltictalents.lesson3.pattern.example.legacy;

public class MyApplication {

	private EmailService email = new EmailService();
	
	public void processMessages(String msg, String rec){
		// Do some msg validation, manipulation logic etc
		this.email.sendEmail(msg, rec);
	}
}
