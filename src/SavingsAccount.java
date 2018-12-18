
public class SavingsAccount extends BankAccount
{
	private double intRate;
	final double MIN_BAL;
	final double MIN_BAL_FEE;
	private int numTransactions;

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
		numTransactions = 0;
	}
	public void withdraw (double amt)
	{
		if (amt >= getBalance())
			throw new IllegalArgumentException ("Balance can not go negative!");
		else if (getBalance()<=MIN_BAL)
			throw new IllegalArgumentException ("Transaction cannot occur");
		super.withdraw(amt);
		if ((getBalance()-amt) >= MIN_BAL)
			withdraw (MIN_BAL_FEE);
		numTransactions++;
	}
	public void transfer (BankAccount other, double amt)
	{
		super.transfer(other, amt);
		numTransactions++;
	}
}