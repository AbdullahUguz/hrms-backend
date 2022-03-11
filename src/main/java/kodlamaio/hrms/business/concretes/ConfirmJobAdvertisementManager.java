package kodlamaio.hrms.business.concretes;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ConfirmJobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ConfirmJobAdvertisementDao;
import kodlamaio.hrms.dataAccess.abstracts.SystemEmployeeDao;
import kodlamaio.hrms.entities.concretes.ConfirmJobAdvertisement;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class ConfirmJobAdvertisementManager implements ConfirmJobAdvertisementService{

	private ConfirmJobAdvertisementDao confirmJobAdvertisementDao;
	private SystemEmployeeDao systemEmployeeDao;
	
	@Autowired
	public ConfirmJobAdvertisementManager(ConfirmJobAdvertisementDao confirmJobAdvertisementDao
			,SystemEmployeeDao systemEmployeeDao) {
		this.confirmJobAdvertisementDao=confirmJobAdvertisementDao;
		this.systemEmployeeDao=systemEmployeeDao;
	}

	@Override
	public void createConfirmJobAdvertisement(JobAdvertisement jobAdvertisement) {
		
		ConfirmJobAdvertisement confirmJobAdvertisement=new ConfirmJobAdvertisement();
		
		confirmJobAdvertisement.setJobAdvertisement(jobAdvertisement);
		confirmJobAdvertisement.setConfirmed(false);
		confirmJobAdvertisement.setSystemEmployee(null);
		
		this.confirmJobAdvertisementDao.save(confirmJobAdvertisement);
		
	}

	@Override
	public Result confirmJobAdvertisement(int jobAdvertisementId, int systemEmployeeId) {

		try {
			ConfirmJobAdvertisement confirmJobAdvertisement=this.confirmJobAdvertisementDao.getByJobAdvertisement_AdvertisementId(jobAdvertisementId);
			
			confirmJobAdvertisement.setConfirmDate(new Date());
			confirmJobAdvertisement.setSystemEmployee(this.systemEmployeeDao.getByUserId(systemEmployeeId));
			confirmJobAdvertisement.setConfirmed(true);
			
			this.confirmJobAdvertisementDao.save(confirmJobAdvertisement);
			
		} catch (EntityNotFoundException e) {
			
			return new ErrorResult("Wrong id");
		}
		return new SuccessResult("Job Advertisement Confirmed");
	}


}
