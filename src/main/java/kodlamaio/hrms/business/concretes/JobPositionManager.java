package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements  JobPositionService{

	private JobPositionDao jobPositionDao;
	
	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		super();
		this.jobPositionDao=jobPositionDao;
	}
	
	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(), "Data Listed");
	}

	@Override
	public Result add(JobPosition jobPosition) {
		
		if(this.isJobTitleExist(jobPosition.getJobName())) {
			new ErrorResult("Job title is already exist !!!");
		}
		this.jobPositionDao.save(jobPosition);
		return new SuccessResult("Job title added");
	}
	
	private boolean isJobTitleExist(String jobName) {
		
		if(this.jobPositionDao.findByJobName(jobName) != null) {
			
			return true;
		}
		return false;
	}
	

}
