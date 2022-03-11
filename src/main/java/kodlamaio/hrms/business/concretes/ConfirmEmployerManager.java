package kodlamaio.hrms.business.concretes;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ConfirmEmployerService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ConfirmEmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.SystemEmployeeDao;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.ConfirmEmployer;

@Service
public class ConfirmEmployerManager implements ConfirmEmployerService {

	private ConfirmEmployerDao confirmEmployerDao;
	private SystemEmployeeDao systemEmployeeDao;

	@Autowired
	public ConfirmEmployerManager(ConfirmEmployerDao confirmEmployerDao,
			SystemEmployeeDao systemEmployeeDao) {

		this.confirmEmployerDao = confirmEmployerDao;
		this.systemEmployeeDao = systemEmployeeDao;
	}
	
	@Override
	public void createConfirmEmployer(Employer employer) {
		ConfirmEmployer confirmEmployer=new ConfirmEmployer();
		
		confirmEmployer.setEmployer(employer);
		confirmEmployer.setConfirmed(false);
		confirmEmployer.setSystemEmployee(null);
		
		confirmEmployerDao.save(confirmEmployer);
		
	}
	@Override
	public Result confirmEmployer(int employerId, int systemEmployeeId) {
		
		try {
			ConfirmEmployer confirmEmployer=this.confirmEmployerDao.getByEmployer_UserId(employerId);
				
			confirmEmployer.setConfirmed(true);
			confirmEmployer.setConfirmDate(new Date());
			confirmEmployer.setSystemEmployee(this.systemEmployeeDao.getByUserId(systemEmployeeId));
			
			this.confirmEmployerDao.save(confirmEmployer);
			
			
		} catch (EntityNotFoundException e) {
			
			return new ErrorResult("Wrong id");
		}
		
		return new SuccessResult("Employer confirmed");
		
	}

}
