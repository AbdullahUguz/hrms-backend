package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SystemEmployee;

public interface SystemEmployeeService {
	
	DataResult<List<SystemEmployee>> getAll();
	
	Result add(SystemEmployee systemEmployee);
	
	Result delete(int sysytemEmployeeId);
	
	DataResult<SystemEmployee> update(int systemEmployeeId ,SystemEmployee systemEmployeeInfo);
}
