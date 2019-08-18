package fr.formation.enums;

/**
 * Enum to restrain entry of loan type to RE (Real Estate), AU (AUtomotive) or
 * WO (WOrks).
 * 
 * @author Philippe AMICE
 * 
 */
public enum LoanType {
	/**
	 * RE Real Estate
	 */
	RE("RE"),
	/**
	 * AU AUtomotive
	 */
	AU("AU"),
	/**
	 * WO WOrks
	 */
	WO("WO");

	String loanType;

	/**
	 * @param loanType type of loan
	 */
	private LoanType(String loanType) {
		this.loanType = loanType;
	}
}
