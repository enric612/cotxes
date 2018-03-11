package cotxes.excepcions;

public class CotxeNoEliminatException extends Exception {
	/**
	 * Per ser elegant pero no es necesari
	 */
	private static final long serialVersionUID = -700250034685480599L;

	public CotxeNoEliminatException() {
		super("Cotxe no eliminat");
	}
}
