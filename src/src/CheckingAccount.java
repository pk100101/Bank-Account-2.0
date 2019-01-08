public class CheckingAccount extends BankAccount
{
	//fields
	final double OVER_DRAFT_FEE;
	final double TRANSACTION_FEE;
	final double FREE_TRANS;
	private int numTransactions;
	
	//constructors
	public CheckingAccount (String n, double b, double odf, double tf, int freeTrans)
	{
		super (n, b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	public CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super (n);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	//methods
	public void endOfMonthUpdate()
	{
		numTransactions = 0;
	}
	public void deposit (double amt)
	{
		if (amt <= 0)
			throw new IllegalArgumentException ("Amount cannot be negative!");
		super.deposit(amt);
		numTransactions++;
		if (numTransactions >= FREE_TRANS)
			super.withdraw(TRANSACTION_FEE);
		
	}
	public void withdraw (double amt)
	{
		if (amt <= 0)
			throw new IllegalArgumentException ("Amount cannot be negative!");
		else if (getBalance() < 0)
			throw new IllegalArgumentException ("Your balance is negative, you cannot make the transaction");
		super.withdraw(amt);
		if (amt > getBalance())
			super.withdraw(OVER_DRAFT_FEE);
		numTransactions++;
		if (numTransactions > FREE_TRANS)
			super.withdraw(TRANSACTION_FEE);
	}
	public void transfer (BankAccount other, double amt)
	{
		if (amt > getBalance())
			throw new  IllegalArgumentException ("Balance cannot go negative");
		else if(!(getName().equals(other.getName())))
			throw new IllegalArgumentException ("Account names do not match!");
		else
			super.transfer(other, amt);
	
	}
}