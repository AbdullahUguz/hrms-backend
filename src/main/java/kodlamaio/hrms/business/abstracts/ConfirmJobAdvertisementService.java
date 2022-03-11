package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface ConfirmJobAdvertisementService {


	void createConfirmJobAdvertisement(JobAdvertisement jobAdvertisement);
	
	Result confirmJobAdvertisement(int jobAdvertisementId,int systemEmployeeId);

}
