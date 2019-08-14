package fr.formation.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * This abstract class aims at providing mathematical functions needed for
 * computations in an amortization table.
 * 
 * @author Philippe AMICE
 *
 */
class ComputeAmortization {
	static MathContext mc = new MathContext(6, RoundingMode.HALF_UP);

	// compute periodRate
	static BigDecimal periodRate(BigDecimal rate, long period) {
		BigDecimal pr = rate.divide(BigDecimal.valueOf(period), mc);
		return pr;
	}

	// compute payoff
	// +-------comp1------+/+-----------------comp4----------------+==>final comput
	// +-------comp1------+______________+---comp2----+_+---comp3--+==>comput compos
	// (Capital*taux/Coeff)/(1-PUISSANCE((1+Taux/Coeff);-Duree*Coeff))
	static BigDecimal payoff(BigDecimal loanAmount, BigDecimal rate, long duration, int coeff) {
		final BigDecimal comp1;
		final BigDecimal comp2;
		final BigDecimal comp3;
		final BigDecimal comp4;

		// compute 1 = 9000
		comp1 = loanAmount.multiply(rate).divide(BigDecimal.valueOf(coeff), mc);
		// compute 2 = 1.045
		comp2 = rate.divide(BigDecimal.valueOf(coeff), mc).add(new BigDecimal(1));

		// compute 3 = -20
		comp3 = new BigDecimal(-1 * duration * coeff);
		// compute 4 =
		comp4 = new BigDecimal(1).subtract(comp2.pow((int) comp3.longValueExact(), mc));

		BigDecimal r = comp1.divide(comp4, 6);

		return r;
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
		insu = insu.divide(BigDecimal.valueOf(duration), mc);

		return insu;
	}

	// compute total cost
	static BigDecimal totalCost(BigDecimal payoff, BigDecimal insurance) {
		return payoff.add(insurance);
	}
}
