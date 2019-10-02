package cs425;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;



public class CustomerMenu extends JFrame {
	
	ButtonGroup option = new ButtonGroup();
	JPanel row1 = new JPanel();
	JLabel customerLabel = new JLabel("Customer", JLabel.LEFT);

	JPanel row2 = new JPanel();
	JButton shoppingButton = new JButton("  Shopping  ");
	
	JPanel row3 = new JPanel();
	JButton balanceButton = new JButton("   Balance   ");
	
	JPanel row4 = new JPanel();
	JButton creditcardButton = new JButton("Credit  Card");
	
	JPanel row5 = new JPanel();
	JButton addressButton = new JButton("   Address   ");
	
	
	public CustomerMenu(int id) {
		
		setSize(380, 350);
		setResizable(false);
		GridLayout layout = new GridLayout(5, 6, 100, 10);
		setLayout(layout);

		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(customerLabel);
		add(row1);

		// put on row 2
		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row2.setLayout(layout2);
		row2.add(shoppingButton);
		add(row2);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(balanceButton);
		add(row3);

		// put on row 4
		FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row4.setLayout(layout4);
		row4.add(creditcardButton);
		add(row4);

		// put on row 5
		FlowLayout layout5 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row5.setLayout(layout5);
		row5.add(addressButton);
		add(row5);

		
		shoppingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShoppingMenu sm = new ShoppingMenu(id);
			}
		});
		
		balanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BalanceMenu bm = new BalanceMenu(id);
			}
		});
		
		creditcardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreditcardMenu cm = new CreditcardMenu(id);
			}
		});
		
		addressButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddressMenu am = new AddressMenu(id);
			}
		});
		
		setVisible(true);
	}
	
}
