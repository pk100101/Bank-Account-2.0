/**
 * 
 * @author Pragnya Kousik
 *
 */
import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain 
{
	/**
	 * 
	 * @param str - takes a string parameter
	 * @return checks if the string is a integer or not,  returns false if it is not an integer
	 */
	private static boolean isNumeric(String str)
	{
		try
		{
			Double.parseDouble(str);
			return true;
		}
		catch(IllegalArgumentException e)
		{
			return false;
		}
	}
	
	

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
								String balance = s.nextLine();
								while(!isNumeric(balance))
								{
									System.out.println("Invalid input, please input a numeric value");
									balance = s.nextLine();
								}
								CheckingAccount account = new CheckingAccount (name, Double.parseDouble(balance), OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
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
								String balance = s.nextLine();
								while(!isNumeric(balance))
								{
									System.out.println("Invalid input, please input a numeric value");
									balance = s.nextLine();
								}
								SavingsAccount account = new SavingsAccount (name, Double.parseDouble(balance), OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
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
						+ "\nTo retrieve accounts, enter: get acc num");
				String transactionResponse = s.nextLine();
				switch (transactionResponse)
				{
					
					case ("withdraw"):
					{
						boolean withdraw = true;
						while (withdraw)
						{					
							System.out.println("Please enter your account number");
							String accNum = s.nextLine();
							while(!isNumeric(accNum))
							{
								System.out.println("Invalid input, please input a numeric value");
								accNum = s.nextLine();
							}	
							for (BankAccount acc: accounts) 
							{
								if (Integer.parseInt(accNum) == acc.getAccNum())
								{
									System.out.println("Please enter how much you would like to withdraw:");
									String withAmt = s.nextLine();
									while(!isNumeric(withAmt))
									{
										System.out.println("Invalid input, please input a numeric value");
										withAmt = s.nextLine();
									}
									s.nextLine();
									try
									{
										acc.withdraw(Double.parseDouble(withAmt));
									}
									catch (IllegalArgumentException e)
									{
										System.out.println("Transaction not authorized!");
									}
									System.out.println("Withdraw successful"
											+ "\nBalance: " + acc.getBalance());
									withdraw = false;
								}
								else
								{
									System.out.println("The account number that you have provided is incorrect."
											+ "\nYou can re-enter your account number or get your account number"
											+ "\nTo re-enter your account number: press enter"
											+ "\nTo get the account number enter: get num");
									if (s.nextLine().equals("get num"))
									{
										boolean retrieveAccounts = true;
										while(retrieveAccounts)
										{
											int count = 0;
											System.out.println("Please enter your name");
											String accName= s.nextLine();
											for (BankAccount acc2 : accounts)
											{
												if (accName.equals(acc2.getName()))
												{
													System.out.println(acc2.toString());
													if (acc2 instanceof CheckingAccount)
														System.out.println("Checking account");
													else if (acc2 instanceof SavingsAccount)
														System.out.println("Savings account");
													count++;
													retrieveAccounts = false;
												}
												if(count == 0)
													System.out.println("The name you have entered is not linked with an account");
											}
										}
									}
								}
							}
						}
						break;
					}
					
					case ("deposit"):
					{
						boolean deposit = true;
						while (deposit)
						{
							System.out.println("Please enter your account number");
							String accNum = s.nextLine();
							while(!isNumeric(accNum))
							{
								System.out.println("Invalid input, please input a numeric value");
								accNum = s.nextLine();
							}
							for (BankAccount accs: accounts) 
							{
								if (Integer.parseInt(accNum) == accs.getAccNum())
								{
									System.out.println("Please enter how much you would like to deposit:");
									String depAmt = s.nextLine();
									while(!isNumeric(depAmt))
									{
										System.out.println("Invalid input, please input a numeric value");
										depAmt = s.nextLine();
									}
									try
									{
										accs.deposit(Double.parseDouble(depAmt));
									}
									catch (IllegalArgumentException e)
									{
										System.out.println("Transaction not authorized!");
									}
									System.out.println("Deposit successful"
											+ "\nBalance: " + accs.getBalance());
									deposit = false;
								}
								else
								{
									System.out.println("The account number that you have provided is incorrect."
											+ "\nYou can re-enter your account number or get your account number"
											+ "\nTo re-enter your account number: press enter"
											+ "\nTo get the account number enter: get num");
									if (s.nextLine().equals("get num"))
									{
										boolean retrieveAccounts = true;
										while(retrieveAccounts)
										{
											System.out.println("Please enter your name");
											String accName= s.nextLine();
											for (BankAccount acc2 : accounts)
											{
												if (accName.equals(acc2.getName()))
												{
													System.out.println(acc2.toString());
													if (acc2 instanceof CheckingAccount)
														System.out.println("Checking account");
													else if (acc2 instanceof SavingsAccount)
														System.out.println("Savings account");
													retrieveAccounts = false;
												}
												else
													System.out.println("The name you have entered is not linked with an account");
											}
										}
									}
								}
							}							
						}						
						break;
					}
					
					case ("transfer"):
					{
						boolean transfer = true;
						while (transfer)
						{					
							System.out.println("Please enter the account number that you would like transfer from");
							String accNum = s.nextLine();
							while(!isNumeric(accNum))
							{
								System.out.println("Invalid input, please input a numeric value");
								accNum = s.nextLine();
							}							
							for (BankAccount acc: accounts) 
							{									
								if (acc.getAccNum() == Integer.parseInt(accNum))
								{
									boolean transferResponse = true;
									while (transferResponse)
									{
										System.out.println("Please enter the account number that you would like to transfer to");
										String transAccNum = s.next();
										while(!isNumeric(transAccNum))
										{
											System.out.println("Invalid input, please input a numeric value");
											transAccNum = s.nextLine();
										}
										for(BankAccount acc1: accounts)
										{
											if (acc1.getAccNum() == Integer.parseInt(transAccNum))
											{
												System.out.println("Please enter how much you would like to transfer:");
												String transAmt = s.nextLine();
												while(!isNumeric(transAmt))
												{
													System.out.println("Invalid input, please input a numeric value");
													transAmt = s.nextLine();
												}
												s.nextLine();
												try
												{
													acc.transfer(acc1, Double.parseDouble(transAmt));
													System.out.println("Transfer successful"
															+ "\nBalance: $" + acc1.getBalance());
												}
												catch (IllegalArgumentException e)
												{
													System.out.println("Transaction not authorized!");
												}												
												transfer = false;
											}										
										}
									}
								}
								else
								{
									System.out.println("The account number that you have provided is incorrect."
											+ "\nYou can re-enter your account number or get your account number"
											+ "\nTo re-enter your account number: press enter"
											+ "\nTo retrieve all accounts enter: get num");
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
											else
												System.out.println("The name you have entered is not linked with an account");
										}
									}
								}
							}
							transfer = false;
							break;
						}
						
					}
					
					case ("get acc num"):
					{
						boolean retrieveAccounts = true;
						while(retrieveAccounts)
						{
							int count = 0;
							System.out.println("Please enter your name");
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
									count++;
									retrieveAccounts = false;
								}
							}
							if(count == 0)
								System.out.println("The name you have entered is not linked with an account");
						}
						break;
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
