/**
 * 
 * @author Pragnya Kousik
 *
 */
public abstract class BankAccount 
{
	//fields
	private String name;
	private static int nextNum = 1;
	private int accNum; 
	private double balance;
	
	//constructors
	/**
	 * 
	 * @param n - bank account holder's name
	 * @param b - balance in bank account
	 */
	public BankAccount (String n, double b)
	{
		name = n;
		accNum = nextNum;
		balance = b;
		nextNum++;
	}
	/**
	 * 
	 * @param n - bank account holder's name
	 */
	public BankAccount (String n)
	{
		name = n;
		accNum = nextNum;
		balance = 0;
		nextNum++;
	}
	
	//methods
	/**
	 * 
	 * @param amt - the amount to be deposited
	 * this method adds the amount to be deposited to the balance
	 */
	public void deposit (double amt)
	{
		balance += amt;
	}
	/**
	 * 
	 * @param amt  -  the amount to be withdrawn
	 * this method removes the amount to be withdrawn from the balance
	 */
	public void withdraw (double amt)
	{
		balance -= amt;
	}
	/**
	 * 
	 * @param other - the account that the money is transferred to
	 * @param amt - transfer amount
	 * removes amount from one account and deposits to the other account
	 */
	public void transfer (BankAccount other, double amt)
	{
		if (getName().equals(other.getName()))
		{
			withdraw(amt);
			other.deposit(amt);
		}
	}
	/**
	 * 
	 * @return - returns the name of the account holder
	 */
	public String getName ()
	{
		return name;
	}
	/**
	 * 
	 * @return - returns the balance in the account
	 */
	public double getBalance()
	{
		return balance;
	}
	/**
	 * 
	 * @return - returns the account number
	 */
	public int getAccNum()
	{
		return accNum;
	}
	public String toString()
	{
		return "Account number: " + accNum + "\tName: " + name + "\tBalance: $" + balance;
	}
	public abstract void endOfMonthUpdate();
	
}

