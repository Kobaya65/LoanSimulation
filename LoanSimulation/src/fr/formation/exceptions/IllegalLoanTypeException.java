package fr.formation.exceptions;

import fr.formation.enums.LoanType;

/**
 * Non valid loan type.
 * 
 * @see LoanType
 * @author Philippe AMICE
 * 
 */
public class IllegalLoanTypeException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalLoanTypeException(String message) {
		super(message);
	}

}
