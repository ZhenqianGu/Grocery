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

public class DeleteProduct extends JFrame{
	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel nameLabel = new JLabel("  Name  ", JLabel.LEFT);
	JTextField name = new JTextField("", 10);

	JPanel row2 = new JPanel();
	JLabel categoryLabel = new JLabel("Category", JLabel.LEFT);
	JTextField category = new JTextField("", 10);

	JPanel row3 = new JPanel();
	JButton delete = new JButton("Delete");

	public DeleteProduct() {
		super("Delete Product");
		setSize(380, 180);
		setResizable(false);
		GridLayout layout = new GridLayout(3, 6, 100, 10);
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
		row3.add(delete);
		add(row3);

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName = name.getText();
				String productCate = category.getText();
				Staff.delete(productName, productCate);
				
				setVisible(false);
			}
		});
		
		setVisible(true);
	}
	
}
