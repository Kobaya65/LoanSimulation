package fr.formation.utils;

import java.util.Scanner;

class InputData {
	/**
	 * Aims at getting entry on keyboard by the user thanks to Scanner().
	 * 
	 * @param sc     scanner object
	 * @param invite the text describing type of data to enter
	 * @return the "text" entered, whatever it is, number, date or text
	 */
	static String input(Scanner sc, String invite) {
		System.out.println("Enter " + invite + " : ");

		// scanner on keyboard
		String enteredValue = sc.next();

		return enteredValue;
	}
}
