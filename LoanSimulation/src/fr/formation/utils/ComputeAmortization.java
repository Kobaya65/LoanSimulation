package fr.formation.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * This class aims at providing static mathematical functions needed for
 * computation of an amortization table.
 * 
 * @author Philippe AMICE
 *
 */
class ComputeAmortization {
	/**
	 * MathContext object used many times in this class for calculations on
	 * BigDecimal
	 */
	static MathContext mc = new MathContext(12, RoundingMode.HALF_UP);

	/**
	 * static method to compute the rate for the relevant period (i.e. the rate of
	 * the loan itself for annual amortization, or the loan rate divided by 12
	 * months).
	 * 
	 * @param rate   interest rate of the loan
	 * @param period period covered by the payment
	 * 
	 * @return interest rate for the relevant period
	 */
	static BigDecimal periodRate(BigDecimal rate, long period) {
		return rate.divide(BigDecimal.valueOf(period), mc);
	}

	/**
	 * static method to compute the payoff for this payment (amount * rate /
	 * duration).
	 * 
	 * here is detail of the different variables used to compute the payoff _____
	 * {@literal =>} final computation
	 * +-------comp1------+/+-------------comp4-------------+____________________
	 * {@literal =>} compute components for the final computation
	 * +-------comp1------+____+---comp2----+_+----comp3---+
	 * (Capital*taux/Coeff)/(1-(1+Taux/Coeff)^(-Duree*Coeff))
	 * 
	 * @param loanAmount amount of the loan
	 * @param rate       interest rate of the loan
	 * @param duration   duration of the loan in years
	 * @param coeff      1 for annual amortization table, 12 for monthly
	 * 
	 * @return the payoff
	 */
	static BigDecimal payoff(BigDecimal loanAmount, BigDecimal rate, long duration, int coeff) {
		final BigDecimal comp1;
		final BigDecimal comp2;
		final int comp3;
		final BigDecimal comp4;
		final BigDecimal one = new BigDecimal(1);

		// compute 1
		comp1 = loanAmount.multiply(rate, mc).divide(BigDecimal.valueOf(coeff), mc);
		// compute 2
		comp2 = rate.divide(BigDecimal.valueOf(coeff), mc).add(new BigDecimal(1));
		// compute 3
		comp3 = -1 * (int) duration * coeff;
		// compute 4
		comp4 = one.subtract(comp2.pow(comp3, mc));

		return comp1.divide(comp4, 6);
	}

	/**
	 * static method to compute amount of interest for a given payment.
	 * 
	 * @param remainingK remaining capital
	 * @param rate       rate of the loan
	 * @return amount of interest for this payment
	 */
	static BigDecimal interests(BigDecimal remainingK, BigDecimal rate) {
		return remainingK.multiply(rate);
	}

	/**
	 * static method to compute amount of capital repaid.
	 * 
	 * @param payoff    payoff of this loan
	 * @param interests amount of interests for the given period
	 * @return amount of capital repaid
	 */
	static BigDecimal principal(BigDecimal payoff, BigDecimal interests) {
		return payoff.subtract(interests);
	}

	/**
	 * static method to compute the remaining capital to be repaid.
	 * 
	 * @param remaining capital to be repaid
	 * @param principal amount of capital repaid for this payment
	 * 
	 * @return amount of remaining capital to be repaid
	 */
	static BigDecimal remainingK(BigDecimal remaining, BigDecimal principal) {
		return remaining.subtract(principal);
	}

	/**
	 * static method to compute amount of insurance for this payment.
	 * 
	 * @param capital  amount of the loan on which to calculate the insurance
	 *                 contribution
	 * @param rate     interest rate of the insurance
	 * @param duration duration of the loan in years
	 * 
	 * @return amount of insurance for this payment
	 */
	static BigDecimal insurance(BigDecimal capital, BigDecimal rate, long duration) {
		BigDecimal insu = capital.multiply(rate);
		insu = insu.divide(BigDecimal.valueOf(duration), mc);

		return insu;
	}

	/**
	 * static method to compute total cost of this payment (amount of capital repaid
	 * + interest + insurance contribution)
	 * 
	 * @param payoff    payoff for this loan (remaining capital to be repaid * rate
	 *                  / duration)
	 * @param insurance amount of insurance contribution for this payment
	 * 
	 * @return total cost for this payment
	 */
	static BigDecimal totalCost(BigDecimal payoff, BigDecimal insurance) {
		return payoff.add(insurance);
	}
}
