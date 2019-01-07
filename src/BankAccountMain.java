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
			String response = s.nextLine();
			if ((response.toLowerCase()).equals("add an account"))
			{
				System.out.println("What kind of account would you like to add?"
						+ "\nYou can create a checking or a savings account");
				response = s.nextLine();	
				if ((response.toLowerCase()).equals("checking"))
					{
						System.out.println("Please provide a name");
						String name = s.nextLine();
						System.out.println("Name: " + name);
						System.out.println("Would you like to provide an initial balance?"
								+ "\nYes or No");
						response = s.nextLine();
						if ((response.toLowerCase()).equals("yes"))
						{
							System.out.println("You have chosen to provide an initial balance"
									+ "\nPlease enter your initial balance");
							double balance = s.nextDouble();
							s.nextLine();
							System.out.println("Balance: " + balance);
							accounts.add(new CheckingAccount (name, balance, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
							accounts.get(0).toString();
						}
						else if ((response.toLowerCase()).equals("no"))
						{
							accounts.add(new CheckingAccount (name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
							accounts.get(0).toString();
						}
					}
					else if ((response.toLowerCase()).equals("savings"))
					{
						System.out.println("Please provide a name");
						String name = s.nextLine();
						System.out.println("Name: " + name);
						System.out.println("Would you like to provide an initial balance?"
								+ "\nYes or No");
						s.nextLine();
						if ((response.toLowerCase()).equals("yes"))
						{
							System.out.println("You have chosen to provide an initial balance"
									+ "\nPlease enter your initial balance");
							s.nextLine();
							double balance = s.nextDouble();
							accounts.add(new SavingsAccount (name, balance, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
						}
						else if ((response.toLowerCase()).equals("no"))
						{
							accounts.add(new SavingsAccount (name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
							accounts.toString();
						}
					}
					else
					{	
						System.out.println("Invalid response, try again");
						s.nextLine();
					}
			}
			else if (response.equals("make a transaction"))
			{
				System.out.println("What kind of transaction would you like to make"
						+ "\nYou can withdraw, deposit, transfer, or get account numbers");
				s.nextLine();
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
					s.nextInt();
				}
				else if ((response.toLowerCase()).equals("transfer"))
				{
					System.out.println("Please enter your account number");
					//finish up!
				}
			}
			else if ((response.toLowerCase()).equals("terminate the program"))
				{
					System.out.println("Thank you for banking with us, we hope to see you soon");
					response1 = false;
				}
			else
				System.out.println("Invalid response, please try again");
		}
	}
}