package bridgelabz.censusanalyser;

import org.junit.Test;
import org.junit.Assert;
import com.bridgelabz.censusanalyser.CensusAnalyserException;
import com.bridgelabz.censusanalyser.CensusAnlayser;

public class CensusAnalyserTest {
	private static final String INDIA_CENSUS_FILE_PATH = "C:\\Users\\abhis\\eclipse-workspace\\day21assignment\\src\\main\\java\\com\\bridgelabz\\censusanalyser\\IndiaStateCensusData.csv";

	@Test
	public void givenIndianCensusCorrectRecords() {
		try {
			CensusAnlayser censusAnalyser = new CensusAnlayser();
			int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_FILE_PATH);
			Assert.assertEquals(5, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}
}
