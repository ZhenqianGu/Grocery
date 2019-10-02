package cs425;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class UpdateAddress extends JFrame {
	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel accountLabel = new JLabel(" Set the new address as the shipping address? ", JLabel.LEFT);


	JPanel row4 = new JPanel();
	JButton yes = new JButton("Yes");
	JButton no = new JButton("no");

	public UpdateAddress (int id, int streetNum, String streetName, int aptNum, int zipCode,
			String state) {
		super("Update shipping address");
		setSize(380, 250);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout layout = new GridLayout(5, 6, 100, 10);
		setLayout(layout);

		// put on row 1
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(accountLabel);
		add(row1);

		// put on row 4
		FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row4.setLayout(layout4);
		row4.add(yes);
		row4.add(no);
		add(row4);
		
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Address.updateShippingAddress(id, streetNum, streetName, aptNum, zipCode, state);
				setVisible(false);
			}
		});
		
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		setVisible(true);
	}

	
}
