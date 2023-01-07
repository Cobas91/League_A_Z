package cobas.coding.lol_a_z_backend.security.model;

import cobas.coding.lol_a_z_backend.model.Champion;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder @Entity @Getter @Setter public class Summoner {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private long id;
	private String username;
	private String password;
	private String email;
	private String passwordResetToken;

	@ManyToMany(cascade = CascadeType.ALL) @JoinTable(name = "summoner_champions", joinColumns = @JoinColumn(name = "summoner_null"), inverseJoinColumns = @JoinColumn(name =
			"champions_own_id")) private List<Champion> champions = new ArrayList<>();

}



