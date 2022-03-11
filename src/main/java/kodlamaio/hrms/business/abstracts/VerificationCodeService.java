package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface VerificationCodeService {

	String createVerificationCode();
	
	Result verifyUser(int userId,String code);
}
