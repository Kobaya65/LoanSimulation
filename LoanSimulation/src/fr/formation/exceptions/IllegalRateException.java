package fr.formation.exceptions;

/**
 * Non valid rate.
 * 
 * @author Philippe AMICE
 * 
 */
public class IllegalRateException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalRateException(String message) {
		super(message);
	}

}
