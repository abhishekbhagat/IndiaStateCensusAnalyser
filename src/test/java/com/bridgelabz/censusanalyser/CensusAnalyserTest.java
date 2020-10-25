package com.bridgelabz.censusanalyser;

import org.junit.Test;
import org.junit.Assert;

public class CensusAnalyserTest {
	private static final String INDIA_CENSUS_FILE_PATH = "C:\\Users\\abhis\\eclipse-workspace\\day21assignment\\src\\main\\java\\com\\bridgelabz\\censusanalyser\\IndiaStateCensusData.csv";
	private static final String WRONG_FILE_PATH ="";
	@Test
	public void givenIndianCensusData_ShouldReturnNoOfRecords() {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_FILE_PATH);
			Assert.assertEquals(5, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}
	@Test
	public void givenIndianCensusData_WithWrongFile_ShouldThrowExceptionCorrect() {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
		    censusAnalyser.loadIndiaCensusData(WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
		}
	}
}