package cobas.coding.lol_a_z_backend.repository;

import cobas.coding.lol_a_z_backend.model.SystemInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemInformationRepository extends JpaRepository<SystemInformation, Long> {
	SystemInformation findTopByOrderByCreateDateDesc();
}