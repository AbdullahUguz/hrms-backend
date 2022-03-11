package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.ConfirmJobAdvertisement;

public interface ConfirmJobAdvertisementDao extends JpaRepository<ConfirmJobAdvertisement, Integer> {

	ConfirmJobAdvertisement getByJobAdvertisement_AdvertisementId(int jobAdvertisementId);
}
