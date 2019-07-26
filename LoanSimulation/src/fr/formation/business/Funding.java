package fr.formation.business;

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
	private String startDate;
	private Double insuranceRate;

	// constructor with all fields, considering all as mandatory
	public Funding(int amount, String loanType, int duration, Double interestRate, String startDate,
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
		if (loanType == null) {
			throw new NullPointerException("Loan Type is mandatory.");
		} else {
			this.loanType = loanType;
		}
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
		if (interestRate == 0) {
			throw new IllegalArgumentException("Interest rate must be greater than 0.");
		} else {
			this.interestRate = interestRate;
		}
	}

	public String getStartDate() {
		return startDate;
	}

	private void setStartDate(String startDate) {
//		if (startDate.compareTo(LocalDate.now().toString()) {
		this.startDate = startDate;
//		} else {
//			throw new IllegalArgumentException("Start date must be after today.");
//		}
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
