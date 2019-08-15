package fr.formation.utils;

import java.util.Scanner;

import fr.formation.business.Funding;

/**
 * Launching simulation
 * 
 * @author Philippe AMICE
 *
 */
class LaunchLoanAppli {

	public static void main(String[] args) {
		Funding myLoan = new Funding();
		// opening scanner
		Scanner sc = new Scanner(System.in);

		final String line = ">>-----------------------<<";

		System.out.println(line);
		System.out.println(">>--- Loan Simulation ---<<");
		System.out.println(line);

		// getting data and storing it in myLoan
		myLoan = InputData.loanInputs(sc);

		// little summary before going further...
		System.out.println("Summary : ".concat(myLoan.toString()));

		// choosing annual or monthly amortization table, or Exit program
		InputData.tableChoiceOrExit(sc, myLoan);

		// closing scanner
		sc.close();
	}
}
