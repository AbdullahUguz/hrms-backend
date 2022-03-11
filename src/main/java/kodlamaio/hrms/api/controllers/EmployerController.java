package kodlamaio.hrms.api.controllers;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dto.EmployerDtoForRegister;

@RestController
@RequestMapping("/api/employers")
public class EmployerController {
	
	private EmployerService employerService;
	
	public EmployerController(EmployerService employerService) {
		this.employerService=employerService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Employer>> getAll(){
		
		return this.employerService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody EmployerDtoForRegister employerDtoForRegister) {
		
		return this.employerService.add(employerDtoForRegister);
	}
	
	@DeleteMapping("/delete/{employerId}")
	public Result  delete(@PathVariable int employerId) {
		return this.employerService.delete(employerId);
	}
	
	@PutMapping("/update/{employerId}")
	public DataResult<Employer>  update(@PathVariable int employerId,@RequestBody Employer employerInfo){
		
		return this.employerService.update(employerId, employerInfo);
	}

}
