package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CandidateCvService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CandidateCv;

@RestController
@RequestMapping("/CandidateCv")
public class CandidateCvController {

	private CandidateCvService cvService;

	@Autowired
	public CandidateCvController(CandidateCvService cvService) {
		this.cvService = cvService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CandidateCv cv) {

		return this.cvService.add(cv);
	}

	@GetMapping("/getAll")
	public DataResult<List<CandidateCv>> getAll() {

		return this.cvService.getAll();
	}
}
