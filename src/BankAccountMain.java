import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain 
{
	public static void main (String [] args)
	{
		final double OVER_DRAFT_FEE = 15;
		final double RATE = .0025;
		final double TRANSACTION_FEE = 1.5;
		final double MIN_BAL = 300;
		final double MIN_BAL_FEE = 10;
		final int FREE_TRANSACTIONS = 10;
		
		Scanner s = new Scanner(System.in);
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		boolean response1 = true;
		while (response1)
		{
			System.out.println("Hello, what would you like to do?"
				+ "\nYou can add an account, make a transaction, or terminate the program");
			String response = s.next();
			s.nextLine();
			if ((response.toLowerCase()).equals("add an account"))
			{
				
			}
			else if (response.equals("make a transaction"))
			{
				System.out.println("What kind of transaction would you like to make"
						+ "\nYou can withdraw, deposit, transfer, or get account numbers");
				if ((response.toLowerCase()).equals("withdraw"))
				{
					System.out.println("Please enter your account number");
					//not sure if the following line is correct
					s.nextInt();
				}
				else if ((response.toLowerCase()).equals("deposit"))
				{
					System.out.println("Please enter your account number");
					//finish up!
				}
				else if ((response.toLowerCase()).equals("transfer"))
				{
					System.out.println("Please enter your account number");
					//finish up!
				}
			}
			else if ((response.toLowerCase()).equals("terminate the program"))
				response1 = false;
			else
				System.out.println("Invalid response, please try again");
		}
	}
}
