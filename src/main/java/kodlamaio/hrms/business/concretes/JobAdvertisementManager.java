package kodlamaio.hrms.business.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ConfirmJobAdvertisementService;
import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dto.JobAdvertisementDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdvertisementDao jobAdvertisementDao;
	private CityDao cityDao;
	private EmployerDao employerDao;
	private JobPositionDao jobPositionDao;
	private ConfirmJobAdvertisementService confirmJobAdvertisementService;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, CityDao cityDao, EmployerDao employerDao,
			JobPositionDao jobPositionDao, ConfirmJobAdvertisementService confirmJobAdvertisementService) {

		this.jobAdvertisementDao = jobAdvertisementDao;
		this.cityDao = cityDao;
		this.employerDao = employerDao;
		this.jobPositionDao = jobPositionDao;
		this.confirmJobAdvertisementService = confirmJobAdvertisementService;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisements() {
		
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllActiveJobAdvertisements(),
				"Active Job Advertisements Listed");
	}
	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveByEmployer(int employerId) {
		
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllActiveByEmployer(employerId),
				"Active Job Advertisements Listed By Employer ");
	}
	@Override
	public DataResult<List<JobAdvertisement>> getAllSorted() {
	
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllSorted(),
				"Job Advertisements sorted by application deadline ");
	}
	

	@Override
	public Result add(JobAdvertisementDto jobAdvertisementDto) {

		JobAdvertisement jobAdvertisement = this.jobAdDtoConvertjobAd(jobAdvertisementDto);

		this.jobAdvertisementDao.save(jobAdvertisement);
		this.confirmJobAdvertisementService.createConfirmJobAdvertisement(jobAdvertisement);

		return new SuccessResult("Job advertisement added.Waiting system employee confirm");
	}

	private JobAdvertisement jobAdDtoConvertjobAd(JobAdvertisementDto jobAdvertisementDto) {

		JobAdvertisement jobAdvertisement = new JobAdvertisement();

		jobAdvertisement.setApplicationDeadline(jobAdvertisementDto.getApplicationDeadLine());
		jobAdvertisement.setCity(this.cityDao.getByCityId(jobAdvertisementDto.getCityId()));
		jobAdvertisement.setCreationDate(new Date());
		jobAdvertisement.setEmployer(this.employerDao.getByUserId(jobAdvertisementDto.getEmployerId()));
		jobAdvertisement.setJobDescription(jobAdvertisementDto.getDescription());
		jobAdvertisement.setJobPosition(this.jobPositionDao.getByJobPositionId(jobAdvertisementDto.getJobPositionId()));
		jobAdvertisement.setMaxSalary(jobAdvertisementDto.getMaxSalary());
		jobAdvertisement.setMinSalary(jobAdvertisementDto.getMinSalary());
		jobAdvertisement.setNumberOfOpenPosition(jobAdvertisementDto.getNumberOfOpenPosition());

		jobAdvertisement.setActive(false);

		return jobAdvertisement;
	}

	@Override
	public Result setActivation(int jobAdvertisementId) {

		JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.getByAdvertisementId(jobAdvertisementId);

		jobAdvertisement.setActive(true);
		this.jobAdvertisementDao.save(jobAdvertisement);

		return new SuccessResult("Advertisement active");
	}

	@Override
	public Result passiveActivation(int jobAdvertisementId) {
		JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.getByAdvertisementId(jobAdvertisementId);

		jobAdvertisement.setActive(false);
		this.jobAdvertisementDao.save(jobAdvertisement);

		return new SuccessResult("Advertisement passive");
	}

	@Override
	public DataResult<JobAdvertisement> getByAdvertisement(int id) {
		
		return new SuccessDataResult<JobAdvertisement>(this.jobAdvertisementDao.getByAdvertisementId(id), "Data geldi");
	}



	


	

}
