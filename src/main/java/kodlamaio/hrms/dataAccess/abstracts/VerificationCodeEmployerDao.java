package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.VerificationCodeEmployer;

public interface VerificationCodeEmployerDao extends JpaRepository<VerificationCodeEmployer, Integer>{

	VerificationCodeEmployer getById(int id);
	
	VerificationCodeEmployer getByEmployer_UserId(int employerId);
}
