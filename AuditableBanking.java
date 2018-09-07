import java.util.Arrays;
import java.util.Scanner;

public class AuditableBanking {
	
	public static void main(String[] args) {
		// Initalization
		Scanner sc = new Scanner(System.in);
		int[][] allTransactions = new int[5][];
		int allTransactionsCount = 0;
		String command;
		boolean active = true;
		
		System.out.println("========== Welcome to the Auditable Banking App ==========");
		
		while (active) {
			System.out.println("COMMAND MENU:");
			System.out.println("  Submit a Transaction (enter sequence of integers separated by spaces)");
			System.out.println("  Show Current [B]alance");
			System.out.println("  Show Number of [O]verdrafts");
			System.out.println("  [Q]uit Program");
			System.out.print("ENTER COMMAND: ");
			command = sc.nextLine().trim().toUpperCase();
			
			if (command.contains("Q")) {
				active = false;
			}
			else {
				allTransactionsCount = processCommand(command, allTransactions, allTransactionsCount);
				System.out.println();
			}
		}
		System.out.println("============ Thank you for using this App!!!! ============");	
	}
	
	/**
	 * Adds a transaction group to an array of transaction groups. If the allTransactions array is
	 * already full then this method will do nothing other than return allTransactionCount.
	 * 
	 * @param newTransactions is the new transaction group being added (perfect size).
	 * @param allTransactions is the collection that newTransactions is being added to (oversize).
	 * @param allTransactionsCount is the number of transaction groups within allTransactions 
	 *        (before newTransactions is added.
	 * @return the number of transaction groups within allTransactions after newTransactions is added.
	 */
	public static int submitTransactions(int[] newTransactions, int[][] allTransactions, int allTransactionsCount) {
		
		if (allTransactionsCount < allTransactions.length) {
			allTransactions[allTransactionsCount] = newTransactions;
			allTransactionsCount++;
		} 	
		return allTransactionsCount;
	}
	
	public static int processCommand(String command, int[][] allTransactions, int allTransactionsCount) {
		
		if (command.charAt(0) == 'B') {
			System.out.println("Current Balance: " + calculateCurrentBalance(allTransactions, allTransactionsCount));
			return allTransactionsCount;
		}
		if (command.charAt(0) == 'O') {
			System.out.println("Number of Overdrafts: " + calculateNumberOfOverdrafts(allTransactions, allTransactionsCount));
			return allTransactionsCount;
		}
		
		String[] numsAsString = numsAsString = command.split(" ");		
		
		// Create new nums array to add to transactions
		int[] nums = new int[numsAsString.length];
		
		for (int i = 0 ; i < numsAsString.length ; i++) {
			nums[i] = Integer.parseInt(numsAsString[i]);
		}
		
		if (nums[0] == 0 || nums[0] == 1 || nums[0] == 2) {
			allTransactionsCount = submitTransactions(nums, allTransactions, allTransactionsCount);
		}
		
		return allTransactionsCount;
	}
	
	public static int calculateCurrentBalance(int[][] allTransactions, int allTransactionsCount) {
		
		int balance = 0;
		
		for (int i = 0 ; i < allTransactionsCount ; i++) {
			for (int j = 1 ; j < allTransactions[i].length ; j++) {
			
				// Binary Amount Transcations (DONE)
				if (allTransactions[i][0] == 0) {
					if (allTransactions[i][j] == 1) {
						balance++;
					}
					else if (allTransactions[i][j] == 0) {
						balance--;
					}
					}
				//________________________________________________
				
				
				// Integer Amount Transcations (DONE)
				else if (allTransactions[i][0] == 1) {
					balance += allTransactions[i][j];
				}
				//________________________________________________
				
				
				// Quick Withdraw Transcations
				
				else if (allTransactions[i][0] == 2) {
					if (j == 1) {
						balance -= allTransactions[i][j] * 20;
					}
					if (j == 2) {
						balance -= allTransactions[i][j] * 40;
					}
					if (j == 3) {
						balance -= allTransactions[i][j] * 80;
					}
					if (j == 4) {
						balance -= allTransactions[i][j] * 100;
					}
				}
				
				//________________________________________________
			}
		}
		return balance;
	}
	
	public static int calculateNumberOfOverdrafts(int[][] allTransactions, int allTransactionsCount) {
		int balance = 0;
		int numOverdrafts = 0;
		
		for (int i = 0 ; i < allTransactionsCount ; i++) {
			for (int j = 1 ; j < allTransactions[i].length ; j++) {
			
				// Binary Amount Transcations
				if (allTransactions[i][0] == 0) {
					if (allTransactions[i][j] == 1) {
						balance++;
					}
					else if (allTransactions[i][j] == 0) {
						balance--;
						if (balance < 0) {
							numOverdrafts++;
						}
					}
					}
				//________________________________________________
				
				
				// Integer Amount Transcations
				else if (allTransactions[i][0] == 1) {
					balance += allTransactions[i][j];
					if (balance < 0) {
						numOverdrafts++;
					}
				}
				//________________________________________________
				
				
				// Quick Withdraw Transcations
				
				else if (allTransactions[i][0] == 2) {
					if (j == 1) {
						balance -= allTransactions[i][j] * 20;
					}
					if (j == 2) {
						balance -= allTransactions[i][j] * 40;
					}
					if (j == 3) {
						balance -= allTransactions[i][j] * 80;
					}
					if (j == 4) {
						balance -= allTransactions[i][j] * 100;
					}
					if (balance < 0) {
						numOverdrafts++;
					}
				}
				
				//________________________________________________
			}
		}
		return numOverdrafts;
	}
}