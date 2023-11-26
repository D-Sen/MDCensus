/*
 *---------------------------------------------------------------------------------------
 *     Method Name                Description
 *---------------------------------------------------------------------------------------
 *   main()    It creates an object of the CensusProblemService class, and calls 
 *             10~ of its methods. All output printing is handled in the service class.
 *---------------------------------------------------------------------------------------
 */

import java.io.*;

public class CensusProblemClient 
{
 public static void main(String[] arg) throws IOException
 {
  CensusProblemService cenObject = new CensusProblemService();
  
  cenObject.storeCensusData();
  
  cenObject.computePercentChange();
  cenObject.columnHeading('a');
  cenObject.printData();
 
  cenObject.alphabetize();
  cenObject.columnHeading('b');
  cenObject.printData();  

  cenObject.sortByRanks();
  cenObject.columnHeading('c');
  cenObject.printData();
  
  cenObject.sortByPercentChange();
  cenObject.columnHeading('d');
  cenObject.printData();
 
  cenObject.columnHeading('e'); 
  cenObject.lowestIncome();  
  cenObject.highestIncrease( );
  cenObject.highestRankIncrease( );
  
  cenObject.inputRank( );
  
  } //end main()
	
}//end class 

