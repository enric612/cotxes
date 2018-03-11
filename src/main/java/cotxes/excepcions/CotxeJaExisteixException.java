package cotxes.excepcions;

public class CotxeJaExisteixException extends Exception {
	/**
	 * Per ser elegant pero no es necessari
	 */
	private static final long serialVersionUID = -1844799082364789342L;

	public CotxeJaExisteixException() {
		super("Cotxe ja existent");
	}
}
