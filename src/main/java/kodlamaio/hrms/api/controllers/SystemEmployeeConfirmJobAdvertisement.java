package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.ConfirmJobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/systemEmployeeConirmJobAdvertisements")
public class SystemEmployeeConfirmJobAdvertisement {

	private ConfirmJobAdvertisementService confirmJobAdvertisementService;
	
	@Autowired
	public SystemEmployeeConfirmJobAdvertisement(ConfirmJobAdvertisementService confirmJobAdvertisementService) {
		
		this.confirmJobAdvertisementService=confirmJobAdvertisementService;
	}
	
	@PostMapping("/confirm/{jobAdvertisementId}/{systemEmployeeId}")
	public Result confirmJobAdvertisement(@PathVariable int jobAdvertisementId,@PathVariable int systemEmployeeId) {
		
		return this.confirmJobAdvertisementService.confirmJobAdvertisement(jobAdvertisementId, systemEmployeeId);
	}
	
	
}
