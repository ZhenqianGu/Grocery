package cs425;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class SetPrice extends JFrame {
	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel nameLabel = new JLabel("    Name   ", JLabel.LEFT);
	JTextField name = new JTextField("", 10);

	JPanel row2 = new JPanel();
	JLabel categoryLabel = new JLabel("  Category ", JLabel.LEFT);
	JTextField category = new JTextField("", 10);
	
	JPanel row5 = new JPanel();
	JLabel stateLabel = new JLabel("    State    ", JLabel.LEFT);
	JTextField state = new JTextField("", 10);

	JPanel row3 = new JPanel();
	JLabel priceLabel = new JLabel("    Price    ", JLabel.LEFT);
	JTextField price = new JTextField("", 10);

	JPanel row4 = new JPanel();
	JButton add = new JButton("Add");

	public SetPrice() {
		super("Set price");
		setSize(380, 330);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout layout = new GridLayout(5, 6, 100, 10);
		setLayout(layout);

		// put on row 1
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(nameLabel);
		row1.add(name);
		name.setEditable(true);
		add(row1);

		// put on row 2
		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row2.setLayout(layout2);
		row2.add(categoryLabel);
		row2.add(category);
		category.setEditable(true);
		add(row2);

		// put on row 5
		FlowLayout layout5 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row5.setLayout(layout5);
		row5.add(stateLabel);
		row5.add(state);
		state.setEditable(true);
		add(row5);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(priceLabel);
		row3.add(price);
		price.setEditable(true);
		add(row3);

		// put on row 4
		FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row4.setLayout(layout4);
		row4.add(add);
		add(row4);

		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName = name.getText();
				String productCate = category.getText();
				String newState = state.getText();
				int p = Integer.parseInt(price.getText());

				Staff.setPrice(productName, productCate, newState, p);
				setVisible(false);
			}
		});

		setVisible(true);
	}
	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception exc) {
			// ignore error
		}
	}

	public static void main(String[] arguments) {
		SetPrice.setLookAndFeel();
		SetPrice lo = new SetPrice();
	}
}
