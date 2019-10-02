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

public class DeleteCard extends JFrame{
	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel cardLabel = new JLabel("Card number", JLabel.LEFT);
	JTextField card = new JTextField("", 10);


	JPanel row3 = new JPanel();
	JButton delete = new JButton("Delete");

	public DeleteCard(int id) {
		super("Delete card");
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
		row3.add(delete);
		add(row3);

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long cardNum = Long.parseLong(card.getText());
				CreditCard.delete(id, cardNum);
				setVisible(false);
			}
		});
		
		setVisible(true);
	}
	
}
