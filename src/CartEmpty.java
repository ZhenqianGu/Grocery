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

public class CartEmpty extends JFrame{

	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel addedLabel = new JLabel("Your cart is empty", JLabel.LEFT);

	JPanel row2 = new JPanel();
	JButton okButton = new JButton("OK");

	public CartEmpty() {
		super("Empty");
		setSize(400, 200);
		setResizable(false);

		GridLayout layout = new GridLayout(2, 1, 10, 5);
		setLayout(layout);
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 90, 20);
		row1.setLayout(layout1);
		row1.add(addedLabel);
		add(row1);

		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 90, 20);
		row2.setLayout(layout2);

		row2.add(okButton);
		add(row2);

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});
		setVisible(true);

	}
}
