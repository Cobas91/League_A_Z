package cobas.coding.lol_a_z_backend.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor @Builder public class Champion {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long own_id;
	private String id;
	private int key;
	private String name;
	private String title;
	@ElementCollection private List<String> tags;
	@ElementCollection private Map<String, Integer> stats;
	@Lob private byte[] iconByteArray;
	private boolean played;
	private int wins;
	private int loose;
	private String version;

	public static class Comparators {

		public static Comparator<Champion> NAME = Comparator.comparing(o -> o.name);

	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		Champion champion = (Champion) o;
		return own_id != null && Objects.equals(own_id, champion.own_id);
	}

	@Override public int hashCode() {
		return getClass().hashCode();
	}
}
