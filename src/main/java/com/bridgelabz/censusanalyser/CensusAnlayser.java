package com.bridgelabz.censusanalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

public class CensusAnlayser {
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException, CSVException {
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
			List<IndiaCensusCSV> censusCSVIterator = OpenCSVBuilder.getCSVFileList(reader, IndiaCensusCSV.class);
			return censusCSVIterator.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public int loadIndiaStateCodeCensusData(String csvFilePath) throws CensusAnalyserException, CSVException {
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
			List<CSVStates> censusCSVIterator = OpenCSVBuilder.getCSVFileList(reader, CSVStates.class);
			return censusCSVIterator.size();
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

	public String getStatedWiseSortedCensusData(String indiaCensusFilePath) throws IOException, CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(indiaCensusFilePath)))
		{
			ICSVBuilder OpenCSVBuilder = CSVBuilderFactory.createCSVBuilder();
			List<IndiaCensusCSV> censusCSVList = OpenCSVBuilder.getCSVFileList(reader, IndiaCensusCSV.class);
		    Comparator<IndiaCensusCSV> censusComparator=Comparator.comparing(census->census.state);
		    this.sort(censusCSVList,censusComparator);
		    String sortedStateCensus =new Gson().toJson(censusCSVList);
		    return sortedStateCensus;
		}
		catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
		catch(CSVException e) {
			throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	private void sort(List<IndiaCensusCSV> censusList,Comparator<IndiaCensusCSV> censusComparator)
   {
   	for(int i=0;i<censusList.size()-1;i++) {
       for(int j=0;j<censusList.size()-i-1;j++) {
    	   IndiaCensusCSV census1=censusList.get(i);
    	   IndiaCensusCSV census2=censusList.get(j+1);
    	   if(censusComparator.compare(census1,census2)>0) {
    		   censusList.set(i,census2);
    		   censusList.set(j+1,census1);
    	   }
       }
	}
  }
}
