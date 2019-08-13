package fr.formation.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This abstract class aims at providing mathematical functions needed for
 * computations in an amortization table.
 * 
 * @author Philippe AMICE
 *
 */
class ComputeAmortization {

	// compute periodRate
	static BigDecimal periodRate(BigDecimal rate, long period) {
		BigDecimal pr = rate.divide(BigDecimal.valueOf(period), 8, RoundingMode.HALF_DOWN);
		return pr;
	}

	// compute payoff
	// ________________________________________________+------------------3------------+
	// (+------1------+*+-------------2-------------+)/(+-------------2-------------+)
	// (Capital*TauxEch*PUISSANCE(1+TauxEch;Duree*12))/(PUISSANCE(1+TauxEch;Duree*12)-1)
	static BigDecimal payoff(BigDecimal loanAmount, BigDecimal periodRate, long duration) {
		final BigDecimal kMultipliedByPeriodRate;
		final BigDecimal onePlusRatePowDuration;
		final BigDecimal powMinusOne;
		final BigDecimal finalComputation1;
		final BigDecimal finalComputation2;

		// compute 1
		kMultipliedByPeriodRate = loanAmount.multiply(periodRate);
		// compute 2
		onePlusRatePowDuration = new BigDecimal(1).add(periodRate).pow((int) duration);
		// compute 3
		powMinusOne = onePlusRatePowDuration.subtract(new BigDecimal(1));
		// compute 1 * 2
		finalComputation1 = kMultipliedByPeriodRate.multiply(onePlusRatePowDuration);
		finalComputation2 = finalComputation1.divide(powMinusOne, RoundingMode.HALF_DOWN);

		return finalComputation2;
	}

	// compute amount of interest
	static BigDecimal interests(BigDecimal remainingK, BigDecimal rate) {
		return remainingK.multiply(rate);
	}

	// compute principal
	static BigDecimal principal(BigDecimal payoff, BigDecimal interests) {
		return payoff.subtract(interests);
	}

	// compute remainingK
	static BigDecimal remainingK(BigDecimal remaining, BigDecimal principal) {
		return remaining.subtract(principal);
	}

	// compute insurance
	static BigDecimal insurance(BigDecimal capital, BigDecimal rate, long duration) {
		BigDecimal insu = capital.multiply(rate);
		insu = insu.divide(BigDecimal.valueOf(duration));

		return insu;
	}

	// compute total cost
	static BigDecimal totalCost(BigDecimal payoff, BigDecimal insurance) {
		return payoff.add(insurance);
	}
}
