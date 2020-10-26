package com.bridgelabz.censusanalyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E> {
	public <E> Iterator<E> getCsvFileIterator(Reader reader, Class csvClass) throws CSVException;

	public List<E> getCSVFileList(Reader reader, Class csvClass) throws CSVException;
}
