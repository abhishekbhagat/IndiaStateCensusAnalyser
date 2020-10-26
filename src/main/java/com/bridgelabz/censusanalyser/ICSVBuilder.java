package com.bridgelabz.censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
	public <E> Iterator<E> getFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException;
}
