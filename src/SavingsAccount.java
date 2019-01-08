public class SavingsAccount extends BankAccount
{
	private double intRate;
	final double MIN_BAL;
	final double MIN_BAL_FEE;
	

	SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super (n, b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	SavingsAccount(String n, double r, double mb, double mbf)
	{
		super (n);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	public void addInterest()
	{
		deposit(getBalance() * intRate);
	}
	public void endOfMonthUpdate()
	{
		addInterest();
	}
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
	public void deposit (double amt)
	{
		if (amt<=0)
			throw new IllegalArgumentException ("Invalid deposit amount");
		super.deposit(amt);
	}
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