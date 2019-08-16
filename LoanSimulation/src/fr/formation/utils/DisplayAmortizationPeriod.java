package fr.formation.utils;

import java.util.ArrayList;

import fr.formation.business.AmortizationLine;

/**
 * Aims at displaying an amortization table.
 * 
 * @author Philippe AMICE
 *
 */
public class DisplayAmortizationPeriod {

	public static void displayTable(ArrayList<AmortizationLine> table, String typeOfTable) {
		StringBuilder dataLine = new StringBuilder();
		StringBuilder frameLine = new StringBuilder();
		StringBuilder title = new StringBuilder();

		final String LINE12 = "------------";
		final String FMT122F = "%12.2f";
		final String FMTNBPLUSYEAR = "%1$5d - %2$4s";
		final String EQA = "A";
		final char PLUS = '+';
		final char STRIP = '|';

		// build separation line for the table
		// if typeOfTable = 'A', then it's a annually report, so table has 7 columns,
		// if typeOfTable = 'M', then it's a monthly report, so table has only 6 columns
		byte nbColumns = (byte) (typeOfTable.equalsIgnoreCase(EQA) ? 7 : 6);
		for (byte i = 0; i < nbColumns; i++) {
			frameLine.append(PLUS);
			frameLine.append(LINE12);
		}
		frameLine.append(PLUS);

		// build title line
		title.append(STRIP);
		if (typeOfTable.equalsIgnoreCase(EQA))
			title.append("    Year    ");
		else
			title.append("    Date    ");
		title.append(STRIP);
		title.append("  Amort. K  ");
		title.append(STRIP);
		title.append(" Interests  ");
		title.append(STRIP);
		title.append(" Remain. K  ");
		if (typeOfTable.equalsIgnoreCase(EQA)) {
			title.append(STRIP);
			title.append("   Payoff   ");
		}
		title.append(STRIP);
		title.append(" Insurance  ");
		title.append(STRIP);
		title.append(" Total Cost ");
		title.append(STRIP);

		// display header
		System.out.println(frameLine.toString());
		System.out.println(title.toString());
		System.out.println(frameLine.toString());

		int yearNumber = 1;
		for (AmortizationLine line : table) {
			// build line
			dataLine.append(STRIP);
			if (typeOfTable.equalsIgnoreCase(EQA))
				dataLine.append(String.format(FMTNBPLUSYEAR, yearNumber++, line.getPeriod()));
			else
				dataLine.append(line.getPeriod());

			dataLine.append(STRIP);
			dataLine.append(String.format(FMT122F, line.getAmortizedK()));
			dataLine.append(STRIP);
			dataLine.append(String.format(FMT122F, line.getInterests()));
			dataLine.append(STRIP);
			dataLine.append(String.format(FMT122F, line.getRemainingK()));

			// this column only for annual amortization
			if (typeOfTable.equalsIgnoreCase(EQA)) {
				dataLine.append(STRIP);
				dataLine.append(String.format(FMT122F, line.getPayoff()));
			}

			dataLine.append(STRIP);
			dataLine.append(String.format(FMT122F, line.getInsurance()));
			dataLine.append(STRIP);
			dataLine.append(String.format(FMT122F, line.getTotalCost()));
			dataLine.append(STRIP);

			// displaying line
			System.out.println(dataLine.toString());

			// emptying sb
			dataLine.setLength(0);
		}

		// display last line + empty line
		System.out.println(frameLine.toString());
		System.out.println();
	}
}
