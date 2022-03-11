package kodlamaio.hrms.api.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.ConfirmEmployerService;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/systemEmployeeConfirmEmployers")
public class SystemEmployeeConfirmEmployer {

	private ConfirmEmployerService confirmEmployerService;
	
	public SystemEmployeeConfirmEmployer(ConfirmEmployerService confirmEmployerService) {
		this.confirmEmployerService=confirmEmployerService;
	}
	
	@PostMapping("/confirm/{employerId}/{systemEmployeeId}")
	public Result confirmEmployer(@PathVariable int employerId,@PathVariable int systemEmployeeId) {
		
		return this.confirmEmployerService.confirmEmployer(employerId, systemEmployeeId);
	}
}
