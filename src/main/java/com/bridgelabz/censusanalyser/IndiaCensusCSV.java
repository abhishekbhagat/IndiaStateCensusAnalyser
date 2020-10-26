package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {

	@CsvBindByName(column = "State")
	public String state;
	@CsvBindByName(column = "Population")
	public int population;
	@CsvBindByName(column = "AreaSqKm")
	public int areaSqKm;
	@CsvBindByName(column = "DensityPerSqKm")
	public int densityPerSqKm;

	@Override
	public String toString() {
		return "IndianCensusAnalyser [population=" + population + ", state=" + state + ", areaInSqKm=" + areaSqKm
				+ ", densityPerSqKm=" + densityPerSqKm + "]";
	}

}
