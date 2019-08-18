package fr.formation.business;

import java.math.BigDecimal;

/**
 * This abstract class represents a "line" of an amortization table to be
 * displayed in the console
 * 
 * @author Philippe AMICE
 *
 */
public class AmortizationLine {
	private String period;
	private BigDecimal amortizedK;
	private BigDecimal interests;
	private BigDecimal remainingK;
	private BigDecimal payoff;
	private BigDecimal insurance;
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
	 * @param amortizedK   amortized capital
	 * @param interestRate interest rate for the relevant period
	 * @param remainingK   remaining capital
	 * @param payoff       principal + interests
	 * @param insurance    amount of insurance
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

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setAmortizedK(BigDecimal amortizedK) {
		this.amortizedK = amortizedK;
	}

	public void setInterests(BigDecimal interests) {
		this.interests = interests;
	}

	public void setRemainingK(BigDecimal remainingK) {
		this.remainingK = remainingK;
	}

	public void setPayoff(BigDecimal payoff) {
		this.payoff = payoff;
	}

	public void setInsurance(BigDecimal insurance) {
		this.insurance = insurance;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public String getPeriod() {
		return period;
	}

	public BigDecimal getAmortizedK() {
		return amortizedK;
	}

	public BigDecimal getInterests() {
		return interests;
	}

	public BigDecimal getRemainingK() {
		return remainingK;
	}

	public BigDecimal getPayoff() {
		return payoff;
	}

	public BigDecimal getInsurance() {
		return insurance;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	@Override
	public String toString() {
		return "AmortizationLine [period=" + period + ", amortizedK=" + amortizedK + ", interests=" + interests
				+ ", remainingK=" + remainingK + ", payoff=" + payoff + ", insurance=" + insurance + ", totalCost="
				+ totalCost + "]";
	}
}
