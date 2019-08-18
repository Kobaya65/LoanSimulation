package fr.formation.business;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

import fr.formation.exceptions.IllegalAmountException;
import fr.formation.exceptions.IllegalDateException;
import fr.formation.exceptions.IllegalDurationException;
import fr.formation.exceptions.IllegalRateException;
import fr.formation.utils.Constants;

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

	// empty constructor
	public Funding() {
	}

	/**
	 * constructor with all fields mandatory
	 * 
	 * @param amount        amount of the loan must be greater than or equal to 1000
	 * @param loanType      type of loan (RE - Real Estate, AU - Automotive, WO -
	 *                      Works)
	 * @param duration      term of the loan
	 * @param interestRate  interest rate
	 * @param startDate     date of the first monthly payment
	 * @param insuranceRate insurance rate
	 * @throws IllegalAmountException   non valid amount
	 * @throws IllegalDateException     non valid date
	 * @throws IllegalDurationException non valid duration
	 * @throws IllegalRateException     non valid rate
	 */
	public Funding(BigDecimal amount, String loanType, long duration, BigDecimal interestRate, LocalDate startDate,
			BigDecimal insuranceRate) throws IllegalAmountException, IllegalDateException, IllegalDurationException,
			IllegalAmountException, IllegalRateException {

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

	private void setAmount(BigDecimal amount) throws IllegalAmountException {
		// a loan must be greater than or equal to 1000
		if (amount.compareTo(BigDecimal.valueOf(1000.0)) != -1) {
			this.amount = amount;
		} else {
			throw new IllegalAmountException(Constants.AMNTGTETHOUSAND);
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

	private void setDuration(long duration) throws IllegalDurationException {
		if (duration >= 1 && duration <= 30) {
			this.duration = duration;
		} else {
			throw new IllegalDurationException(Constants.DURATIONBTW1AND30);
		}
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	private void setInterestRate(BigDecimal interestRate) throws IllegalRateException {
		Objects.requireNonNull(interestRate);

		if (interestRate.compareTo(BigDecimal.valueOf(0.0)) > 0) {
			this.interestRate = interestRate;
		} else {
			throw new IllegalRateException(Constants.RATEGT0);
		}
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	private void setStartDate(LocalDate startDate) throws IllegalDateException {
		Objects.requireNonNull(startDate);

		if (!startDate.isBefore(LocalDate.now())) {
			this.startDate = startDate;
		} else {
			throw new IllegalDateException(Constants.ILLEGALDATE);
		}
	}

	public BigDecimal getInsuranceRate() {
		return insuranceRate;
	}

	private void setInsuranceRate(BigDecimal insuranceRate) throws IllegalRateException {
		if (insuranceRate.compareTo(BigDecimal.valueOf(0.0)) > 0) {
			this.insuranceRate = insuranceRate;
		} else {
			throw new IllegalRateException(Constants.RATEGT0);
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
