package cobas.coding.lol_a_z_backend.security.repo;

import cobas.coding.lol_a_z_backend.security.model.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository public interface SummonerRepo extends JpaRepository<Summoner, String> {
	Optional<Summoner> findByUsernameEquals(String username);

	Optional<Summoner> findByEmailEquals(String email);

	Optional<Summoner> findByPasswordResetTokenEquals(String token);
}
