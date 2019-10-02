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

public class ModifyCard extends JFrame {
	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel cardLabel = new JLabel("  Card number  ", JLabel.LEFT);
	JTextField card = new JTextField("", 10);

	JPanel row3 = new JPanel();
	JButton modify = new JButton("Enter");

	public ModifyCard(int id) {
		super("Modify card");
		setSize(380, 180);
		setResizable(false);
		GridLayout layout = new GridLayout(3, 6, 100, 10);
		setLayout(layout);

		// put on row 1
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(cardLabel);
		row1.add(card);
		card.setEditable(true);
		add(row1);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(modify);
		add(row3);

		modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreditCard.oldNum = Long.parseLong(card.getText());
				CreditCard.modify(id, CreditCard.oldNum);
				setVisible(false);
			}
		});

		
		setVisible(true);
	}

}
