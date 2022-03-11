package kodlamaio.hrms.business.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateCheckService;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateCheckManager implements CandidateCheckService{

	@Override
	public boolean checkIfRealPerson(Candidate candidate) {
		return true;
	}

}
