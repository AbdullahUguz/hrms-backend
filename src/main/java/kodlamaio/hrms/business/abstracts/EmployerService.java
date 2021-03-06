package kodlamaio.hrms.business.abstracts;


import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dto.EmployerDtoForRegister;

public interface EmployerService {

	DataResult<List<Employer>> getAll();
	
	Result add(EmployerDtoForRegister employerDtoForRegister);
	
	Result delete(int employerId);
	
	DataResult<Employer> update(int employerId,Employer employerInfo);
}
