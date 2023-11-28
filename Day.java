/**
 * Assignment 5
 * Student: Chris Bierley
 *
 * Resources:
 * (Part 1) Used Day.java file provided on canvas (the first one of the three)
 * (Part 2) https://runestone.academy/ns/books/published/csjava/Unit6-Writing-Classes/topic-6-3-comments-conditions.html
 * 
 * Notes:
 * (Part 1) 
 * I realized I could probably just implement my bday instead of asking for Scanner input (because req is generic)...
 * I didn't implement all the scanner inputs for bad inputs either. ie, putting in a string for year. Will return error.
 * I wasn't sure how to implement all the code for the code given. 
 * I spent many hours and revisiting it over a few days with no great progress for implementing given code. 
 * Not willing to hit my head against a wall anymore, ended up babying the result...
 * 
 * (Part 2)
 * Part 2 is at the bottom! It's commented out so it doesn't effect the other code.
 * Wasn't sure exactly what the problem wanted me to do.
 * I assumed it wanted me to make a precondition so that the code given was viable?
 * I feel like the problem could be interpreted a few ways...
 */

//   --------------------------- Part 1 --------------------------------

import java.util.Scanner;


public class Day
{
   
	public static void main(String[] args) {      
		
		Scanner myBDay = new Scanner(System.in);
		
		
		
        System.out.print("Please input year born \n");
        int year = myBDay.nextInt();
        //birthDay.setYear(year);
        
        System.out.print("Please input month born (1-12) \n");
        int month = myBDay.nextInt();
        
        System.out.print("Please input day born (1-31) \n");
        int day = myBDay.nextInt();
        
        Day birthDay = new Day(year, month, day);
                
        
        System.out.println("Birthday: " + birthDay.getYear() + "-" + birthDay.getMonth() + "-" + birthDay.getDate());
        
        int pYear = 2022;
        int pMonth = 10;
        int cDay = 27;
        
        int total = (365 * (pYear - year) + ((pMonth*30) - (month*30)) + (cDay - day));
        
        System.out.println("Total days you have lived: " + total);
		
    }
	
	
   public Day(int aYear, int aMonth, int aDate)
   {
      year = aYear;
      month = aMonth;
      date = aDate;
   }
   
   public void setYear(int aYear) {
       year = aYear;
   }

   public void setMonth(int aMonth) {
       month = aMonth;
   }

   public void setDate(int aDate) {
       date = aDate;
   }
   
   /**
      Returns the year of this day
      @return the year
   */
   public int getYear()
   {
      return year;
   }

   /**
      Returns the month of this day
      @return the month
   */
   public int getMonth()
   {
      return month;
   }

   /**
      Returns the day of the month of this day
      @return the day of the month
   */
   public int getDate()
   {
      return date;
   }

   /**
      Returns a day that is a certain number of days away from
      this day
      @param n the number of days, can be negative
      @return a day that is n days away from this one
   */
   public Day addDays(int n)
   {
      Day result = this;
      while (n > 0)
      {
         result = result.nextDay();
         n--;
      }
      while (n < 0)
      {
         result = result.previousDay();
         n++;
      }
      return result;
   }

   /**
      Returns the number of days between this day and another
      day
      @param other the other day
      @return the number of days that this day is away from 
      the other (>0 if this day comes later)
   */
   public int daysFrom(Day other)
   {
      int n = 0;
      Day d = this;
      while (d.compareTo(other) > 0)
      {
         d = d.previousDay();
         n++;
      }
      while (d.compareTo(other) < 0)
      {
         d = d.nextDay();
         n--;
      }
      return n;
   }

   /**
      Compares this day with another day.
      @param other the other day
      @return a positive number if this day comes after the
      other day, a negative number if this day comes before
      the other day, and zero if the days are the same
   */
   private int compareTo(Day other)
   {
      if (year > other.year) return 1;
      if (year < other.year) return -1;
      if (month > other.month) return 1;
      if (month < other.month) return -1;
      return date - other.date;
   }

   /**
      Computes the next day.
      @return the day following this day
   */
   private Day nextDay()
   {
      int y = year;
      int m = month;
      int d = date;

      if (y == GREGORIAN_START_YEAR
            && m == GREGORIAN_START_MONTH
            && d == JULIAN_END_DAY)
         d = GREGORIAN_START_DAY;
      else if (d < daysPerMonth(y, m))
         d++;
      else
      {
         d = 1;
         m++;
         if (m > DECEMBER) 
         { 
            m = JANUARY; 
            y++; 
            if (y == 0) y++;
         }
      }
      return new Day(y, m, d);
   }

   /**
      Computes the previous day.
      @return the day preceding this day
   */
   private Day previousDay()
   {
      int y = year;
      int m = month;
      int d = date;

      if (y == GREGORIAN_START_YEAR
            && m == GREGORIAN_START_MONTH
            && d == GREGORIAN_START_DAY)
         d = JULIAN_END_DAY;
      else if (d > 1)
         d--;
      else
      { 
         m--;
         if (m < JANUARY) 
         {             
            m = DECEMBER; 
            y--; 
            if (y == 0) y--;
         }
         d = daysPerMonth(y, m);
      }
      return new Day(y, m, d);
   }

   /**
      Gets the days in a given month
      @param y the year
      @param m the month
      @return the last day in the given month
   */
   private static int daysPerMonth(int y, int m)
   {
      int days = DAYS_PER_MONTH[m - 1];
      if (m == FEBRUARY && isLeapYear(y)) 
         days++;
      return days;
   }

   /**
      Tests if a year is a leap year
      @param y the year
      @return true if y is a leap year
   */
   private static boolean isLeapYear(int y)
   {
      if (y % 4 != 0) return false;
      if (y < GREGORIAN_START_YEAR) return true;
      return (y % 100 != 0) || (y % 400 == 0);
   }

   private int year;
   private int month;
   private int date;

   private static final int[] DAYS_PER_MONTH 
         = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

   private static final int GREGORIAN_START_YEAR = 1582;
   private static final int GREGORIAN_START_MONTH = 10;
   private static final int GREGORIAN_START_DAY = 15;
   private static final int JULIAN_END_DAY = 4;

   private static final int JANUARY = 1;
   private static final int FEBRUARY = 2;
   private static final int DECEMBER = 12;
}



//--------------------------- Part 2 --------------------------------

// Precondition = A precondition is a condition that must be true for your method code to work, 
//                for example the assumption that the parameters have values and are not null. 

// Question: 2. Write a precondition or requires clause for the method removeDuplicates, 
//              so all duplicates from List lst are removed.

// Answer: pseudo code
//           if (ArrayList(1st) has duplicates)
//             removeDuplicates(List 1st)
//           
//         This is a precondition that the list 1st requires for duplicates to be removed to be true

//         Also to note the line:
//        if (lst == null || lst.size() == 0) return;
//         Is also a good precondition inside the code.

/*
public static void removeDuplicates(List lst) {

	if (lst == null || lst.size() == 0) return;
		List copy = new ArrayList(lst);
		Iterator elements = copy.iterator();
		Object pre = elements.next();
		while(elements.hasNext()) {
			Object nex = elements.next();
			if (pre.equals(nex)) lst.remove(nex);
			else pre = nex;
		}
}
*/
