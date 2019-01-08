public abstract class BankAccount 
{
	//fields
	private String name;
	private static int nextNum = 1;
	private int accNum; 
	private double balance;
	
	//constructors
	public BankAccount (String n, double b)
	{
		name = n;
		accNum = nextNum;
		balance = b;
		nextNum++;
	}
	public BankAccount (String n)
	{
		name = n;
		accNum = nextNum;
		balance = 0;
		nextNum++;
	}
	
	//methods
	public void deposit (double amt)
	{
		balance += amt;
	}
	public void withdraw (double amt)
	{
		balance -= amt;
	}
	public void transfer (BankAccount other, double amt)
	{
		if (getName().equals(other.getName()))
		{
			withdraw(amt);
			other.deposit(amt);
		}
	}
	public String getName ()
	{
		return name;
	}
	public double getBalance()
	{
		return balance;
	}
	public int getAccNum()
	{
		return accNum;
	}
	public String toString()
	{
		return "Account number: " + accNum + "\tName: " + name + "\tBalance: " + balance;
	}
	public abstract void endOfMonthUpdate();
	
}