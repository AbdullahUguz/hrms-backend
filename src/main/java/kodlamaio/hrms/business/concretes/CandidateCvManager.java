package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateCvService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateCvDao;
import kodlamaio.hrms.entities.concretes.CandidateCv;

@Service
public class CandidateCvManager implements CandidateCvService{

	
	private CandidateCvDao cvDao;
	
	@Autowired
	public CandidateCvManager(CandidateCvDao cvDao) {
		this.cvDao=cvDao;
	}

	@Override
	public Result add(CandidateCv cv) {
		
		this.cvDao.save(cv);
	/*	candidateCv.setCandidate(cv.getCandidate());
		candidateCv.setCoverLetter(cv.getCoverLetter());
		candidateCv.setGithub(cv.getGithub());
		candidateCv.setImage(cv.getImage());
		candidateCv.setKnownLanguages(cv.getKnownLanguages());
		candidateCv.setKnownTechnologies(cv.getKnownTechnologies());
		candidateCv.setLinkedin(cv.getLinkedin());
		candidateCv.setSchools(cv.getSchools());
		candidateCv.setWorkExperiences(cv.getWorkExperiences());
	*/	
		return new SuccessResult("Cv added");
	}

	@Override
	public DataResult<List<CandidateCv>> getAll() {
		
		return new SuccessDataResult<List<CandidateCv>>(this.cvDao.findAll(), "Cv listed");
	}
	
}
