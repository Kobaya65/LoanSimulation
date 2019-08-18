package fr.formation.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import fr.formation.business.AmortizationLine;
import fr.formation.business.Funding;
import fr.formation.enums.Choose;
import fr.formation.enums.LoanType;
import fr.formation.exceptions.IllegalAmountException;
import fr.formation.exceptions.IllegalDateException;
import fr.formation.exceptions.IllegalDurationException;
import fr.formation.exceptions.IllegalLoanTypeException;
import fr.formation.exceptions.IllegalRateException;

/**
 * This class is used to input data through keyboard, then to create a Funding
 * object.
 * 
 * @author Philippe AMICE
 *
 */
class InputData {

	/**
	 * This function aims at getting all information needed to create Funding
	 * object. It uses the class Scanner to prompt information.
	 * 
	 * @param sc the scanner used to get info
	 * @return Funding object
	 * @throws IllegalRateException     non valid rate
	 * @throws IllegalDurationException non valid duration
	 * @throws IllegalDateException     non valid date
	 * @throws IllegalAmountException   non valid amount
	 */
	static Funding loanInputs(Scanner sc)
			throws IllegalAmountException, IllegalDateException, IllegalDurationException, IllegalRateException {
		BigDecimal amount = new BigDecimal(0);
		BigDecimal interestRate = new BigDecimal(0);
		BigDecimal insuranceRate = new BigDecimal(0);

		LocalDate startDate = LocalDate.now();
		String loanType = "";
		String tempString = "";
		int duration = 0;

		// flag used to exit control loops
		Boolean loopExit = false;

		// to convert date in string to localdate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATEFORMAT10);

		// scanning loan type
		while (loopExit == false) {
			try {
				tempString = scanKeyboard(sc, Constants.LOANTYPE);
				loanType = tempString.toUpperCase();

				if (loanType.equalsIgnoreCase(LoanType.AU.toString())
						|| loanType.equalsIgnoreCase(LoanType.RE.toString())
						|| loanType.equalsIgnoreCase(LoanType.WO.toString())) {
					loopExit = true;
				} else {
					throw new IllegalLoanTypeException(Constants.ONLYREAUWO);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		// scanning loan amount
		while (amount.compareTo(BigDecimal.valueOf(1000.0)) == -1) {
			try {
				tempString = scanKeyboard(sc, Constants.AMOUNT);
				amount = BigDecimal.valueOf(Double.parseDouble(tempString));
				if (amount.compareTo(BigDecimal.valueOf(1000.0)) == -1) {
					throw new IllegalAmountException(Constants.AMNTGTETHOUSAND);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		// scanning duration
		loopExit = false;
		while (loopExit == false) {
			try {
				tempString = scanKeyboard(sc, Constants.DURATIONYEARS);
				duration = Integer.parseInt(tempString);
				if (duration >= 1 && duration <= 30) {
					loopExit = true;
				} else {
					throw new IllegalDurationException(Constants.DURATIONBTW1AND30);
				}

			} catch (Exception e) {
				if (e.getMessage().substring(0, 16).equals(Constants.FORINPUTSTRING)) {
					System.err.println(Constants.ENTERNUMBERGT0);
				} else {
					System.err.println(e.getMessage());
				}
			}
		}

		// scanning interest rate
		loopExit = false;
		while (loopExit == false) {
			try {
				tempString = scanKeyboard(sc, Constants.INTRATEDOTDECIMALSEPAR);
				interestRate = BigDecimal.valueOf(Double.parseDouble(tempString));
				interestRate = interestRate.divide(BigDecimal.valueOf(100L));
				if (interestRate.compareTo(BigDecimal.valueOf(0.0)) != 0) {
					loopExit = true;
				} else {
					throw new IllegalRateException(Constants.RATEGT0);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		// scanning start date
		byte countError = 0;
		loopExit = false;
		// while startDate <= today
		while (loopExit == false) {
			try {
				tempString = scanKeyboard(sc, Constants.STARTDATEDDMMYYYY);
				startDate = LocalDate.parse(tempString, formatter);
				if (startDate.compareTo(LocalDate.now()) > 0) {
					loopExit = true;
				} else {
					throw new IllegalDateException(Constants.ILLEGALDATE);
				}
			} catch (Exception e) {
				countError++;
				if (countError < 3) {
					if (e instanceof IllegalDateException) {
						System.err.println(e.getMessage());
					} else {
						System.err.println(Constants.WRONGDATEFORMAT);
					}
				} else {
					System.err.println(Constants.FUCKINGBASTARD);
				}
			}
		}

		// scanning insurance rate
		loopExit = false;
		while (loopExit == false) {
			try {
				tempString = scanKeyboard(sc, Constants.INSURATEDOTDECIMALSEPAR);
				insuranceRate = BigDecimal.valueOf(Double.parseDouble(tempString));
				insuranceRate = insuranceRate.divide(BigDecimal.valueOf(100L));
				if (insuranceRate.compareTo(BigDecimal.valueOf(0.0)) != -1) {
					loopExit = true;
				} else {
					throw new IllegalRateException(Constants.RATEGT0);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		// create and return the funding object
		Funding myLoan = new Funding(amount, loanType, duration, interestRate, startDate, insuranceRate);
		return myLoan;
	}

	/**
	 * Scanning keyboard for the user to choose between annual or monthly
	 * amortization table, or exit the program.
	 * 
	 * @param sc   the scanner used to get info
	 * @param loan the loan
	 */
	static void tableChoiceOrExit(Scanner sc, Funding loan) {
		final String ANNUALMONTHLYEEXIT = "A for [A]nnual or M for [M]onthly amortization table, or E to [E]xit";
		final String CHOOSEAME = "Choose ".concat(ANNUALMONTHLYEEXIT);
		String tempString = "";
		int choice = 0;
		boolean whileExit = false;

		do {
			// initializing string
			tempString = "";

			// choosing A for [A]nnual or M for [M]onthly amortization table, or E to [E]xit
			while (whileExit == false) {
				try {
					tempString = scanKeyboard(sc, ANNUALMONTHLYEEXIT);

					if (tempString.equalsIgnoreCase(Choose.A.toString())
							|| tempString.equalsIgnoreCase(Choose.M.toString())
							|| tempString.equalsIgnoreCase(Choose.E.toString())) {
						whileExit = true;
					} else {
						throw new IllegalLoanTypeException(CHOOSEAME);
					}
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}

			if (tempString.equalsIgnoreCase(Choose.A.toString())) {
				choice = 1;
			} else if (tempString.equalsIgnoreCase(Choose.M.toString())) {
				choice = 12;
			}

			if (!tempString.equalsIgnoreCase(Choose.E.toString())) {
				// call prepareCalculation only if input != "E"
				ArrayList<AmortizationLine> table = PrepareCalculation.prepareCalculation(loan, choice);
				// now, display table
				// tempString contains either "A" or "M"
				DisplayAmortizationPeriod.displayTable(table, tempString.toString());
				whileExit = false;
			}

			// exit the loop if input is "E"
		} while (!tempString.equalsIgnoreCase(Choose.E.toString()));

		if (tempString.equalsIgnoreCase(Choose.E.toString()))
			System.out.println("This is the end !");
		else
			System.out.println("! Abnormal end of program !");
	}

	/**
	 * Aims at getting entry on keyboard by the user thanks to Scanner().
	 * 
	 * @param sc     the scanner
	 * @param invite text describing the data to enter
	 * @return the "text" entered, whatever it is, number, date or text
	 */
	private static String scanKeyboard(Scanner sc, String invite) {
		String enteredValue = "";

		System.out.println("\nEnter ".concat(invite).concat(" : "));

		// scan keyboard
		try {
			enteredValue = sc.nextLine();
		} catch (Exception e) {
			// System.out.println("Message " + e.getMessage() + "\n" + e.toString());
			System.out.println("Message ".concat(e.getMessage()).concat("\n").concat(e.toString()));
		}

		return enteredValue;
	}
}
