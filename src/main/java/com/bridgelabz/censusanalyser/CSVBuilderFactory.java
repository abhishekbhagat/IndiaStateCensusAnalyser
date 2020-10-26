package com.bridgelabz.censusanalyser;

public class CSVBuilderFactory<E> {
	public static ICSVBuilder createCSVBuilder() {
		return (ICSVBuilder) new OpenCSVBuilder();
	}
}
