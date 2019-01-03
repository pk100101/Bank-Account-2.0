
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
			withdraw(TRANSACTION_FEE);
	}
	public void withdraw (double amt)
	{
		if (getBalance()<=0)
			throw new IllegalArgumentException ("Error: Invalid Balance");
		else if (amt <= 0)
			throw new IllegalArgumentException ("Amount cannot be negative!");
		else  if (amt >= this.getBalance())
			throw new IllegalArgumentException ("Invalid amount");
		super.deposit(amt);
		numTransactions++;
		if (numTransactions >= FREE_TRANS)
			withdraw(TRANSACTION_FEE);		
	}
	public void transfer (double amt)
	{
		
	}
}
