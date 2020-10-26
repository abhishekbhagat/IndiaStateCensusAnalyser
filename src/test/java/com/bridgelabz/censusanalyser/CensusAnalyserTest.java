package com.bridgelabz.censusanalyser;

import org.junit.Test;

import com.google.gson.Gson;

import java.io.IOException;

import org.junit.Assert;

public class CensusAnalyserTest {
	private static final String INDIA_CENSUS_FILE_PATH = "C:\\Users\\abhis\\eclipse-workspace\\day21assignment\\src\\main\\java\\com\\bridgelabz\\censusanalyser\\IndiaStateCensusData.csv";
	private static final String WRONG_FILE_PATH = "";
	private static final String INDIA_STATE_CODE_FILE_PATH = "C:\\Users\\abhis\\eclipse-workspace\\day21assignment\\src\\main\\java\\com\\bridgelabz\\censusanalyser\\IndianStateCodeData.csv";
	private static final String CENSUS_INVALID_DELIMETER_FILE_PATH = "C:\\Users\\abhis\\eclipse-workspace\\day21assignment\\src\\main\\java\\com\\bridgelabz\\censusanalyser\\IndiaStateCensusDelimeter.csv";
	private static final String CENSUS_INVALID_HEADER_FILE_PATH = "C:\\Users\\abhis\\eclipse-workspace\\day21assignment\\src\\main\\java\\com\\bridgelabz\\censusanalyser\\IndiaStateCensusInvalidHeader.csv";
	private static final String INDIA_STATE_CODE_INVALID_DELIMETER_FILE_PATH = "C:\\Users\\abhis\\eclipse-workspace\\day21assignment\\src\\main\\java\\com\\bridgelabz\\censusanalyser\\IndiaStateCodeInvalidDelimeter.csv";
	private static final String WRONG_FILE_TYPE_PATH = "F:\\ContactPerson-File.txt";

	@Test
	public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult()
			throws CSVException, IOException {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_FILE_PATH);
			String sortedCensusData = censusAnalyser.getPopulation_WiseSortedCensusData();
			IndiaCensusCSV[] censusList = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
			Assert.assertEquals(198492341, censusList[0].population);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
		}
	}

	@Test
	public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResult()
			throws CSVException, IOException {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_FILE_PATH);
			String sortedCensusData = censusAnalyser.getDensityPerSqKm_WiseSortedCensusData();
			IndiaCensusCSV[] censusList = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
			Assert.assertEquals(1102, censusList[0].densityPerSqKm);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
		}
	}

	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() throws CSVException, IOException {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_FILE_PATH);
			String sortedCensusData = censusAnalyser.getStatedWiseSortedCensusData();
			IndiaCensusCSV[] censusList = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
			Assert.assertEquals("Bengal", censusList[0].state);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
		}
	}

	@Test
	public void givenIndiaStateCodeCensusData_WhenSortedOnStateCode_ShouldReturnSortedResult()
			throws CSVException, IOException {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			String sortedCensusData = censusAnalyser.getStatedWiseSortedCensusData();
			CSVStates[] censusList = new Gson().fromJson(sortedCensusData, CSVStates[].class);
			Assert.assertEquals("BH", censusList[0].stateCode);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
		}
	}

	@Test
	public void givenIndianCensusData_ShouldReturnNoOfRecords() throws CSVException {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_FILE_PATH);
			Assert.assertEquals(5, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenIndianCensusData_WithWrongFile_ShouldThrowExceptionCorrect() throws CSVException {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			censusAnalyser.loadIndiaCensusData(WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndianCensusData_WithWrongFile_Type_ShouldThrowCustomExceptiont() throws CSVException {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			censusAnalyser.loadIndiaCensusData(WRONG_FILE_TYPE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndianCensusData_With_Invalid_Delimeter_ShouldThrowCustomExceptiont() throws CSVException {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			censusAnalyser.loadIndiaCensusData(CENSUS_INVALID_DELIMETER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMETER_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndianCensusData_With_Invalid_Header_ShouldThrowCustomExceptiont() throws CSVException {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			censusAnalyser.loadIndiaCensusData(CENSUS_INVALID_HEADER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaStateCodeData_ShouldReturnNoOfRecord() throws CSVException {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			int numOfRecords = censusAnalyser.loadIndiaStateCodeCensusData(INDIA_STATE_CODE_FILE_PATH);
			Assert.assertEquals(6, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenIndiaStateCodeData_WithWrongFile_ShouldThrowCustomExceptiont() throws CSVException {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			censusAnalyser.loadIndiaStateCodeCensusData(WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaStateCodeData_WithWrongFile_Type_ShouldThrowCustomExceptiont() throws CSVException {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			censusAnalyser.loadIndiaStateCodeCensusData(WRONG_FILE_TYPE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaStateCodeData_With_Invalid_Delimeter_ShouldThrowCustomExceptiont() throws CSVException {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			censusAnalyser.loadIndiaStateCodeCensusData(INDIA_STATE_CODE_INVALID_DELIMETER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMETER_PROBLEM, e.type);
		}
	}

}
