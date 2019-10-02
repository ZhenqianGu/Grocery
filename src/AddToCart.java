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

public class AddToCart extends JFrame {
	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel nameLabel = new JLabel("  Name  ", JLabel.LEFT);
	JTextField name = new JTextField("", 10);

	JPanel row2 = new JPanel();
	JLabel categoryLabel = new JLabel("Category", JLabel.LEFT);
	JTextField category = new JTextField("", 10);

	JPanel row3 = new JPanel();
	JLabel quantityLabel = new JLabel("Quantity", JLabel.LEFT);
	JTextField quantity = new JTextField("", 10);
	
	JPanel row4 = new JPanel();
	JButton add = new JButton("Add");

	public AddToCart(int id) {
		super("Add to cart");
		setSize(380, 280);
		setResizable(false);
		GridLayout layout = new GridLayout(4, 6, 100, 10);
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

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(quantityLabel);
		row3.add(quantity);
		quantity.setEditable(true);
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
				int q = Integer.parseInt(quantity.getText());
				Order.addToCart(id, productName, productCate, q);
				setVisible(false);
			}
		});
		
		setVisible(true);
	}
	
}
