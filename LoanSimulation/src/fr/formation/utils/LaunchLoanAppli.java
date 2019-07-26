package fr.formation.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import fr.formation.business.Funding;

public class LaunchLoanAppli {

	public static void main(String[] args) {

		int amount;
		String loanType;
		int duration;
		Double interestRate;
		String startDate;
		LocalDate sLocalDate;
		Double insuranceRate;
		String fourYearsDigitsDatePattern = "[0-9]{2}/[0-9]{2}/[0-9]{4}";

		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
		decimalFormatSymbols.setDecimalSeparator(',');
		decimalFormatSymbols.setGroupingSeparator(' ');
		DecimalFormat decimalFormat = new DecimalFormat("# ##0,00", decimalFormatSymbols);

		// in order to convert string to localdate...
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter longFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");

		// initialize scanner on keyboard
		Scanner sc = new Scanner(System.in);

		// input amount
		System.out.println("Enter amount : ");
		amount = sc.nextInt();

		// input loanType
		System.out.println("Enter loan type ('RE = Real Estate', 'AU = Auto' or 'WO = Works') : ");
		loanType = sc.next().toUpperCase();

		// input duration
		System.out.println("Enter duration in year(s) : ");
		duration = sc.nextInt();

		// input interest rate
		System.out.println("Enter interest rate (with a comma as decimal separator) : ");
		interestRate = sc.nextDouble();

		// input start date
		System.out.println("Enter start date (dd/mm/yyyy) : ");
		startDate = sc.next(fourYearsDigitsDatePattern);
		sLocalDate = LocalDate.parse(startDate, formatter);

		// input insurance rate
		System.out.println("Enter insurance rate (with a comma as decimal separator) : ");
		insuranceRate = sc.nextDouble();

		// close scanner
		sc.close();

//		System.out.println("Amount         => " + decimalFormat.format((long) amount));
//		System.out.println("Loan type      => " + loanType);
//		System.out.println("Duration       => " + duration);
//		System.out.println("Interest rate  => " + interestRate + "%");
//		System.out.println("Start date     => " + sLocalDate.format(longFormatter));
//		System.out.println("Insurance rate => " + insuranceRate + "%");

		// create a funding
		Funding myLoan = new Funding(amount, loanType, duration, interestRate, sLocalDate, insuranceRate);
		System.out.println(myLoan.toString());
		// System.out.println("My loan = " + myLoan.toString());
	}
}
