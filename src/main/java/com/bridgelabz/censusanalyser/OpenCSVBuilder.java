package com.bridgelabz.censusanalyser;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements ICSVBuilder {
	public <E> Iterator<E> getFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException, CSVException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} 
		catch (IllegalStateException e) {
			throw new CSVException(e.getMessage(), CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}
