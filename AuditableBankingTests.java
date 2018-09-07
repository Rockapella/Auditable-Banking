//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Auditable Banking
// Files:           (a list of all source files used by that program)
// Course:          CS300, Fall, 2018
//
// Author:          John Bednarczyk
// Email:           jbednarczyk@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Arrays;

public class AuditableBankingTests {
	public static void main(String[] args) {
		testProcessCommand();
		testCalculateCurrentBalance();
		testCalculateNumberOfOverdrafts();
	}
	
	public static boolean testProcessCommand() {
		
		// Oversize Array
		int[][] allTransactions = new int[10][];
		
		int passed = 0;
		int tests = 3;
		
		AuditableBanking.processCommand("0 0 1 1 0 1 1", allTransactions, 1);
		
		
		System.out.println("---- ProcessCommand Test results ----");
		
		if (allTransactions[1][2] == 1) {
			System.out.println("- Test 1 Passed");
			passed++;
		}
		else {
			System.out.println("- Test 1 Failed (Number at index 2 is not equal to 1)");
		}
		if (allTransactions[1][0] == 0) {
			System.out.println("- Test 2 Passed");
			passed++;
		}
		else {
			System.out.println("- Test 2 Failed (Number at index 0 is not equal to 0)");
		}
		if (allTransactions[1][6] == 1) {
			System.out.println("- Test 3 Passed");
			passed++;
		}
		else {
			System.out.println("- Test 3 Failed (Number at index 6 is not equal to 1)");
		}
		
		System.out.println("Passed " + passed + " out of " + tests + " tests");
		
		System.out.println();
		
		if (passed == tests) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean testCalculateCurrentBalance() {
		
		int[][] allTransactions = new int[5][];
		int allTransactionsCount = 1;
	    int tests = 3;
		int passed = 0;
		int balance = 0;
	
		
		System.out.println("---- CalculateCurrentBalance Test results ----");
	
	
		allTransactions[0] = new int[] {0,1,1,0,1};
		balance = AuditableBanking.calculateCurrentBalance(allTransactions, allTransactionsCount);
		
		if (balance == 2) {
			System.out.println("- Test 1 Passed");
			passed++;
		}
		else {
			System.out.println("- Test 1 Failed (Balance was \"" + balance + "\", balance should have been \"2\"");
		}
		
		allTransactions[0] = new int[] {1,20,-1,40,1};
		balance = AuditableBanking.calculateCurrentBalance(allTransactions, allTransactionsCount);
		
		if (balance == 60) {
			System.out.println("- Test 2 Passed");
			passed++;
		}
		else {
			System.out.println("- Test 2 Failed (Balance was \"" + balance + "\", balance should have been \"60\"");
		}
		
		allTransactions[0] = new int[] {2,1,1,1,1};
		balance = AuditableBanking.calculateCurrentBalance(allTransactions, allTransactionsCount);
		
		if (balance == 240) {
			System.out.println("- Test 3 Passed");
			passed++;
		}
		else {
			System.out.println("- Test 3 Failed (Balance was \"" + balance + "\", balance should have been \"240\"");
		}
		
		System.out.println("Passed " + passed + " out of " + tests + " tests");
		
		System.out.println();
		
		if (passed == tests) {
			return true;
		}
		return false;
	}
	
	public static boolean testCalculateNumberOfOverdrafts() {
		boolean foundProblem = false;
		int[][] transactions = new int[][] {
			{1,10,-20,+30,-20,-20}, // +2 overdrafts (ending balance:  -20)
			{0,1,1,1,0,0,1,1,1,1},  // +2 overdrafts (ending balance:  -15)
			{1,115},                // +0 overdrafts (ending balance: +100)
			{2,3,1,0,1},            // +1 overdrafts (ending balance: -100)
		};
		
		// test with a single transaction of the Integer Amount encoding
		int transactionCount = 1;    
		int overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions,transactionCount);
		if(overdrafts != 2) {
			System.out.println("FAILURE: calculateNumberOfOverdrafts did not return 2 when transactionCount = 1, and transactions contained: "+Arrays.deepToString(transactions));
			foundProblem = true;
		} else
			System.out.println("PASSED TEST 1/2 of TestCalculateNumberOfOverdrafts!!!");
	 
		// test with four transactions: including one of each encoding
		transactionCount = 4;
		overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions,transactionCount);
		if(overdrafts != 5) {
			System.out.println("FAILURE: calculateNumberOfOverdrafts did not return 5 when transactionCount = 4, and transactions contained: "+Arrays.deepToString(transactions));
			foundProblem = true;
		} else
			System.out.println("PASSED TESTS 2/2 of TestCalculateNumberOfOverdrafts!!!");
		
		return !foundProblem;
	}
}