package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.ConfirmEmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.SystemEmployeeDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeCandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeEmployerDao;
import kodlamaio.hrms.entities.concretes.ConfirmEmployer;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.concretes.VerificationCodeCandidate;
import kodlamaio.hrms.entities.concretes.VerificationCodeEmployer;

@Service
public class UserManager implements UserService {

	private UserDao userDao;
	private CandidateDao candidateDao;
	private EmployerDao employerDao;
	private SystemEmployeeDao systemEmployeeDao;
	private VerificationCodeCandidateDao verificationCodeCandidateDao;
	private VerificationCodeEmployerDao verificationCodeEmployerDao;
	private ConfirmEmployerDao confirmEmployerDao;

	@Autowired
	public UserManager(UserDao userDao, CandidateDao candidateDao, EmployerDao employerDao,
			SystemEmployeeDao systemEmployeeDao, VerificationCodeCandidateDao verificationCodeCandidateDao,
			VerificationCodeEmployerDao verificationCodeEmployerDao,
			ConfirmEmployerDao confirmEmployerDao) {

		this.userDao = userDao;
		this.candidateDao = candidateDao;
		this.employerDao = employerDao;
		this.verificationCodeCandidateDao = verificationCodeCandidateDao;
		this.verificationCodeEmployerDao = verificationCodeEmployerDao;
		this.confirmEmployerDao = confirmEmployerDao;
	}

	@Override
	public Result login(User user) {

		User userr = this.userDao.findByEmail(user.getEmail());
		

		if (userr == null) {
			return new ErrorResult("Wrong email");
		}

		if (!user.getPassword().equals(userr.getPassword())) {
			return new ErrorResult("Wrong password");
		}

		if (this.candidateDao.getByUserId(userr.getUserId()) != null) {

			return this.candidateVerifyControl(userr.getUserId());

		} else if (this.employerDao.getByUserId(userr.getUserId()) != null) {
			
			return this.employerVerifyControl(userr.getUserId());
			
		} else if(this.systemEmployeeDao.getByUserId(userr.getUserId()) !=null) {
			
			return new SuccessResult("Login Successful By Admin");
		}else {
			
			return new ErrorResult("There is a problem");
		}
	}

	private Result candidateVerifyControl(int userId) {

		VerificationCodeCandidate verificationCodeCandidate = this.verificationCodeCandidateDao
				.getByCandidate_UserId(userId);

		if (!verificationCodeCandidate.isVerified()) {
			return new ErrorResult("Email not verified");
		} else {
			return new SuccessResult("Login Successful");
		}

	}

	private Result employerVerifyControl(int userId) {

		VerificationCodeEmployer verificationCodeEmployer = this.verificationCodeEmployerDao
				.getByEmployer_UserId(userId);
		ConfirmEmployer confirmEmployer = this.confirmEmployerDao
				.getByEmployer_UserId(userId);

		if (!verificationCodeEmployer.isVerified()) {
			return new ErrorResult("Email not verify");
		} else if (!confirmEmployer.isConfirmed()) {
			return new ErrorResult("System Employee not confirm");
		} else {
			return new SuccessResult("Login Successful");
		}

	}

}
