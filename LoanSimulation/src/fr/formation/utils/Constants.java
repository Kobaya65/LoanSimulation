package fr.formation.utils;

class Constants {
	protected static final char space = ' ';

	protected static final String AMOUNT = "amount";
	protected static final String AMNTGTE100 = "Amount of the loan must be greater than or equal to 100";
	protected static final String LOANTYPE = "loan type ('RE = Real Estate', 'AU = Auto' or 'WO = Works')";
	protected static final String ONLYREAUWO = "Loan type can only be RE, AU or WO";
	protected static final String DURATIONYEARS = "duration in year(s)";
	protected static final String DURATIONBTW1AND30 = "Duration must be between 1 and 30 years both included";
	protected static final String FORINPUTSTRING = "For input string";
	protected static final String INTRATEDOTDECIMALSEPAR = "interest rate with a dot as decimal separator (ex. 3.26 for 3.26%)";
	protected static final String ENTERNUMBERGT0 = "Please enter a number >0";
	protected static final String RATEGT0 = "Rate must be greater than 0";
	protected static final String DATEFORMAT10 = "dd/MM/yyyy";
	protected static final String ILLEGALDATE = "Date must be after today.";
	protected static final String STARTDATEDDMMYYYY = "start date (".concat(DATEFORMAT10).concat(")");
	protected static final String WRONGDATEFORMAT = "Wrong date format. Expected format is '".concat(DATEFORMAT10)
			.concat("'");
	protected static final String FUCKINGBASTARD = "\nYOU FUCKING BASTARD !\nENTER A DATE > TODAY WITH THAT FORMAT 'dd/mm/yyyy'\nAND NOTHING ELSE. OK ?\n";
	protected static final String INSURATEDOTDECIMALSEPAR = "insurance rate with a dot as decimal separator (ex. 1.98 for 1.98%)";
}
