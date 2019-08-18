package fr.formation.utils;

import java.util.ArrayList;

import fr.formation.business.AmortizationLine;

/**
 * Aims at displaying a given amortization table.
 * 
 * @author Philippe AMICE
 *
 */
public class DisplayAmortizationPeriod {

	/**
	 * static function to display the lines of an arraylist of amortization lines.
	 * 
	 * @param table       arraylist of amortization line
	 * @param typeOfTable A for annual amortization table, M for monthly
	 */
	public static void displayTable(ArrayList<AmortizationLine> table, String typeOfTable) {
		StringBuilder dataLine = new StringBuilder();
		StringBuilder frameLine = new StringBuilder();
		StringBuilder title = new StringBuilder();

		// build separation line for the table
		// if typeOfTable = 'A', then it's an annually report, so table has 7 columns,
		// if typeOfTable = 'M', then it's a monthly report, so table has only 6 columns
		byte nbColumns = (byte) (typeOfTable.equalsIgnoreCase(Constants.EQA) ? 7 : 6);
		for (byte i = 0; i < nbColumns; i++) {
			frameLine.append(Constants.PLUS);
			frameLine.append(Constants.LINE14);
		}
		frameLine.append(Constants.PLUS);

		// build title line
		title.append(Constants.STRIP);
		if (typeOfTable.equalsIgnoreCase(Constants.EQA))
			title.append("     Year     ");
		else
			title.append("     Date     ");
		title.append(Constants.STRIP);
		title.append(" Amortized K  ");
		title.append(Constants.STRIP);
		title.append("  Interests   ");
		title.append(Constants.STRIP);
		title.append(" Remaining K  ");
		if (typeOfTable.equalsIgnoreCase(Constants.EQA)) {
			title.append(Constants.STRIP);
			title.append("    Payoff    ");
		}
		title.append(Constants.STRIP);
		title.append("  Insurance   ");
		title.append(Constants.STRIP);
		title.append("  Total Cost  ");
		title.append(Constants.STRIP);

		// display table header
		System.out.println(frameLine.toString());
		System.out.println(title.toString());
		System.out.println(frameLine.toString());

		int yearNumber = 1;
		for (AmortizationLine line : table) {
			// build line
			dataLine.append(Constants.STRIP);
			if (typeOfTable.equalsIgnoreCase(Constants.EQA))
				dataLine.append(String.format(Constants.FMTNBPLUSYEAR, yearNumber++, line.getPeriod()));
			else {
				dataLine.append(Constants.SPACE);
				dataLine.append(line.getPeriod());
				dataLine.append(Constants.SPACE);
			}

			dataLine.append(Constants.STRIP);
			// dataLine.append(String.format(Constants.FMT142F, line.getAmortizedK()));
			dataLine.append(StringUtils.formattedValue(14, line.getAmortizedK()));

			dataLine.append(Constants.STRIP);
			// dataLine.append(String.format(Constants.FMT142F, line.getInterests()));
			dataLine.append(StringUtils.formattedValue(14, line.getInterests()));

			dataLine.append(Constants.STRIP);
			// dataLine.append(String.format(Constants.FMT142F, line.getRemainingK()));
			dataLine.append(StringUtils.formattedValue(14, line.getRemainingK()));

			// this column only for annual amortization
			if (typeOfTable.equalsIgnoreCase(Constants.EQA)) {
				dataLine.append(Constants.STRIP);
				// dataLine.append(String.format(Constants.FMT142F, line.getPayoff()));
				dataLine.append(StringUtils.formattedValue(14, line.getPayoff()));
			}

			dataLine.append(Constants.STRIP);
			// dataLine.append(String.format(Constants.FMT142F, line.getInsurance()));
			dataLine.append(StringUtils.formattedValue(14, line.getInsurance()));

			dataLine.append(Constants.STRIP);
			// dataLine.append(String.format(Constants.FMT142F, line.getTotalCost()));
			dataLine.append(StringUtils.formattedValue(14, line.getTotalCost()));
			dataLine.append(Constants.STRIP);

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
