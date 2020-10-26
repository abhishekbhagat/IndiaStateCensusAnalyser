package com.bridgelabz.censusanalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnlayser {
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
			String indiaStateCensusData = "";
			while ((indiaStateCensusData = br.readLine()) != null) {
				if (!indiaStateCensusData.contains(","))
					throw new CensusAnalyserException("Invalid delimiter in csv file",
							CensusAnalyserException.ExceptionType.DELIMETER_PROBLEM);
			}
			br.close();
			ICSVBuilder OpenCSVBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<IndiaCensusCSV> censusCSVIterator = OpenCSVBuilder.getFileIterator(reader, IndiaCensusCSV.class);
			return getCount(censusCSVIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public int loadIndiaStateCodeCensusData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
			String indiaStateCode = "";
			while ((indiaStateCode = br.readLine()) != null) {
				if (!indiaStateCode.contains(","))
					throw new CensusAnalyserException("Invalid delimiter in csv file",
							CensusAnalyserException.ExceptionType.DELIMETER_PROBLEM);
			}
			br.close();
			ICSVBuilder OpenCSVBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<CSVStates> censusCSVIterator = OpenCSVBuilder.getFileIterator(reader, CSVStates.class);
			return getCount(censusCSVIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	private <E> int getCount(Iterator<E> iterator) {
		int numOfEntries = 0;
		while (iterator.hasNext()) {
			E iteratorData = iterator.next();
			numOfEntries++;
		}
		return numOfEntries;
	}
}
