package com.bridgelabz.censusanalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
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
			Iterator<IndiaCensusCSV> censusCSVIterator =getIndiaCensusFileIterator(reader,IndiaCensusCSV.class); 
			int numOfEntries = 0;
			while (censusCSVIterator.hasNext()) {
				IndiaCensusCSV censusData = censusCSVIterator.next();
				numOfEntries++;
			}
			return numOfEntries;
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
			Iterator<CSVStates> censusCSVIterator =getIndiaStatCodeFileIterator(reader,CSVStates.class);
			int numOfEntries = 0;
			while (censusCSVIterator.hasNext()) {
				CSVStates censusData = censusCSVIterator.next();
				numOfEntries++;
			}
			return numOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	private Iterator<IndiaCensusCSV> getIndiaCensusFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException {
		try {
			CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<IndiaCensusCSV>(reader);
			csvToBeanBuilder.withType(IndiaCensusCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException("Invalid type", CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
	private Iterator<CSVStates> getIndiaStatCodeFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException {
		try {
			CsvToBeanBuilder<CSVStates> csvToBeanBuilder = new CsvToBeanBuilder<CSVStates>(reader);
			csvToBeanBuilder.withType(CSVStates.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVStates> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException("Invalid type", CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}
