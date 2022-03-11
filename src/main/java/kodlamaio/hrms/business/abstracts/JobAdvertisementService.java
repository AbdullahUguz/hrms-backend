package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dto.JobAdvertisementDto;

public interface JobAdvertisementService {
	
	DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisements();
	
	DataResult<List<JobAdvertisement>> getAllActiveByEmployer(int employerId);
	
	DataResult<List<JobAdvertisement>> getAllSorted();
	
	Result add(JobAdvertisementDto jobAdvertisementDto);
	
	Result setActivation(int jobAdvertisementId);
	
	Result passiveActivation(int jobAdvertisementId);
	
	
	DataResult<JobAdvertisement> getByAdvertisement(int id);

}
