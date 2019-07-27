package fr.formation.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import fr.formation.business.Funding;
import fr.formation.exceptions.IllegalAmountException;
import fr.formation.exceptions.IllegalDurationException;
import fr.formation.exceptions.IllegalLoanTypeException;
import fr.formation.exceptions.IllegalRateException;

public class LaunchLoanAppli {

	public static void main(String[] args) {
		int amount = 0;
		String loanType = "";
		int duration = 0;
		double interestRate = 0;
		LocalDate startDate = LocalDate.parse("1970-01-01");
		double insuranceRate = 0;

		Boolean checkForLoopExit = false;

		// to convert date in string to localdate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// temporary variable for reading the scanner

		String tempString = "";
		// initialize scanner
		Scanner sc = new Scanner(System.in);

		// scanning amount
		while (amount < 100) {
			try {
				tempString = InputData.input(sc, "amount");
				amount = Integer.parseInt(tempString);
				if (amount < 100) {
					throw new IllegalAmountException("Amount of the loan must be greater than or equal to 100");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		// scanning loan type
		while (checkForLoopExit == false) {
			try {
				tempString = InputData.input(sc, "loan type ('RE = Real Estate', 'AU = Auto' or 'WO = Works')");
				loanType = tempString.toUpperCase();

				if (loanType.toUpperCase().equals("RE") || loanType.toUpperCase().equals("AU")
						|| loanType.toUpperCase().equals("WO")) {
					checkForLoopExit = true;
				} else {
					throw new IllegalLoanTypeException("Loan type can only be RE, AU or WO");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		// scanning duration
		checkForLoopExit = false;
		while (checkForLoopExit == false) {
			try {
				tempString = InputData.input(sc, "duration in year(s)");
				duration = Integer.parseInt(tempString);
				if (duration < 1 || duration > 30) {
					throw new IllegalDurationException("Duration must be between 1 and 30 years both included");
				} else {
					checkForLoopExit = true;
				}

			} catch (Exception e) {
				if (e.getMessage().substring(0, 16).equals("For input string")) {
					System.err.println("Please enter a number >0");
				} else {
					System.err.println(e.getMessage());
				}
			}
		}

		// scanning interest rate
		checkForLoopExit = false;
		while (checkForLoopExit == false) {
			try {
				tempString = InputData.input(sc, "interest rate with a dot as decimal separator (ex. 3.26 for 3.26%)");
				interestRate = Double.parseDouble(tempString);
				if (interestRate <= 0) {
					throw new IllegalRateException("Rate must be greater than 0");
				} else {
					checkForLoopExit = true;
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		// scanning start date
		byte countError = 0;
		while (startDate.equals(LocalDate.parse("1970-01-01"))) {
			try {
				tempString = InputData.input(sc, "start date (dd/mm/yyyy)");
				startDate = LocalDate.parse(tempString, formatter);
			} catch (Exception e) {
				countError++;
				if (countError < 3) {
					System.err.println("Wrong date format. Please retry !");
				} else {
					System.err.println(
							"\nYOU FUCKING BASTARD !\nENTER THE DATE WITH THAT FORMAT 'dd/mm/yyyy'\nAND NOTHING ELSE. OK ?\n");
				}
			}
		}

		// scanning insurance rate
		checkForLoopExit = false;
		while (checkForLoopExit == false) {
			try {
				tempString = InputData.input(sc, "insurance rate with a dot as decimal separator (ex. 1.98 for 1.98%)");
				insuranceRate = Double.parseDouble(tempString);
				if (insuranceRate <= 0) {
					throw new IllegalRateException("Rate must be greater than 0");
				} else {
					checkForLoopExit = true;
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		sc.close(); // close scanner

		// create the funding
		Funding myLoan = new Funding(amount, loanType, duration, interestRate, startDate, insuranceRate);

		// little summary
		System.out.println(myLoan.toString());
	}
}
