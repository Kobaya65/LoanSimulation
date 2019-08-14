package fr.formation.enums;

public enum Choose {
	A('A'), M('M'), E('E');

	char choose;

	/**
	 * @param choose letter typed to choose between annual or monthly report, or
	 *               exiting the loop
	 */
	private Choose(char choose) {
		this.choose = choose;
	}
}
