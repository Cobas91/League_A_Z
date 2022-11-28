package cobas.coding.lol_a_z_backend.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "system_information")  @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor public class SystemInformation {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id", nullable = false)
	private Long id;

	@CreationTimestamp
	private LocalDateTime createDate;
	private String champVersion;
	private int champCount;


	public String getChampVersion(){
		if(champVersion != null && !champVersion.isBlank() && !champVersion.isEmpty()){
			return champVersion;
		}
		return "0";
	}

}