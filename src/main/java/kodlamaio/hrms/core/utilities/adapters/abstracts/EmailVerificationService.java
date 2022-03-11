package kodlamaio.hrms.core.utilities.adapters.abstracts;

import kodlamaio.hrms.entities.concretes.User;

public interface EmailVerificationService {
	
	boolean verify(User user);
	
	void sendVerifyEmail(User user,String code);
}
