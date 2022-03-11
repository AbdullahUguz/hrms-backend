package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.ConfirmEmployerService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.adapters.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dto.EmployerDtoForRegister;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	private EmailVerificationService emailVerificationService;
	private UserDao userDao;
	private ConfirmEmployerService confirmEmployerService;
	private VerificationCodeService verificationCodeService;


	@Autowired
	public EmployerManager(EmployerDao employerDao, UserDao userDao
			,EmailVerificationService emailVerificationService
			,ConfirmEmployerService confirmEmployerService
			,VerificationCodeService verificationCodeService) {
		
		this.employerDao=employerDao;
		this.userDao = userDao;
		this.emailVerificationService = emailVerificationService;
		this.confirmEmployerService=confirmEmployerService;
		this.verificationCodeService=verificationCodeService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {

		return new SuccessDataResult<>(this.employerDao.findAll(), "Data Listed");
	}

	@Override
	public Result add(EmployerDtoForRegister employerDtoForRegister) {
		Employer employer = employerDtoConvertEmployer(employerDtoForRegister);

		if(this.isEmailExist(employer.getEmail())) {
			return new ErrorResult("Email is already exist !!!");
		}
		
		this.employerDao.save(employer);
		
		this.emailVerificationService.sendVerifyEmail(employer, this.verificationCodeService.createVerificationCode());
		this.confirmEmployerService.createConfirmEmployer(employer);

		return new SuccessResult("Employer added.Waiting email verify send email to "+employer.getEmail()+".Waiting system employee confirm");
	}
	
	@Override
	public Result delete(int employerId) {
		this.employerDao.deleteById(employerId);
		return new SuccessResult("Employer deleted");
	}
	
	@Override
	public DataResult<Employer> update(int employerId, Employer employerInfo) {
		
		Employer employer=this.employerDao.getByUserId(employerId);
		
		employer.setCompanyName(employerInfo.getCompanyName());
		employer.setEmail(employerInfo.getEmail());
		employer.setPassword(employerInfo.getPassword());
		employer.setPhoneNumber(employerInfo.getPhoneNumber());
		employer.setWebAddress(employerInfo.getWebAddress());
		
		
		this.employerDao.save(employer);
		
		return new SuccessDataResult<Employer>(employer, "Employer updated");
	}

	

	private Employer employerDtoConvertEmployer(EmployerDtoForRegister employerDtoForRegister) {

		Employer employer = new Employer();

		employer.setCompanyName(employerDtoForRegister.getCompanyName());
		employer.setEmail(employerDtoForRegister.getEmail());
		employer.setPassword(employerDtoForRegister.getPassword());
		employer.setPhoneNumber(employerDtoForRegister.getPhoneNumber());
		employer.setWebAddress(employerDtoForRegister.getWebAddress());

		return employer;
	}

	private boolean isEmailExist(String email) {

		if (this.userDao.findByEmail(email) != null) {
			return true;
		} else {
			return false;
		}
	}

	


}
