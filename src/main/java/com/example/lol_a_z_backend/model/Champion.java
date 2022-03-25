package com.example.lol_a_z_backend.model;

import com.example.lol_a_z_backend.security.model.Summoner;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
	private String icon;
	@Lob private byte[] iconByteArray;
	@Lob private String description;
	private boolean played;
	private int wins;
	private int loose;
	@JsonBackReference @ManyToOne(cascade = CascadeType.ALL) private Summoner summoner;

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
