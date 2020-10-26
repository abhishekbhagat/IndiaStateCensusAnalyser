package com.bridgelabz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CommonCsvBuilder implements ICSVBuilder {
	public <E> Iterator<E> getFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException, CSVException {
		try {
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			List<CSVRecord> records = csvParser.getRecords();
			List<E> recordsList = new ArrayList<>();
			for (int i = 1; i < records.size(); i++) {
				recordsList.add((E) records);
			}
			Iterator<E> iterator = recordsList.iterator();
			return iterator;
		} catch (IOException e) {
			throw new CSVException("Unable to parse", CSVException.ExceptionType.UNABLE_TO_PARSE);
		} catch (IllegalStateException e) {
			throw new CSVException("Unable to parse", CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}