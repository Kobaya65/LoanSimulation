package fr.formation.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * A class containing some string utility functions.
 * 
 * @author Philippe AMICE
 *
 */
public class StringUtils {
	/**
	 * Aims at creating a string containing <i>n</i> times a given string.
	 * 
	 * @param what    string to be repeated
	 * @param howmany number of times to repeat {@code what}
	 * 
	 * @return the string with {@code what} repeated {@code howmany} times
	 */
	public static String repeatStr(String what, int howmany) {
		char[] pattern = what.toCharArray();
		char[] res = new char[howmany * pattern.length];
		int length = pattern.length;

		for (int i = 0; i < howmany; i++)
			System.arraycopy(pattern, 0, res, i * length, length);

		return new String(res);
	}

	/**
	 * right pad a given number in a given width of string.
	 * 
	 * @param width width of the string to be filled
	 * @param value value to be right padded
	 * @return the right padded number
	 */
	public static String formattedValue(long width, BigDecimal value) {
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.FRANCE);
		numberFormat.setGroupingUsed(true);
		numberFormat.setMaximumIntegerDigits(9);
		numberFormat.setMinimumFractionDigits(2);
		numberFormat.setMaximumFractionDigits(2);

		String n = numberFormat.format(value);
		String m = repeatStr(String.valueOf(Constants.SPACE), (int) (width - n.length()));

		return m.concat(n);
	}
}
