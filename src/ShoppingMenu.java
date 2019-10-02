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

public class ShoppingMenu extends JFrame {

	ButtonGroup option = new ButtonGroup();
	JPanel row1 = new JPanel();
	JLabel customerLabel = new JLabel("Customer - shopping", JLabel.LEFT);

	JPanel row2 = new JPanel();
	JButton browseButton = new JButton("  Browse product list  ");

	JPanel row3 = new JPanel();
	JButton searchButton = new JButton("     Search product   ");

	JPanel row4 = new JPanel();
	JButton addButton = new JButton("       Add to cart       ");

	JPanel row5 = new JPanel();
	JButton cartButton = new JButton("   What's in the cart   ");

	JPanel row6 = new JPanel();
	JButton deleteButton = new JButton("    Delete from cart    ");

	JPanel row7 = new JPanel();
	JButton placeButton = new JButton("       Place order       ");

	public ShoppingMenu(int id) {
		

		setSize(380, 430);
		setResizable(false);
		GridLayout layout = new GridLayout(8, 5, 100, 10);
		setLayout(layout);

		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(customerLabel);
		add(row1);

		// put on row 2
		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row2.setLayout(layout2);
		row2.add(browseButton);
		add(row2);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(searchButton);
		add(row3);

		// put on row 4
		FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row4.setLayout(layout4);
		row4.add(addButton);
		add(row4);

		// put on row 5
		FlowLayout layout5 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row5.setLayout(layout5);
		row5.add(cartButton);
		add(row5);

		// put on row 6
		FlowLayout layout6 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row6.setLayout(layout6);
		row6.add(deleteButton);
		add(row6);

		// put on row 7
		FlowLayout layout7 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row7.setLayout(layout7);
		row7.add(placeButton);
		add(row7);

		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product.browse();
			}
		});

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchProduct sp = new SearchProduct();
			}
		});

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToCart atc = new AddToCart(id);
			}
		});

		cartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order.getCart(id);
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteFromCart dfc = new DeleteFromCart(id);
			}
		});

		
		placeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order.placeOrder(id);
			}
		});


		setVisible(true);
	}
	
}
