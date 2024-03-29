package fr.formation.exceptions;

/**
 * Non valid duration.
 * 
 * @author Philippe AMICE
 * 
 */
public class IllegalDurationException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalDurationException(String message) {
		super(message);
	}

}
