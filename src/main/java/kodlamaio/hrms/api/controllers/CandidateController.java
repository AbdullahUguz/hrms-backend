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

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.dto.CandidateDtoForRegister;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

	private CandidateService candidateService;

	@Autowired
	public CandidateController(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	@GetMapping("/getAll")
	public DataResult<List<Candidate>> getAll() {
		return this.candidateService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody CandidateDtoForRegister candidateDtoForRegister) {

		return this.candidateService.add(candidateDtoForRegister);
	}

	@DeleteMapping("/delete/{candidateId}")
	public Result delete(@PathVariable int candidateId) {
		return this.candidateService.delete(candidateId);
	}

	@PutMapping("/update/{candidateId}")
	public DataResult<Candidate> update(@PathVariable int candidateId,
			@RequestBody Candidate candidateInfo) {

		return this.candidateService.update(candidateId, candidateInfo);
	}

}
