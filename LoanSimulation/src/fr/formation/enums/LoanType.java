package fr.formation.enums;

/**
 * 
 * @author Philippe AMICE
 * 
 */
public enum LoanType {
	RE("RE"), AU("AU"), WO("WO");

	String loanType;

	/**
	 * @param loanType type of loan
	 */
	private LoanType(String loanType) {
		this.loanType = loanType;
	}
}
