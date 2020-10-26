package com.bridgelabz.censusanalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

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
			CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IndiaCensusCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
			int numOfEntries = 0;
			while (censusCSVIterator.hasNext()) {
				IndiaCensusCSV censusData = censusCSVIterator.next();
				numOfEntries++;
			}
			return numOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
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
			CsvToBeanBuilder<CSVStates> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(CSVStates.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVStates> csvToBean = csvToBeanBuilder.build();
			Iterator<CSVStates> censusCSVIterator = csvToBean.iterator();
			int numOfEntries = 0;
			while (censusCSVIterator.hasNext()) {
				CSVStates censusData = censusCSVIterator.next();
				numOfEntries++;
			}
			return numOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}
