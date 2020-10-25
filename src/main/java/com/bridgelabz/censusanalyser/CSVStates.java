package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {
	@CsvBindByName(column = "State")
	public String state;
	@CsvBindByName(column = "StateCode")
	public String stateCode;

	@Override
	public String toString() {
		return "CSVStates [state=" + state + ", stateCode=" + stateCode + "]";
	}

}
