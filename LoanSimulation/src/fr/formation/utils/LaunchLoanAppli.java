package fr.formation.utils;

import java.util.Scanner;

import fr.formation.business.Funding;
import fr.formation.exceptions.IllegalAmountException;
import fr.formation.exceptions.IllegalDateException;
import fr.formation.exceptions.IllegalDurationException;
import fr.formation.exceptions.IllegalRateException;

/**
 * Launching the simulation.
 * 
 * @author Philippe AMICE
 *
 */
class LaunchLoanAppli {

	/**
	 * Starting point of the program.
	 * 
	 * @param args no expected {@literal param}
	 * 
	 * @throws IllegalAmountException   amount must be greater than or equal to 1000
	 * @throws IllegalDateException     date must be greater than today
	 * @throws IllegalDurationException duration must be between 1 and 30 years
	 * @throws IllegalRateException     rate must be greater than 0
	 */
	public static void main(String[] args)
			throws IllegalAmountException, IllegalDateException, IllegalDurationException, IllegalRateException {
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
