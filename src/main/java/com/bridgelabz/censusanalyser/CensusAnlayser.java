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
	private List<IndiaCensusCSV> censusCSVList=null;
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
			censusCSVList = OpenCSVBuilder.getCSVFileList(reader, IndiaCensusCSV.class);
			return censusCSVList.size();
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

	public String getStatedWiseSortedCensusData() throws CensusAnalyserException{
        if(censusCSVList==null ||censusCSVList.size()==0)
        	throw new CensusAnalyserException("No Census Data",
					CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		Comparator<IndiaCensusCSV> censusComparator=Comparator.comparing(census->census.state);
        this.sort(censusComparator);
	    String sortedStateCensus =new Gson().toJson(censusCSVList);
	    return sortedStateCensus;
	}
	private void sort(Comparator<IndiaCensusCSV> censusComparator)
   {
   	for(int i=0;i<censusCSVList.size()-1;i++) {
       for(int j=0;j<censusCSVList.size()-i-1;j++) {
    	   IndiaCensusCSV census1=censusCSVList.get(i);
    	   IndiaCensusCSV census2=censusCSVList.get(j+1);
    	   if(censusComparator.compare(census1,census2)>0) {
    		   censusCSVList.set(i,census2);
    		   censusCSVList.set(j+1,census1);
    	   }
       }
	}
  }
}
