package fr.formation.business;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Objects;

/**
 * 
 * @author Philippe AMICE
 * 
 *         This class represents a loan.
 * 
 */
public class Funding {
	private int amount;
	private String loanType;
	private int duration;
	private Double interestRate;
	private LocalDate startDate;
	private Double insuranceRate;

	// constructor with all fields, considering all as mandatory
	/**
	 * 
	 * @param amount        amount of the loan
	 * @param loanType      type of loan (RE - Real Estate, AU - Automotive, WO -
	 *                      Works)
	 * @param duration      term of the loan
	 * @param interestRate  interest rate
	 * @param startDate     date of the first monthly payment
	 * @param insuranceRate insurance rate
	 */
	public Funding(int amount, String loanType, int duration, Double interestRate, LocalDate startDate,
			Double insuranceRate) {

		// using setters where controls are centralized
		setAmount(amount);
		setLoanType(loanType);
		setDuration(duration);
		setInterestRate(interestRate);
		setStartDate(startDate);
		setInsuranceRate(insuranceRate);
	}

	public int getAmount() {
		return amount;
	}

	private void setAmount(int amount) {
		// a loan should not be less than 100 !
		if (amount < 100) {
			throw new IllegalArgumentException("Amount must be greater than 100.");
		} else {
			this.amount = amount;
		}
	}

	public String getLoanType() {
		return loanType;
	}

	private void setLoanType(String loanType) {
		Objects.requireNonNull(loanType);
	}

	public int getDuration() {
		return duration;
	}

	private void setDuration(int duration) {
		if (duration < 1 || duration > 30) {
			throw new IllegalArgumentException("Duration must be between 1 and 30 (years).");
		} else {
			this.duration = duration;
		}
	}

	public Double getInterestRate() {
		return interestRate;
	}

	private void setInterestRate(Double interestRate) {
		Objects.requireNonNull(interestRate);

		if (interestRate == 0) {
			throw new IllegalArgumentException("Interest rate must be greater than 0.");
		} else {
			this.interestRate = interestRate;
		}
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	private void setStartDate(LocalDate startDate) {
		Objects.requireNonNull(startDate);

		if (startDate.isBefore((ChronoLocalDate) startDate)) {
			this.startDate = startDate;
		} else {
			throw new IllegalArgumentException("Start date must be after today.");
		}
	}

	public Double getInsuranceRate() {
		return insuranceRate;
	}

	private void setInsuranceRate(Double insuranceRate) {
		if (insuranceRate == 0) {
			throw new IllegalArgumentException("Insurance rate must be greater than 0.");
		} else {
			this.insuranceRate = insuranceRate;
		}
	}

}
