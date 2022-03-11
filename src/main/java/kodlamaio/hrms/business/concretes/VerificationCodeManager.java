package kodlamaio.hrms.business.concretes;

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeCandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeDao;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeEmployerDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.VerificationCodeCandidate;
import kodlamaio.hrms.entities.concretes.VerificationCodeEmployer;

@Service
public class VerificationCodeManager implements VerificationCodeService {

	protected VerificationCodeDao verificationCodeDao;
	protected EmployerDao employerDao;
	protected CandidateDao candidateDao;

	private VerificationCodeCandidateDao verificationCodeCandidateDao;
	private VerificationCodeEmployerDao verificationCodeEmployerDao;

	private String verifyCode = "";
	private SecureRandom mixer = new SecureRandom();
	private final String codeCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	@Autowired
	public VerificationCodeManager(VerificationCodeDao verificationCodeDao
			, EmployerDao employerDao,CandidateDao candidateDao
			, VerificationCodeCandidateDao verificationCodeCandidateDao
			, VerificationCodeEmployerDao verificationCodeEmployerDao) {

		this.verificationCodeDao = verificationCodeDao;
		this.verificationCodeCandidateDao=verificationCodeCandidateDao;
		this.verificationCodeEmployerDao=verificationCodeEmployerDao;
		this.employerDao = employerDao;
		this.candidateDao = candidateDao;
		
	}

	@Override
	public Result verifyUser(int userId, String code) {

		if (this.employerDao.getByUserId(userId) != null) {
			return this.verifyEmployer(this.employerDao.getByUserId(userId), code);
		} else {
			return this.verifyCandidate(this.candidateDao.getByUserId(userId), code);
		}
	}

	public Result verifyCandidate(Candidate candidate, String code) {
		
		VerificationCodeCandidate verificationCodeCandidate= new VerificationCodeCandidate();
		
		verificationCodeCandidate.setCandidate(candidate);
		verificationCodeCandidate.setVerified(true);
		verificationCodeCandidate.setCode(code);
		verificationCodeCandidate.setVerifiedDate(new Date());

		this.verificationCodeCandidateDao.save(verificationCodeCandidate);

		return new SuccessResult("Candidate Verified");
	}
	
	public Result verifyEmployer(Employer employer, String code) {
	
		VerificationCodeEmployer verificationCodeEmployer= new VerificationCodeEmployer();
		
		verificationCodeEmployer.setEmployer(employer);
		verificationCodeEmployer.setVerified(true);
		verificationCodeEmployer.setCode(code);
		verificationCodeEmployer.setVerifiedDate(new Date());

		this.verificationCodeEmployerDao.save(verificationCodeEmployer);
		return new SuccessResult("Employer Verified");
	}

	@Override
	public String createVerificationCode() {

		verifyCode = randomValueProvider(4);

		if (!isCodeExist(verifyCode)) {
			
			return verifyCode;
		} else {

			new IllegalArgumentException("code not valid");
			return null;
		}

	}

	private String randomValueProvider(int length) {

		StringBuilder setRandomValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			setRandomValue.append(codeCharacters.charAt(mixer.nextInt(codeCharacters.length())));
		}

		return setRandomValue.toString();
	}

	protected boolean isCodeExist(String code) {

		if (this.verificationCodeDao.getByCode(code) == null) {
			return false;

		} else {
			return true;
		}

	}

}
