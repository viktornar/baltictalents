package lt.baltictalents.lesson3.pattern.example.legacy;

public class EmailService {

	public void sendEmail(String message, String receiver){
		// Logic to send email
		System.out.println("Email sent to "+receiver+ " with Message="+message);
	}
}
