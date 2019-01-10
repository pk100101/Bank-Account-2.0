/**
 * 
 * @author Pragnya Kousik
 *
 */
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
		boolean mainResponse = true;
		while (mainResponse)
		{
			System.out.println("\nHello, what would you like to do?"
				+ "\nYou can add an account, make a transaction, or terminate the program"
				+ "\nTo add an account, enter: Account"
				+ "\nTo make a transaction, enter: Transaction"
				+ "\nTo terminate the program, enter: Terminate");
			String response = s.nextLine();
			boolean accResponse = true;
			if ((response.toLowerCase()).equals("account"))
			{
				while (accResponse)
				{
					System.out.println("What kind of account would you like to add?"
							+ "\nYou can create a checking or a savings account");
					String accTypeResponse = s.next();
					s.nextLine();
					if ((accTypeResponse.toLowerCase()).equals("checking"))
						{
							System.out.println("Please provide a name");
							String name = s.nextLine();
							System.out.println("Name: " + name);
							System.out.println("Would you like to provide an initial balance?"
									+ "\nYes or No");
							String balResponse = s.nextLine();
							if ((balResponse.toLowerCase()).equals("yes"))
							{
								System.out.println("You have chosen to provide an initial balance"
										+ "\nPlease enter your initial balance");
								double balance = s.nextDouble();
								s.nextLine();
								System.out.println("Balance: " + balance);
								CheckingAccount account = new CheckingAccount (name, balance, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
								accounts.add(account);
								System.out.println(account.toString());
							}
							else if ((balResponse.toLowerCase()).equals("no"))
							{
								CheckingAccount account = new CheckingAccount (name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
								accounts.add(account);
								System.out.println(account.toString());
							}
							accResponse = false;
						}
						else if ((accTypeResponse.toLowerCase()).equals("savings"))
						{
							System.out.println("Please provide a name");
							String name = s.nextLine();
							System.out.println("Name: " + name);
							System.out.println("Would you like to provide an initial balance?"
									+ "\nYes or No");
							String balResponse = s.nextLine();
							if ((balResponse.toLowerCase()).equals("yes"))
							{
								System.out.println("You have chosen to provide an initial balance"
										+ "\nPlease enter your initial balance");
								double balance = s.nextDouble();
								s.nextLine();
								System.out.println("Balance: " + balance);
								SavingsAccount account = new SavingsAccount (name, balance, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
								accounts.add(account);
								System.out.println(account.toString());
							}
							else if ((balResponse.toLowerCase()).equals("no"))
							{
								SavingsAccount account = new SavingsAccount (name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
								accounts.add(account);
								System.out.println(account.toString());
							}
							accResponse = false;
							
						}
						else
						{	
							System.out.println("Invalid response, try again (press enter)");
							s.nextLine();
						}
				}
			}
			else if (response.equals("transaction"))
			{
				System.out.println("What kind of transaction would you like to make"
						+ "\nYou can withdraw, deposit, transfer, or get your account number"
						+ "\nTo withdraw, enter: withdraw"
						+ "\nTo deposit, enter: deposit"
						+ "\nTo transfer, enter: transfer"
						+ "\nTo get account number, enter: get acc num");
				String transactionResponse = s.nextLine();
				switch (transactionResponse)
				{
					case ("withdraw"):
					{
						boolean withdraw = true;
						while (withdraw)
						{					
							System.out.println("Please enter your account number");
							int accNum = s.nextInt();
							for (BankAccount acc: accounts) 
							{
								if (accNum == acc.getAccNum())
								{
									System.out.println("Please enter how much you would like to withdraw:");
									double withAmt = s.nextInt();
									s.nextLine();
									acc.withdraw(withAmt);
									System.out.println("Balance: " + acc.getBalance());
								}
								else
								{
									System.out.println("The account number that you have provided is incorrect."
											+ "\nYou can re-enter your account number or get your account number"
											+ "\nTo re-enter your account number: press enter"
											+ "\nTo get the account number enter: get num");
									if (s.nextLine().equals("get num"))
									{
										String accName= s.nextLine();
										for (BankAccount acc1 : accounts)
										{
											if (accName.equals(acc1.getName()))
											{
												System.out.println(acc1.toString());
												if (acc instanceof CheckingAccount)
													System.out.println("Checking account");
												else if (acc instanceof SavingsAccount)
													System.out.println("Savings account");
											}
											//is this correct or do i need to throw an exception
											else
												System.out.println("The name you have entered is not linked with an account");
										}
									}
								}
							}
							withdraw = false;
							break;
						}
					}
					case ("deposit"):
					{
						boolean deposit = true;
						while (deposit)
						{
							System.out.println("Please enter your account number");
							int accNum = s.nextInt();
							for (BankAccount accs: accounts) 
							{
								if (accNum == accs.getAccNum())
								{
									System.out.println("Please enter how much you would like to deposit:");
									//need to catch exception if user inputs invalid response
									double depAmt = s.nextInt();
									s.nextLine();
									accs.deposit(depAmt);
									System.out.println("Deposit successful"
											+ "\nBalance: " + accs.getBalance());
								}
								else
								{
									System.out.println("The account number that you have provided is incorrect."
											+ "\nYou can re-enter your account number or get your account number"
											+ "\nTo re-enter your account number: press enter"
											+ "\nTo get the account number enter: get num");
									s.nextLine();
								}
								continue;
							}
							deposit = false;
						}
						break;
					}
					
					case ("transfer"):
					{
						boolean transferResponse = true;
						while (transferResponse)
						{
							System.out.println("Which account would you like transfer from?");
							String transAccResponse = s.next();
							switch  (transAccResponse)
							{
								case ("savings"):
								{
									System.out.println("Please enter your savings account number");	
								}
								case ("checking"):
								{
									System.out.println("Please enter your checking account number");	
								}
								default:
									System.out.println("Invalid response, try again");
								break;
							}
						}
					}
					
					case ("get acc num"):
					{
						boolean getAccNum = true;
						while (getAccNum)
						{
							System.out.println("To get your account number, you will need to enter your name");
							String accName= s.nextLine();
							for (BankAccount acc : accounts)
							{
								if (accName.equals(acc.getName()))
								{
									System.out.println(acc.toString());
									if (acc instanceof CheckingAccount)
										System.out.println("Checking account");
									else if (acc instanceof SavingsAccount)
										System.out.println("Savings account");
								}
								//is this correct or do i need to throw an exception
								else
									System.out.println("The name you have entered is not linked with an account");
							}
							s.nextLine();
						getAccNum = false;
						break;
						}	
					}
					default:
					{	
						System.out.println("Invalid response, try again (press enter)");
						s.nextLine();
					}
				}
			}
			else if ((response.toLowerCase()).equals("terminate"))
				{
					System.out.println("Thank you for banking with us, we hope to see you soon");
					mainResponse = false;
				}
			else
				System.out.println("Invalid response, please try again");
		}
	}
}
