package fr.formation.exceptions;

/**
 * Amount not permitted for a loan.
 * 
 * @author Philippe AMICE
 * 
 */
public class IllegalAmountException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalAmountException(String message) {
		super(message);
	}

}
