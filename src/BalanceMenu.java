package cs425;

public class BalanceMenu {
	public BalanceMenu(int id) {
		int bal = Customer.getBalance(id);
		new Message("Current Balance", "The current balance is: " + bal);
	}
}
