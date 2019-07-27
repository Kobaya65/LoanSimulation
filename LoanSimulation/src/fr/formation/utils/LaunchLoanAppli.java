package fr.formation.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import fr.formation.business.Funding;

public class LaunchLoanAppli {

	public static void main(String[] args) {
		// in order to convert string to localdate...
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// temporary variable for reading the scanner
		String tempString = "";
		// initialize scanner
		Scanner sc = new Scanner(System.in);

		tempString = InputData.input(sc, "amount");
		int amount = Integer.parseInt(tempString);

		tempString = InputData.input(sc, "loan type ('RE = Real Estate', 'AU = Auto' or 'WO = Works')");
		String loanType = tempString.toUpperCase();

		tempString = InputData.input(sc, "duration in year(s)");
		int duration = Integer.parseInt(tempString);

		tempString = InputData.input(sc, "interest rate with a dot as decimal separator (ex. 3.26 for 3.26%)");
		double interestRate = Double.parseDouble(tempString);

		// startDate = sc.next(fourYearsDigitsDatePattern);
		LocalDate startDate = LocalDate.parse("1970-01-01");
		byte countError = 0;
		while (startDate.equals(LocalDate.parse("1970-01-01"))) {
			try {
				tempString = InputData.input(sc, "start date ()");
				startDate = LocalDate.parse(tempString, formatter);
			} catch (Exception e) {
				countError++;
				if (countError < 2) {
					System.out.println("Wrong date format. Please retry !");
				} else {
					System.out.println(
							"YOU FUCKING BASTARD !\nENTER THE DATE WITH THAT FORMAT 'dd/mm/yyyy'\nAND NOTHING ELSE. OK ?");
				}
			}
		}

		tempString = InputData.input(sc, "insurance rate with a dot as decimal separator (ex. 1.98 for 1.98%)");
		Double insuranceRate = Double.parseDouble(tempString);

		// close scanner
		sc.close();

		// create a funding
		Funding myLoan = new Funding(amount, loanType, duration, interestRate, startDate, insuranceRate);

		System.out.println(myLoan.toString());
	}
}
