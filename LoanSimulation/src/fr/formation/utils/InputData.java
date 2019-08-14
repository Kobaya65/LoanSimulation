package fr.formation.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import fr.formation.business.AmortizationLine;
import fr.formation.business.Funding;
import fr.formation.exceptions.IllegalAmountException;
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
	 * @return Funding object
	 */
	static Funding loanInputs(Scanner sc) {
		BigDecimal amount = new BigDecimal(0);
		BigDecimal interestRate = new BigDecimal(0);
		BigDecimal insuranceRate = new BigDecimal(0);

		LocalDate startDate = LocalDate.parse("1970-01-01");
		String loanType = "";
		String tempString = "";
		int duration = 0;

		final String EQRE = "RE";
		final String EQAU = "AU";
		final String EQWO = "WO";
		final String AMOUNT = "amount";
		final String AMNTGTE100 = "Amount of the loan must be greater than or equal to 100";
		final String LOANTYPE = "loan type ('RE = Real Estate', 'AU = Auto' or 'WO = Works')";
		final String ONLYREAUWO = "Loan type can only be RE, AU or WO";
		final String DURATIONYEARS = "duration in year(s)";
		final String DURATIONBTW1AND30 = "Duration must be between 1 and 30 years both included";
		final String FORINPUTSTRING = "For input string";
		final String INTRATEDOTDECIMALSEPAR = "interest rate with a dot as decimal separator (ex. 3.26 for 3.26%)";
		final String ENTERNUMBERGT0 = "Please enter a number >0";
		final String RATEGT0 = "Rate must be greater than 0";
		final String DATEFORMAT10 = "dd/MM/yyyy";
		final String STARTDATEDDMMYYYY = "start date (".concat(DATEFORMAT10).concat(")");
		final String WRONGDATEFORMAT = "Wrong date format. Expected format is '".concat(DATEFORMAT10).concat("'");
		final String DATE19700101 = "1970-01-01";
		final String FUCKINGBASTARD = "\nYOU FUCKING BASTARD !\nENTER THE DATE WITH THAT FORMAT 'dd/mm/yyyy'\nAND NOTHING ELSE. OK ?\n";
		final String INSURATEDOTDECIMALSEPAR = "insurance rate with a dot as decimal separator (ex. 1.98 for 1.98%)";

		// flag used to exit control loops
		Boolean loopExit = false;

		// to convert date in string to localdate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATEFORMAT10);

		// scanning loan amount
		while (amount.compareTo(BigDecimal.valueOf(100.0)) == -1) {
			try {
				tempString = scanKeyboard(sc, AMOUNT);
				amount = BigDecimal.valueOf(Double.parseDouble(tempString));
				if (amount.compareTo(BigDecimal.valueOf(100.0)) == -1) {
					throw new IllegalAmountException(AMNTGTE100);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		// scanning loan type
		while (loopExit == false) {
			try {
				tempString = scanKeyboard(sc, LOANTYPE);
				loanType = tempString.toUpperCase();

				if (loanType.equalsIgnoreCase(EQRE) || loanType.equalsIgnoreCase(EQAU)
						|| loanType.equalsIgnoreCase(EQWO)) {
					loopExit = true;
				} else {
					throw new IllegalLoanTypeException(ONLYREAUWO);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		// scanning duration
		loopExit = false;
		while (loopExit == false) {
			try {
				tempString = scanKeyboard(sc, DURATIONYEARS);
				duration = Integer.parseInt(tempString);
				if (duration < 1 || duration > 30) {
					throw new IllegalDurationException(DURATIONBTW1AND30);
				} else {
					loopExit = true;
				}

			} catch (Exception e) {
				if (e.getMessage().substring(0, 16).equals(FORINPUTSTRING)) {
					System.err.println(ENTERNUMBERGT0);
				} else {
					System.err.println(e.getMessage());
				}
			}
		}

		// scanning interest rate
		loopExit = false;
		while (loopExit == false) {
			try {
				tempString = scanKeyboard(sc, INTRATEDOTDECIMALSEPAR);
				interestRate = BigDecimal.valueOf(Double.parseDouble(tempString));
				interestRate = interestRate.divide(BigDecimal.valueOf(100L));
				if (interestRate.compareTo(BigDecimal.valueOf(0.0)) == 0) {
					throw new IllegalRateException(RATEGT0);
				} else {
					loopExit = true;
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		// scanning start date
		byte countError = 0;
		while (startDate.equals(LocalDate.parse(DATE19700101))) {
			try {
				tempString = scanKeyboard(sc, STARTDATEDDMMYYYY);
				startDate = LocalDate.parse(tempString, formatter);
			} catch (Exception e) {
				countError++;
				if (countError < 3) {
					System.err.println(WRONGDATEFORMAT);
				} else {
					System.err.println(FUCKINGBASTARD);
				}
			}
		}

		// scanning insurance rate
		loopExit = false;
		while (loopExit == false) {
			try {
				tempString = scanKeyboard(sc, INSURATEDOTDECIMALSEPAR);
				insuranceRate = BigDecimal.valueOf(Double.parseDouble(tempString));
				insuranceRate = insuranceRate.divide(BigDecimal.valueOf(100L));
				if (insuranceRate.compareTo(BigDecimal.valueOf(0.0)) == -1) {
					throw new IllegalRateException(RATEGT0);
				} else {
					loopExit = true;
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
	 * @param Funding loan the loan
	 */
	static void tableChoiceOrExit(Scanner sc, Funding loan) {
		final String EQA = "A";
		final String EQE = "E";
		final String EQM = "M";
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

					if (tempString.equalsIgnoreCase(EQA) || tempString.equalsIgnoreCase(EQM)
							|| tempString.equalsIgnoreCase(EQE)) {
						whileExit = true;
					} else {
						throw new IllegalLoanTypeException(CHOOSEAME);
					}
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}

			if (tempString.equalsIgnoreCase(EQA)) {
				choice = 1;
			} else if (tempString.equalsIgnoreCase(EQM)) {
				choice = 12;
			}

			if (!tempString.equalsIgnoreCase(EQE)) {
				// call prepareCalculation only if input != "E"
				ArrayList<AmortizationLine> table = PrepareCalculation.prepareCalculation(loan, choice);
				// now, display table
				// tempString contains either "A" or "M"
				DisplayAmortizationPeriod.displayTable(table, tempString.toString());
				whileExit = false;
			}

			// exit the loop if input is "E"
		} while (!tempString.equalsIgnoreCase(EQE));

		if (tempString.equalsIgnoreCase(EQE))
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
