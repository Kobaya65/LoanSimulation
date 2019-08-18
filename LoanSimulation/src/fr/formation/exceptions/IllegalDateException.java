package fr.formation.exceptions;

/**
 * Non valid date.
 * 
 * @author Philippe AMICE
 * 
 */
public class IllegalDateException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalDateException(String message) {
		super(message);
	}
}
