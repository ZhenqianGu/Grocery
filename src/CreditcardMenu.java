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



public class CreditcardMenu extends JFrame {
	
	ButtonGroup option = new ButtonGroup();
	JPanel row1 = new JPanel();
	JLabel creditcardLabel = new JLabel("Credit card", JLabel.LEFT);

	JPanel row2 = new JPanel();
	JButton addButton = new JButton("     Add a card     ");
	
	JPanel row3 = new JPanel();
	JButton deleteButton = new JButton("    Delete a card    ");
	
	JPanel row4 = new JPanel();
	JButton modifyButton = new JButton("    Modify a card    ");
	
	JPanel row5 = new JPanel();
	JButton browseButton = new JButton("Browse all the cards");
	
	
	public CreditcardMenu(int id) {
		
		setSize(380, 350);
		setResizable(false);
		GridLayout layout = new GridLayout(5, 6, 100, 10);
		setLayout(layout);

		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(creditcardLabel);
		add(row1);

		// put on row 2
		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row2.setLayout(layout2);
		row2.add(addButton);
		add(row2);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(deleteButton);
		add(row3);

		// put on row 4
		FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row4.setLayout(layout4);
		row4.add(modifyButton);
		add(row4);

		// put on row 5
		FlowLayout layout5 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row5.setLayout(layout5);
		row5.add(browseButton);
		add(row5);

		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCreditcard acc = new AddCreditcard(id);
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteCard dc = new DeleteCard(id);
			}
		});
		
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyCard mc = new ModifyCard(id);
			}
		});
		
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreditCard.browseCard(id);
			}
		});
		
		setVisible(true);
	}
	
	
}
