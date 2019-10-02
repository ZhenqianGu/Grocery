
package cs425;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddProduct extends JFrame {
	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel nameLabel = new JLabel("  Name  ", JLabel.LEFT);
	JTextField name = new JTextField("", 10);

	JPanel row2 = new JPanel();
	JLabel categoryLabel = new JLabel("Category", JLabel.LEFT);
	JTextField category = new JTextField("", 10);

	JPanel row4 = new JPanel();
	JLabel sizeLabel = new JLabel("    Size   ", JLabel.LEFT);
	JTextField size = new JTextField("", 10);

	JPanel row3 = new JPanel();
	JButton add = new JButton("Add");

	public AddProduct() {
		super("Add Product");
		setSize(380, 200);
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

		// put on row 4
		FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row4.setLayout(layout4);
		row4.add(sizeLabel);
		row4.add(size);
		category.setEditable(true);
		add(row4);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(add);
		add(row3);

		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName = name.getText();
				String productCate = category.getText();
				int productSize = Integer.parseInt(size.getText());
				Staff.addProduct(productName, productCate, productSize);
				new Message("Added", "New product added. ");
				setVisible(false);
			}
		});

		setVisible(true);
	}

}
