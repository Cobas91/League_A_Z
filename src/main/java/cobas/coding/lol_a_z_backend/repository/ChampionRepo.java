package cobas.coding.lol_a_z_backend.repository;

import cobas.coding.lol_a_z_backend.model.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface ChampionRepo extends JpaRepository<Champion, String> {

}
