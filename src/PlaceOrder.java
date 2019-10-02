package cs425;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlaceOrder extends JFrame {

	public PlaceOrder(int id, String cardNums, int totalamt, String state) {
		super();
		setSize(380, 180);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout layout = new GridLayout(3, 6, 100, 10);
		setLayout(layout);

		ButtonGroup option = new ButtonGroup();

		JPanel row1 = new JPanel();
		JLabel cardnumsLabel = new JLabel(cardNums + '\n', JLabel.LEFT);

		JPanel row2 = new JPanel();
		JLabel cardLabel = new JLabel("Card", JLabel.LEFT);
		JTextField card = new JTextField("", 10);

		JPanel row3 = new JPanel();
		JButton placeOrder = new JButton("Place Order");

		// put on row 1
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(cardnumsLabel);
		add(row1);

		// put on row 2
		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row2.setLayout(layout2);
		row2.add(cardLabel);
		row2.add(card);
		card.setEditable(true);
		add(row2);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(placeOrder);
		add(row3);

		
		placeOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long cardForPay = Long.parseLong(card.getText());
				
				Order.placeOrderCont(id, cardForPay, totalamt, state);
				
				setVisible(false);
			}
		});
		
		setVisible(true);
	}

}
