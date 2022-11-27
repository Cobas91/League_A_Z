package cobas.coding.lol_a_z_backend.security.exception;

public class SummonerNotExistException extends RuntimeException {
	public SummonerNotExistException(Exception e, String message) {
		super(message, e);
	}

	public SummonerNotExistException(String message) {
		super(message);
	}
}
