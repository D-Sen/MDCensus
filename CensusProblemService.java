/**                    Program: Census Data
 *-------------------------------------------------------------------------------------
 *   Author: D. Senesi				             Compiler Used:  JGRASP 1.8
 *-------------------------------------------------------------------------------------
 *                  Census Data
 * Purpose: This service class reads from the included census file (a string literal) and attempts
 *          to process the data, and sort it in various ways. It provides header/column information 
 *          for each different type of sorting procedure , along with containing methods to sort the
 *          data for each different type of sorting procdure. It will output 5 pages in total, 
 *          each page containing a different method of sorting. The fifth and final page contains 3 
 *          different outputs, the first being the single record with the lowest change, the second
 *          being a sorted list ,and the third outputs singular records based on the user entered 
 *          rank number.
 *----------------------------------------------------------------------------------------
 *     Method Name                Description
 *----------------------------------------------------------------------------------------
 *
 *  topOfPageInfo()              Prints info about the programmer and the lab.  It also
 *                                prints current date and time when program is executed.
 *  storeCensusData( )           Reads the fixed-name input file,splitting each piece of 
 *                                information into a different array. Stores each line of 
 *                                data in the file for later retrival.
 *  computePercentChange( )      Compute percent change in income and store it in 
 *                                percentChange array. 
 *  columnHeading(int)           Method that prints appropriate table heading, five 
 *                                possible different column heading, 1 for each
 *                                different page type
 *  printData( )                 Prints the original list of data along with the 
 *                                percent change in income under appropriate 
 *                                column headings on Page#1. 
 *  alphabetize( )               Method that creats an alphabetized list of county/city 
 *                                names included in the data file. 
 *  sortByRanks( )               Method that sorts county/city ranks in 2000 
 *                                included in the data file in ascending order.
 *  sortByPercentChange( )       Method that sorts by percent change in descending order. 
 *                                Highest percentage change to lowest percentage change.  
 *  lowestIncome( )              Method that prints any records(2 total) which had 
 *                                a median household income that was lower in 2000, than in 1990.
 *  highestIncrease( )           Method that prints records of the top two counties with the highest
 *                                increase in dollar amounts in median household income in 2000
 *  highestRankIncrease()        Method that prints the top two counties starting 
 *                                with the lowest nationwide rank first (in 2000)
 *  inputRank()                  Method that receives input in the form of rank number(int) 
 *                                from the user and searches the records to attempt to find a 
 *                                matching record. Record will be printed if found, will 
 *                                continue to looping until user has entered the quit argument. 
 *----------------------------------------------------------------------------------------
 */


import java.util.*;
import java.text.DecimalFormat;
import java.io.*; //IO,IOException

public class CensusProblemService
{
 private int[] ranks2000 = new int [50];  
 private int[] ranks1990  = new int [50];
 private int[] incomes2000 = new int [50]; 
 private int[] incomes1990 = new int[50];  

 private String[] countyNames = new String [50];  
 
 double[] percentChange = new double [50];
 int count = 0;
 
 final String DASHLINE = "----------------------------------------------------------------------------";
 DecimalFormat percPattern = new DecimalFormat ("0.0");

 Scanner scan = new Scanner (System.in);
	  
 //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 /*
  *   This method reads the lines in the census file, then stores each token
  *   in it its own array.
  *
  *  @param    none
  *  @return   none
 */
 public void storeCensusData( )  throws IOException
 {  
  File textFile = new File("161P2CensusDataFile.txt");
  Scanner inputFile = new Scanner(textFile);
  
  while(inputFile.hasNext() && count < 50)
  {
   ranks2000[count]= inputFile.nextInt();
	ranks1990[count]= inputFile.nextInt();
   countyNames[count]= inputFile.next();
	incomes2000[count]= inputFile.nextInt();
   incomes1990[count]= inputFile.nextInt();
   count++;  
  } //end switch
  inputFile.close();
 } 
 
 //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 /*
  *  This method computes the percentage change in each record of the 
  *  census file using a for loop. It uses parellel arrays to keep the
  *  percentage in-line.    
  *
  *  @param    none
  *  @return   none
 */
 public void computePercentChange( )  
 {
 int index;
 for (index=0; index< count ; index++)
  percentChange[index] = (double)(incomes2000[index] - incomes1990[index]) / incomes1990[index] * 100;

 }
 
  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 /*
  *  The method prints the column heading as specified by the character 
  *  choice. Each choice correspondes to a different page number. 
  *
  *  @param    character choice
  *  @return   none
 */
 public void columnHeading(char choice )  
 {
  switch (choice)
  {
  case 'a':  
   System.out.println ("\t\tPage # 1\n");
   System.out.println ("\tOriginal List of Records\n"); 
   break;
  case 'b':  
   System.out.println ("\t\tPage # 2\n");  
   System.out.println ("\tAlphabetized List of Records\n");
   break;
  case 'c':  
   System.out.println ("\t\tPage # 3\n");  
   System.out.println ("\tList of Records sorted by Highest Rank\n");
   break;
  case 'd': 
   System.out.println ("\t\tPage # 4\n");  
   System.out.println ("\tList of Records sorted by lowest percentage\n");
   break;
  case 'e': 
   System.out.println ("\t\tPage # 5\n");
   System.out.println ("\tVarious top rankings from the list.\n");   
   break;
  }//end switch
  
  System.out.println (DASHLINE);
  System.out.println("Line \tNational\t\t    Median household Income");
  System.out.println(" #\t  Rank\t    Maryland\t\t(in dollars)\t      Percent");
  
  System.out.println("\t2000  1990  County            2000\t  1990\t      Change");
  
  System.out.println (DASHLINE); 
 }
 
 //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 /*
  *  The method prints each record via the data stored in the parellel arrays.
  *  It uses a loop and the counted number of total lines from the census file
  *  to print all records.
  *
  *  @param    none
  *  @return   none
 */
 public void printData( )
 {
  int index;
  for (index = 0; index < count ; index++)
  System.out.printf (" %2d.\t%-6d%-6d%-16s%7d\t%7d     %6.1f\n", index +1 ,ranks2000[index], ranks1990[index] , countyNames[index]
    , incomes2000[index],incomes1990[index],percentChange[index] );
  System.out.println("\n\t Total Number of Data Lines Processed  = " + count );
  System.out.println ("\n\n" + DASHLINE + "\n\n\n");
 
 }
 
 //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 /*
  *  This method takes the parellel arrays, and then attempts to sort them via 
  *  the the countyNames, in alphabetical order. It also includes code to 
  *  prevent misordering of the parellel arrays. 
  *
  *  @param    none
  *  @return   none
 */
 public void alphabetize( ) 
 {
  int i, j;
  String temp;
  int x,y,b,f;
  double p; 
 
  for (i=0; i< count-1; i++)
   for (j=i+1; j< count; j++)
    if (countyNames[i].compareTo(countyNames[j])> 0)
    {
     temp = countyNames[i]; 
     countyNames[i] = countyNames[j];
     countyNames[j] = temp;
 
     x = ranks2000[i];        
     ranks2000[i] = ranks2000[j];  
     ranks2000[j] = x;
 
     y = ranks1990[i];        
     ranks1990[i] = ranks1990[j];  
     ranks1990[j] = y;
	 
     b = incomes2000[i];        
     incomes2000[i] = incomes2000[j];  
     incomes2000[j] = b;
 
     f = incomes1990[i];        
     incomes1990[i] = incomes1990[j];  
     incomes1990[j] = f;
	 
     p = percentChange[i];        
     percentChange[i] = percentChange[j];  
     percentChange[j] = p; 
   } // end if
 }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 /*
  *  This method takes the parellel arrays, and then attempts to sort them via 
  *  the ranks2000, sorting them with the lowest value first, thereby being 
  *  in ascending order. It also includes code to prevent misordering of the 
  *  parellel arrays. 
  *  
  *
  *  @param    none
  *  @return   none
 */
 public void sortByRanks( )
 {
  int i, j, temp;
  int x,y,b,f;
  double p;
  String tempName;
 
  for (i=0; i< count-1; i++)
   for (j=i+1; j< count; j++)
    if (ranks2000[i]> ranks2000[j])
    {
     temp = ranks2000[i];      
     ranks2000[i] = ranks2000[j];
     ranks2000[j] = temp;
 
     tempName = countyNames[i];          
     countyNames[i] = countyNames[j];
     countyNames[j] = tempName;

     y = ranks1990[i];        
     ranks1990[i] = ranks1990[j];  
     ranks1990[j] = y;
 
     b = incomes2000[i];        
     incomes2000[i] = incomes2000[j];  
     incomes2000[j] = b;
 
     f = incomes1990[i];        
     incomes1990[i] = incomes1990[j];  
     incomes1990[j] = f;
 
     p = percentChange[i];        
     percentChange[i] = percentChange[j];  
     percentChange[j] = p; 
   } // end if
 }
 
 //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 /*
  *  This method takes the parellel arrays, and then attempts to sort them via 
  *  the percentage change, sorting them with the lowest value first, thereby being 
  *  in ascending order. It also includes code to prevent misordering of the 
  *  parellel arrays. 
  *  
  *
  *  @param    none
  *  @return   none
 */
 public void sortByPercentChange( )
 {
  int i, j;
  double temp;
  int x,y,b,f;
  String tempName;

  for (i=0; i<count; i++)
   for (j=i+1; j<count; j++) 
    if (percentChange[i]< percentChange[j])
    {
	  temp = percentChange[i];       
     percentChange[i] = percentChange[j];
     percentChange[j] = temp;
 
     x = ranks2000[j];           
     ranks2000[j] = ranks2000[j];
     ranks2000[j] = x;

     tempName = countyNames[j];  
     countyNames[j] = countyNames[j];
     countyNames[j] = tempName;
 
     y = ranks1990[j];        
     ranks1990[j] = ranks1990[j];  
     ranks1990[j+1] = y;
 
     b = incomes2000[j];        
     incomes2000[j] = incomes2000[j];  
     incomes2000[j] = b;
 
     f = incomes1990[j];        
     incomes1990[j] = incomes1990[j];  
     incomes1990[j] = f;
 
    } // end if
	 
 }
 
 //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 /*
  *  This method takes all the parellel arrays, and then searches for any 
  *  records that have a lower income in 2000 then in 1990. Only those records
  *  in the top two are then displayed.
  *
  *  @param    none
  *  @return   none
 */
 public void lowestIncome( )
 {
  int index;
   System.out.println ("\tMedian household incomes that decreased from 1990 to 2000\n");
   
    for (index=0; index < count; index++)
    if (incomes2000[index]< incomes1990[index]) System.out.printf (" %2d.\t%-6d%-6d%-16s%7d\t%7d     %6.1f\n", index +1 ,ranks2000[index], ranks1990[index] , countyNames[index] , incomes2000[index],incomes1990[index],percentChange[index] );
  System.out.println("\n" + DASHLINE + "\n");

 }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 /*
  *  The method sorts the records in the census file by highest increase 
  *  in dollars amount of median household income in 2000. It defines 
  *  counter variables and then sorts through the array.  Only those records
  *  in the top two are then displayed.
  *
  *  @param    none
  *  @return   none
 */
 public void highestIncrease( )
 {
  int i, j, temp;
  int x,y,b,f;
  double p;
  String tempName;
  int index;
  for (i=0; i< count; i++)
   for (j=i+1; j< count; j++)
    if ((incomes2000[i]- incomes1990[i]) < (incomes2000[j]- incomes1990[j]))
    {
     temp = ranks2000[i];      
     ranks2000[i] = ranks2000[j];
     ranks2000[j] = temp;
 
     tempName = countyNames[i];          
     countyNames[i] = countyNames[j];
     countyNames[j] = tempName;

     y = ranks1990[i];        
     ranks1990[i] = ranks1990[j];  
     ranks1990[j] = y;
 
     b = incomes2000[i];        
     incomes2000[i] = incomes2000[j];  
     incomes2000[j] = b;
 
     f = incomes1990[i];        
     incomes1990[i] = incomes1990[j];  
     incomes1990[j] = f;
 
     p = percentChange[i];        
     percentChange[i] = percentChange[j]; 
     percentChange[j] = p; 
    

    }
   System.out.println ("\tHighest increases in  median household income in 2000\n"); 
   for (index = 0; index < 2 ; index++)
  System.out.printf (" %2d.\t%-6d%-6d%-16s%7d\t%7d     %6.1f\n", index +1 ,ranks2000[index], ranks1990[index] , countyNames[index]
    , incomes2000[index],incomes1990[index],percentChange[index] );
	System.out.println("\n" + DASHLINE + "\n");
 }



 //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 /*
  *  The method prints complete record(s) of Maryland city/county's which 
  *  had the lowest nationwide rank in 2000. It defines counter variables 
  *  and then sorts through the array.  Only those records
  *  in the top two are then displayed.
  *
  *
  *  @param    none
  *  @return   none
 */
 public void highestRankIncrease( )
 {
  int i, j, temp;
  int x,y,b,f;
  double p;
  String tempName;
  int index = 0;
  for (i=0; i< count; i++)
   for (j=i+1; j< count; j++)
    if (ranks2000[i]< ranks2000[j])
    {
     temp = ranks2000[i];      
     ranks2000[i] = ranks2000[j];
     ranks2000[j] = temp;
 
     tempName = countyNames[i];          
     countyNames[i] = countyNames[j];
     countyNames[j] = tempName;

     y = ranks1990[i];        
     ranks1990[i] = ranks1990[j];  
     ranks1990[j] = y;
 
     b = incomes2000[i];        
     incomes2000[i] = incomes2000[j];  
     incomes2000[j] = b;
 
     f = incomes1990[i];        
     incomes1990[i] = incomes1990[j];  
     incomes1990[j] = f;
 
     p = percentChange[i];        
     percentChange[i] = percentChange[j];  
     percentChange[j] = p; 
    } 
  System.out.println ("\tLowest nationwide ranks among Households (2000)\n");
  
  for (index = 0; index < 2 ; index++)
   System.out.printf (" %2d.\t%-6d%-6d%-16s%7d\t%7d     %6.1f\n", index +1 ,ranks2000[index], ranks1990[index] , countyNames[index]
     , incomes2000[index],incomes1990[index],percentChange[index] );
  System.out.println("\n" + DASHLINE + "\n");
 }

 
 //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 /*
  *  Search the array to print name of county or city (if any) with a 
  *  given rank in year 2000. Sets counter variables, then uses the 
  *  Scanner object to read user input in the while statement. A sentinel 
  *  character and decision structure is placed in the loop to stop it.  
  *  
  *  @param    none
  *  @return   none
 */
 public void inputRank()
 {
  int enteredRank;
  int index,dd,i = 1;
  String inpLine;
  
  System.out.println (DASHLINE + "\n");
  
  System.out.println ("\n\tSearch for a County by it's ranking");
  
  while(i == 1)
  {
   dd = 0;
   System.out.print ("\n Enter rank # to search, or q to quit :");
   inpLine = scan.nextLine();
   if (inpLine.charAt(0) == 'q') 
	{
    System.out.println ("\n Quit Chosen, Program Ending.");
    break;
   }
   //NEW 11/20/23
   try{
	enteredRank = Integer.parseInt(inpLine); 
   }catch (NumberFormatException ex) {
    //handle exception here
    System.out.println ("\n\tInput was not a valid rank #.");
    enteredRank = 0;
   }
   //END NEW
   for (index = 0; index < count ; index++)
    if (enteredRank == ranks2000[index]) 
	 {
	  dd = 1;
     System.out.println ("\n\tCounty Found : " + countyNames[index]);
    }
	if (dd ==0) System.out.println ("\n\tCounty Not Found.");       
   }
}



 //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 /*
  *  The method prints programmer and lab info. Programmer name is declares as a constant,
  *  so is a string of dashes in the class. It also prints due date, and current date on which
  *  the program was executed.  
  *
  *  @param    none
  *  @return   none
 */
 public void topOfPageInfo()
  {
    final String MY_NAME = "D.Senesi";
    System.out.println (DASHLINE);   
    System.out.println ("\t\tName: " + MY_NAME);
    
    GregorianCalendar today = new GregorianCalendar( );
	 DecimalFormat twoDigits = new DecimalFormat ("00");
    System.out.println ("\t\tToday's Date:  "
                                  + twoDigits.format(today.get(Calendar.MONTH)+1) //get the current month#
                                  + '/' + twoDigits.format(today.get(Calendar.DAY_OF_MONTH)) //get day
                                  + '/' +twoDigits.format(today.get(Calendar.YEAR))
											 + " " + twoDigits.format(today.get(Calendar.HOUR))
                                  + ':' + twoDigits.format(today.get(Calendar.MINUTE))
                                  + ':' +twoDigits.format(today.get(Calendar.SECOND))); 
    System.out.println (DASHLINE);
  } //end of topOfPageInfo( )

} //end of class


    
