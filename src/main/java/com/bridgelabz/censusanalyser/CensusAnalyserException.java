package com.bridgelabz.censusanalyser;

public class CensusAnalyserException extends Exception {
	enum ExceptionType {
		CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE, DELIMETER_PROBLEM, HEADER_PROBLEM, NO_CENSUS_DATA;
	}

	ExceptionType type;

	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}
