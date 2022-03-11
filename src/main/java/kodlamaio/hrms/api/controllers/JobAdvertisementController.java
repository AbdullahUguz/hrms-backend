package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dto.JobAdvertisementDto;

@RestController
@RequestMapping("/api/jobAdvertisements")
public class JobAdvertisementController {

	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementController(JobAdvertisementService jobAdvertisementService) {
		this.jobAdvertisementService = jobAdvertisementService;
	}

	@GetMapping("/getAllActiveJobAds")
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisements() {

		return this.jobAdvertisementService.getAllActiveJobAdvertisements();
	}

	@GetMapping("/getAllActiveJobAds/{employerId}")
	public DataResult<List<JobAdvertisement>> getAllActiveByEmployer(@PathVariable int employerId) {

		return this.jobAdvertisementService.getAllActiveByEmployer(employerId);
	}

	@GetMapping("/getAllSorted")
	public DataResult<List<JobAdvertisement>> getAllSorted() {

		return this.jobAdvertisementService.getAllSorted();
	}

	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisementDto jobAdvertisementDto) {

		return this.jobAdvertisementService.add(jobAdvertisementDto);
	}

	@PostMapping("/setActivation/{jobAdvertisementId}")
	public Result setActivation(@PathVariable int jobAdvertisementId) {

		return this.jobAdvertisementService.setActivation(jobAdvertisementId);
	}

	@PostMapping("/passiveActivation/{jobAdvertisementId}")
	public Result passiveActivation(@PathVariable int jobAdvertisementId) {

		return this.jobAdvertisementService.passiveActivation(jobAdvertisementId);
	}

	@GetMapping("/getByAdvertisementId")
	public DataResult<JobAdvertisement> getByAdvertisement(@RequestParam int id) {

		return this.jobAdvertisementService.getByAdvertisement(id);
	}
}
