package cobas.coding.lol_a_z_backend.security.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PasswordResetDTO {
	private String email;
}
