package fr.formation.utils;

/**
 * All constants needed for this project are grouped here.
 * 
 * @author Philippe AMICE
 *
 */
public class Constants {
	/**
	 * a single SPACE
	 */
	public static final char SPACE = ' ';
	/**
	 * addition sign
	 */
	public static final char PLUS = '+';
	/**
	 * vertical bar
	 */
	public static final char STRIP = '|';
	/**
	 * the string "A"
	 */
	public static final String EQA = "A";
	/**
	 * text displayed in scanner invite for amount of loan
	 */
	public static final String AMOUNT = "amount";
	/**
	 * text displayed in scanner if loan amount is less than 1000
	 */
	public static final String AMNTGTETHOUSAND = "Amount of the loan must be greater than or equal to 1000";
	/**
	 * text displayed in scanner to choose between Real Estate, AUtomotive or WOrks
	 */
	public static final String LOANTYPE = "loan type ('RE = Real Estate', 'AU = Auto' or 'WO = Works')";
	/**
	 * text displayed in scanner if entry is not RE, AU or WO
	 */
	public static final String ONLYREAUWO = "Loan type can only be RE, AU or WO";
	/**
	 * text displayed in scanner to specify the enter in years
	 */
	public static final String DURATIONYEARS = "duration in year(s)";
	/**
	 * text displayed in scanner to indicate a range of values for duration
	 */
	public static final String DURATIONBTW1AND30 = "Duration must be between 1 and 30 years both included";
	/**
	 * used in error catching to discriminate message beginning with that string
	 * from the others
	 */
	public static final String FORINPUTSTRING = "For input string";
	/**
	 * text invite to enter interest rate with a dot as decimal separator, with an
	 * example
	 */
	public static final String INTRATEDOTDECIMALSEPAR = "interest rate with a dot as decimal separator (ex. 3.26 for 3.26%)";
	/**
	 * text invite to enter insurance rate with a dot as decimal separator, with an
	 * example
	 */
	public static final String INSURATEDOTDECIMALSEPAR = "insurance rate with a dot as decimal separator (ex. 1.98 for 1.98%)";
	/**
	 * used in error catching when expected number is {@code&lte}0
	 */
	public static final String ENTERNUMBERGT0 = "Please enter a number >0";
	/**
	 * used in error catching when expected rate is {@code&lte}0
	 */
	public static final String RATEGT0 = "This rate must be greater than 0";
	/**
	 * date format with four digit year
	 */
	public static final String DATEFORMAT10 = "dd/MM/yyyy";
	/**
	 * used in error catching when date isn't greater than today
	 */
	public static final String ILLEGALDATE = "Date must be after today.";
	/**
	 * invite for entry the date with four digit year
	 */
	public static final String STARTDATEDDMMYYYY = "start date (".concat(DATEFORMAT10).concat(")");
	/**
	 * used in error catching when date entered with a wrong format
	 */
	public static final String WRONGDATEFORMAT = "Wrong date format. Expected format is '".concat(DATEFORMAT10)
			.concat("'");
	/**
	 * hum... this is the message when the user is...unbearable !
	 */
	public static final String FUCKINGBASTARD = "\nYOU FUCKING BASTARD !\nENTER A DATE > TODAY WITH THAT FORMAT 'dd/mm/yyyy'\nAND NOTHING ELSE. OK ?\n";
	/**
	 * line of 1 minus sign, the exact width of a column in the amortization table
	 */
	public static final String LINE14 = "--------------";
	/**
	 * format of number with two decimals
	 */
	public static final String FMT142F = "%14.2f";
	/**
	 * for annual table, display the year's rank, then the year within a 14
	 * character width column
	 */
	public static final String FMTNBPLUSYEAR = "%1$6d - %2$4s ";
}
