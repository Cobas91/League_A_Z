package com.example.lol_a_z_backend.model;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Entity @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor @Builder public class Champion {
	@Id
	private String id;
	private int key;
	private String name;
	private String title;
	@ElementCollection
	private List<String> tags;
	@ElementCollection
	private Map<String, Integer> stats;
	private String icon;
	@Lob
	private String description;
	private boolean played;
	private int wins;
	private int loose;

	public static class Comparators {

		public static Comparator<Champion> NAME = Comparator.comparing(o -> o.name);

	}

}
