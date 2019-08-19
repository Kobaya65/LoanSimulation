/**
 * commentaire package business
 */
package fr.formation.business;

import java.math.BigDecimal;

/**
 * This class represents a "line" of an amortization table.
 * 
 * @author Philippe AMICE
 *
 */

public class AmortizationLine {
	/**
	 * a month or a year
	 */
	private String period;
	/**
	 * amortized capital for this payment
	 */
	private BigDecimal amortizedK;
	/**
	 * amount of interests for this payment
	 */
	private BigDecimal interests;
	/**
	 * remaining capital to be repaid
	 */
	private BigDecimal remainingK;
	/**
	 * payoff for this payment (amount * rate / duration)
	 */
	private BigDecimal payoff;
	/**
	 * amount of insurance for the relevant period
	 */
	private BigDecimal insurance;
	/**
	 * total cost of this payment (payoff + insurance)
	 */
	private BigDecimal totalCost;

	// empty constructor
	public AmortizationLine() {
	}

	/**
	 * This class represents a line in the final table, with figures provided by
	 * methods of the class ComputeAmortization.
	 * 
	 * @param period       the date that determines the period (month or year, e.g.
	 *                     01/01/2020 or 2020)
	 * @param amortizedK   amortized capital for this payment
	 * @param interestRate interest rate for the relevant period
	 * @param remainingK   remaining capital to be repaid
	 * @param payoff       payoff for this payment (amount * rate / duration)
	 * @param insurance    amount of insurance for the relevant period
	 * @param totalCost    total cost of the loan on the relevant period (payoff +
	 *                     insurance)
	 */
	public AmortizationLine(String period, BigDecimal amortizedK, BigDecimal interestRate, BigDecimal remainingK,
			BigDecimal payoff, BigDecimal insurance, BigDecimal totalCost) {
		setPeriod(period);
		setAmortizedK(amortizedK);
		setInterests(interestRate);
		setRemainingK(remainingK);
		setPayoff(payoff);
		setInsurance(insurance);
		setTotalCost(totalCost);
	}

	/**
	 * set the period.
	 * 
	 * @param period either a year or the date of a monthly payment
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

	/**
	 * set amortized capital for this payment.
	 * 
	 * @param amortizedK amount of the amortized capital for this payment
	 */
	public void setAmortizedK(BigDecimal amortizedK) {
		this.amortizedK = amortizedK;
	}

	/**
	 * set the amount of interests for this payment.
	 * 
	 * @param interests amount of interests for this payment
	 */
	public void setInterests(BigDecimal interests) {
		this.interests = interests;
	}

	/**
	 * set remaining capital for this payment.
	 * 
	 * @param remainingK amount of the remaining capital for this payment
	 */
	public void setRemainingK(BigDecimal remainingK) {
		this.remainingK = remainingK;
	}

	/**
	 * set the payoff.
	 * 
	 * @param payoff amount of the payoff
	 */
	public void setPayoff(BigDecimal payoff) {
		this.payoff = payoff;
	}

	/**
	 * set insurance amount for this payment.
	 * 
	 * @param insurance insurance amount for this payment.
	 */
	public void setInsurance(BigDecimal insurance) {
		this.insurance = insurance;
	}

	/**
	 * set total cost for this payment.
	 * 
	 * @param totalCost total cost for this payment
	 */
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * get the period for this payment.
	 * 
	 * @return the period for this payment
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * get amortized capital for this payment.
	 * 
	 * @return amount of amortized capital for this payment
	 */
	public BigDecimal getAmortizedK() {
		return amortizedK;
	}

	/**
	 * get amount of interests for this payment.
	 * 
	 * @return amount of interests for this payment
	 */
	public BigDecimal getInterests() {
		return interests;
	}

	/**
	 * get remaining capital for this payment.
	 * 
	 * @return remaining capital for this payment
	 */
	public BigDecimal getRemainingK() {
		return remainingK;
	}

	/**
	 * get payoff for this payment.
	 * 
	 * @return payoff for this payment
	 */
	public BigDecimal getPayoff() {
		return payoff;
	}

	/**
	 * get insurance amount for this payment.
	 * 
	 * @return insurance amount for this payment
	 */
	public BigDecimal getInsurance() {
		return insurance;
	}

	/**
	 * get total cost for this payment.
	 * 
	 * @return total cost for this payment
	 */
	public BigDecimal getTotalCost() {
		return totalCost;
	}

	/**
	 * shows fields of AmortizationLine object in a readable manner.
	 */
	@Override
	public String toString() {
		return "AmortizationLine [period=" + period + ", amortizedK=" + amortizedK + ", interests=" + interests
				+ ", remainingK=" + remainingK + ", payoff=" + payoff + ", insurance=" + insurance + ", totalCost="
				+ totalCost + "]";
	}
}
