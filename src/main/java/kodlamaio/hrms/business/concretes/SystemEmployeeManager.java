package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SystemEmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SystemEmployeeDao;
import kodlamaio.hrms.entities.concretes.SystemEmployee;

@Service
public class SystemEmployeeManager implements SystemEmployeeService{

	private SystemEmployeeDao systemEmployeeDao;
	
	@Autowired
	public SystemEmployeeManager(SystemEmployeeDao systemEmployeeDao) {
		this.systemEmployeeDao=systemEmployeeDao;
	}
	
	@Override
	public DataResult<List<SystemEmployee>> getAll() {
		
		return new SuccessDataResult<>(this.systemEmployeeDao.findAll(),"Data Listed");
	}

	@Override
	public Result add(SystemEmployee systemEmployee) {
		
		this.systemEmployeeDao.save(systemEmployee);
		return new SuccessResult("System employee added");
	}

	@Override
	public Result delete(int sysytemEmployeeId) {
		this.systemEmployeeDao.deleteById(sysytemEmployeeId);
		return new SuccessResult("System Employee deleted");
	}

	@Override
	public DataResult<SystemEmployee> update(int systemEmployeeId, SystemEmployee systemEmployeeInfo) {
		
		SystemEmployee systemEmployee=this.systemEmployeeDao.getByUserId(systemEmployeeId);
		
		systemEmployee.setEmail(systemEmployeeInfo.getEmail());
		systemEmployee.setFirstName(systemEmployeeInfo.getFirstName());
		systemEmployee.setLastName(systemEmployeeInfo.getLastName());
		systemEmployee.setPassword(systemEmployeeInfo.getPassword());
		
		this.systemEmployeeDao.save(systemEmployee);
		
		return new SuccessDataResult<SystemEmployee>(systemEmployee, "System employee updated");
	}
	
	

}
