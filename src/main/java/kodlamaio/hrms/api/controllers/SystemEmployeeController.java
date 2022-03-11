package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.SystemEmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SystemEmployee;

@RestController
@RequestMapping("api/SystemEmployees")
public class SystemEmployeeController {

	private SystemEmployeeService systemEmployeeService;
	
	@Autowired
	public SystemEmployeeController(SystemEmployeeService systemEmployeeService) {
		this.systemEmployeeService = systemEmployeeService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<SystemEmployee>> getAll() {
		
		return this.systemEmployeeService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody SystemEmployee systemEmployee) {
		
		return this.systemEmployeeService.add(systemEmployee);
	}
	
	@DeleteMapping("/delete/{systemEmployeeId}")
	public Result delete(@PathVariable int systemEmployeeId) {
		
		return this.systemEmployeeService.delete(systemEmployeeId);
	}
	
	@PutMapping("/update/{systemEmployeeId}")
	public DataResult<SystemEmployee> update(@PathVariable int systemEmployeeId,@RequestBody SystemEmployee systemEmployeeInfo){
		
		return this.systemEmployeeService.update(systemEmployeeId, systemEmployeeInfo);
	}
}
