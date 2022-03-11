package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateCheckService;
import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.adapters.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.dto.CandidateDtoForRegister;

@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;
	private CandidateCheckService candidateCheckService;
	private UserDao userDao;
	private EmailVerificationService emailVerificationService;
	private VerificationCodeService verificationCodeService;

	@Autowired
	public CandidateManager(CandidateDao candidateDao, CandidateCheckService candidateCheckService, UserDao userDao,
			EmailVerificationService emailVerificationService, VerificationCodeService verificationCodeService) {

		this.candidateDao = candidateDao;
		this.candidateCheckService = candidateCheckService;
		this.userDao = userDao;
		this.emailVerificationService = emailVerificationService;
		this.verificationCodeService = verificationCodeService;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), "Data Listed");
	}

	@Override
	public Result add(CandidateDtoForRegister candidateDtoForRegister) {

		Candidate candidate = candidateDtoConvertCandidate(candidateDtoForRegister);

		if (!this.candidateCheckService.checkIfRealPerson(candidate)) {

			return new ErrorResult("Invalid Person !!!");
		}

		if (this.isEmailExist(candidate.getEmail())) {
			return new ErrorResult("Email already exist !!!");
		}

		if (this.isIdentityExist(candidate.getIdentityNumber())) {
			return new ErrorResult("Identity number already exist !!!");
		}
		this.candidateDao.save(candidate);
		this.emailVerificationService.sendVerifyEmail(candidate, this.verificationCodeService.createVerificationCode());

		return new SuccessResult("Candidate added , Waiting email verify");
	}

	@Override
	public Result delete(int candidateId) {
		this.candidateDao.deleteById(candidateId);
		return new SuccessResult("Candidate deleted");
	}
	
	@Override
	public DataResult<Candidate> update(int candidateId, Candidate candidateInfo) {
		
		Candidate candidate=this.candidateDao.getByUserId(candidateId);
		
		candidate.setFirstName(candidateInfo.getFirstName());
		candidate.setLastName(candidateInfo.getLastName());
		candidate.setIdentityNumber(candidateInfo.getIdentityNumber());
		candidate.setBirthOfDate(candidateInfo.getBirthOfDate());
		candidate.setEmail(candidateInfo.getEmail());
		candidate.setPassword(candidateInfo.getPassword());
		
		this.candidateDao.save(candidate);
		// buraya bı kontrol ekleyebilirsin illa karşıdan gelen değişcek diye  bir şey yok
		return new SuccessDataResult<Candidate>(candidate,"Candidate updated");
	}

	



	private boolean isEmailExist(String email) {

		if (this.userDao.findByEmail(email) != null) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isIdentityExist(String identityNumber) {

		if (this.candidateDao.findByIdentityNumber(identityNumber) != null) {
			return true;
		} else {
			return false;
		}
	}

	private Candidate candidateDtoConvertCandidate(CandidateDtoForRegister candidateDtoForRegister) {

		Candidate candidate = new Candidate();

		candidate.setFirstName(candidateDtoForRegister.getFirstName());
		candidate.setLastName(candidateDtoForRegister.getLastName());
		candidate.setBirthOfDate(candidateDtoForRegister.getDateOfBirth());
		candidate.setEmail(candidateDtoForRegister.getEmail());
		candidate.setIdentityNumber(candidateDtoForRegister.getIdentityNumber());
		candidate.setPassword(candidateDtoForRegister.getPassword());
		return candidate;
	}

	
	
}
