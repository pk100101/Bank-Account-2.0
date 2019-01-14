public class SavingsAccount extends BankAccount
{
	private double intRate;
	final double MIN_BAL;
	final double MIN_BAL_FEE;
	

	/**
	 * 
	 * @param n - account holder's name
	 * @param b - account balance
	 * @param r - interest rate
	 * @param mb - minimum balance for savings account
	 * @param mbf - minimum balance fee if transaction makes the balance lower than the
	 * minimum balance
	 */
	SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super (n, b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	/**
	 * 
	 * @param n - account holder's name
	 * @param r - interest rate
	 * @param mb - minimum balance for savings account
	 * @param mbf - minimum balance fee if transaction makes the balance lower than the
	 * minimum balance
	 */
	SavingsAccount(String n, double r, double mb, double mbf)
	{
		super (n);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	/**
	 * adds interest to account balance
	 */
	public void addInterest()
	{
		deposit(getBalance() * intRate);
	}
	/**
	 * adds interest at the end of the month
	 */
	public void endOfMonthUpdate()
	{
		addInterest();
	}
	/**
	 * overrides the withdraw method in the BankAccount class; prevents negative withdraw amount,
	 * negative balance, and transaction from occurring if balance is already under the minimum
	 * balance
	 */
	public void withdraw (double amt)
	{
		
		if (amt > getBalance())
			throw new IllegalArgumentException ("Balance can not go negative!");
		else if (amt <= 0)
			throw new IllegalArgumentException ("Cannot withdraw negative amount");
		else if (getBalance() < MIN_BAL)
			throw new IllegalArgumentException ("Transaction cannot occur");
		super.withdraw(amt);
		if ((getBalance()-amt) < MIN_BAL)
			super.withdraw (MIN_BAL_FEE);
	}
	/**
	 * overrides the deposit method in the BankAccount class; prevents negative deposit amount
	 */
	public void deposit (double amt)
	{
		if (amt<=0)
			throw new IllegalArgumentException ("Invalid deposit amount");
		super.deposit(amt);
	}
	/**
	 * overrides transfer method from the BankAccount class; prevents negative balance
	 */
	public void transfer (BankAccount other, double amt)
	{
		if (getName().equals(other.getName()))
			super.transfer(other, amt);
		else if (amt > getBalance())
		{
			throw new  IllegalArgumentException ("Balance cannot go negative");
		}
		else 
			throw new IllegalArgumentException ("Account names do not match!");
	}
}