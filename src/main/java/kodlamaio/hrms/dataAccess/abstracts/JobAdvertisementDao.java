package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement,Integer>{
	
	JobAdvertisement getByAdvertisementId(int jobAdvertisementId);
	
	@Query("from JobAdvertisement ja where ja.isActive=true")
	List<JobAdvertisement> getAllActiveJobAdvertisements();
	
	
	@Query("from JobAdvertisement ja where ja.employer.userId=:employerId")
	List<JobAdvertisement> getAllActiveByEmployer(@Param("employerId") int employerId);
	
	@Query("from JobAdvertisement ja where ja.isActive= true Order By ja.applicationDeadline DESC")
	List<JobAdvertisement> getAllSorted();
	
} 
