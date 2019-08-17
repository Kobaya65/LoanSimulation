package fr.formation.enums;

/**
 * 
 * @author Philippe AMICE
 */
public enum Choose {
	A('A'), M('M'), E('E');

	char choose;

	/**
	 * @param choose letter typed to choose between annual or monthly report, or
	 *               exiting the loop {@literal} A annual amortization table
	 *               {@literal} M monthly amortization table {@literal} E exit
	 *               program
	 */
	private Choose(char choose) {
		this.choose = choose;
	}
}
