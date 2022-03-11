package kodlamaio.hrms.core.utilities.adapters.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.utilities.adapters.abstracts.EmailVerificationService;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class EmailVerificationManager implements EmailVerificationService{

	
	private JavaMailSender emailSender;
	
	@Autowired
	public EmailVerificationManager(JavaMailSender emailSender) {
		
		this.emailSender=emailSender;
	}
	
	@Override
	public boolean verify(User user) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void sendVerifyEmail(User user, String code) {
		
		SimpleMailMessage  message=new SimpleMailMessage();
		
		message.setSubject("HRMS mail verification");
		message.setText("Hrms to complete your registration :"
				+ "http://localhost:8080/api/verificationcodes/verify/"+user.getUserId()+"/"+code);
		message.setTo(user.getEmail());
		message.setFrom("website.hrms@gmail.com");
		emailSender.send(message);
		
	}
	

}
