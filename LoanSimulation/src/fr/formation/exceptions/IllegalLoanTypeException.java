package fr.formation.exceptions;

public class IllegalLoanTypeException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalLoanTypeException(String message) {
		super(message);
	}

}
