package fr.formation.business;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/**
 * This class represents a loan.
 * 
 * @author Philippe AMICE
 * 
 */
public class Funding {
	private BigDecimal amount;
	private String loanType;
	private long duration;
	private BigDecimal interestRate;
	private LocalDate startDate;
	private BigDecimal insuranceRate;

	// DateTimeFormatter used to display the date the right way
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

	public Funding() {
	}

	/**
	 * constructor with all fields as mandatory
	 * 
	 * @param amount        amount of the loan (>1000)
	 * @param loanType      type of loan (RE - Real Estate, AU - Automotive, WO -
	 *                      Works)
	 * @param duration      term of the loan
	 * @param interestRate  interest rate
	 * @param startDate     date of the first monthly payment
	 * @param insuranceRate insurance rate
	 */
	public Funding(BigDecimal amount, String loanType, long duration, BigDecimal interestRate, LocalDate startDate,
			BigDecimal insuranceRate) {

		// using setters where controls are centralized
		setAmount(amount);
		setLoanType(loanType);
		setDuration(duration);
		setInterestRate(interestRate);
		setStartDate(startDate);
		setInsuranceRate(insuranceRate);
	}

	public BigDecimal getAmount() {
		return amount;
	}

	private void setAmount(BigDecimal amount) {
		// a loan should not be less than 100 !
		if (amount.compareTo(BigDecimal.valueOf(1000.0)) == -1) {
			throw new IllegalArgumentException("Amount must be greater than 1000.");
		} else {
			this.amount = amount;
		}
	}

	public String getLoanType() {
		return loanType;
	}

	private void setLoanType(String loanType) {
		Objects.requireNonNull(loanType);
		this.loanType = loanType;
	}

	public long getDuration() {
		return duration;
	}

	private void setDuration(long duration) {
		if (duration < 1 || duration > 30) {
			throw new IllegalArgumentException("Duration must be between 1 and 30 (years).");
		} else {
			this.duration = duration;
		}
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	private void setInterestRate(BigDecimal interestRate) {
		Objects.requireNonNull(interestRate);

		if (interestRate.compareTo(BigDecimal.valueOf(0.0)) == -1) {
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

		if (startDate.isBefore((ChronoLocalDate) LocalDate.now())) {
			throw new IllegalArgumentException("Start date must be after today.");
		} else {
			this.startDate = startDate;
		}
	}

	public BigDecimal getInsuranceRate() {
		return insuranceRate;
	}

	private void setInsuranceRate(BigDecimal insuranceRate) {
		if (insuranceRate.compareTo(BigDecimal.valueOf(0.0)) == -1) {
			throw new IllegalArgumentException("Insurance rate must be greater than 0.");
		} else {
			this.insuranceRate = insuranceRate;
		}
	}

	@Override
	public String toString() {
		// used to display amount with space as thousands separator
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.FRANCE);
		// used to display percentage
		NumberFormat decimalFormat = NumberFormat.getPercentInstance(Locale.FRANCE);
		decimalFormat.setMinimumFractionDigits(3);

		return "[Amount = " + numberFormat.format(getAmount()) + "€, Loan type = " + getLoanType() + ", duration = "
				+ getDuration() + " years, interest rate = " + decimalFormat.format(getInterestRate())
				+ ", start date = " + getStartDate().format(dateTimeFormatter) + ", insurance rate = "
				+ decimalFormat.format(getInsuranceRate()) + "]";
	}

}
