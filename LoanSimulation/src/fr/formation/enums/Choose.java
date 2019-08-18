package fr.formation.enums;

/**
 * Enum to restrain entry to A (Annual amortization table), M (Monthly
 * amortization table) or E (Exit the program).
 * 
 * @author Philippe AMICE
 * 
 */
public enum Choose {
	/**
	 * annual amortization table
	 */
	A('A'),
	/**
	 * monthly amortization table
	 */
	M('M'),
	/**
	 * exit the program
	 */
	E('E');

	char choose;

	/**
	 * @param choose letter typed to choose between annual or monthly report, or
	 *               exiting the loop
	 */
	private Choose(char choose) {
		this.choose = choose;
	}
}
