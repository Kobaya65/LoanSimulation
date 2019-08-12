package fr.formation.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import fr.formation.business.AmortizationLine;
import fr.formation.business.Funding;

abstract class PrepareCalculation {

	/**
	 * Computes needed figures and fills an arraylist of AmortizationLine to be
	 * printed in the console.
	 * 
	 * @param loan  the loan
	 * @param coeff 1 for annual amortization table, 12 for monthly amortization
	 *              table
	 */
	public static ArrayList<AmortizationLine> prepareCalculation(Funding loan, int coeff) {
		ArrayList<AmortizationLine> table = new ArrayList<AmortizationLine>();
		char space = ' ';
		StringBuilder dateAsString = new StringBuilder();

		// formatter to convert date in string to localdate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// at the beginning, remainingK == whole borrowed capital
		BigDecimal remainingK = loan.getAmount();

		// number of lines in the amortization table
		long duration = loan.getDuration() * coeff;
		BigDecimal principal;
		BigDecimal interestAmount;
		BigDecimal maturityRate = ComputeAmortization.periodRate(loan.getInterestRate(), coeff);
		// date of first payoff
		LocalDate date = loan.getStartDate();

		BigDecimal insurance = ComputeAmortization.insurance(loan.getAmount(), loan.getInsuranceRate(), duration);

		// compute payoff
		BigDecimal payoff = ComputeAmortization.payoff(remainingK, maturityRate, duration);

		for (int i = 0; i < duration; i++) {
			AmortizationLine amortizationLine = new AmortizationLine();

			// year or date
			dateAsString.setLength(0);
			if (coeff == 1) {
				// annual computation
				dateAsString.append(Integer.toString(date.getYear() + i));
			} else {
				// monthly computation
				dateAsString.append(space);
				dateAsString.append(date.plusMonths(i).format(formatter));
				dateAsString.append(space);
			}
			amortizationLine.setPeriod(dateAsString.toString());

			// amortized capital (principal)
			principal = ComputeAmortization.principal(payoff, ComputeAmortization.interests(remainingK, maturityRate));
			amortizationLine.setAmortizedK(principal);
			// amount of interests
			interestAmount = ComputeAmortization.interests(remainingK, maturityRate);
			amortizationLine.setInterests(interestAmount);
			// remaining capital
			amortizationLine.setRemainingK(remainingK.subtract(principal));
			// payoff
			amortizationLine.setPayoff(payoff);
			// amount of insurance
			amortizationLine.setInsurance(insurance);
			// total cost
			amortizationLine.setTotalCost(payoff.add(insurance));
			// add line to the list
			table.add(amortizationLine);

			// compute new amortizedK
			remainingK = remainingK.subtract(principal);
		}

		return table;
	}
}
