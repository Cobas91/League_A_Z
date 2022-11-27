package cobas.coding.lol_a_z_backend.security.exception;

public class UserExistsException extends RuntimeException {
	public UserExistsException(String message) {
		super(message);
	}
}
