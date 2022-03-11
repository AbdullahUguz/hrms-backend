package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.dto.CandidateDtoForRegister;

public interface CandidateService {

	DataResult<List<Candidate>> getAll();
	
	Result add(CandidateDtoForRegister candidateDtoForRegister);
	
	Result delete(int candidateId);
	
	DataResult<Candidate> update(int candidateId,Candidate candidateInfo);
	
}
