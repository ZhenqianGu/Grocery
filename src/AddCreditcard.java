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
import javax.swing.JTextField;
import javax.swing.UIManager;

public class AddCreditcard extends JFrame {
	ButtonGroup option = new ButtonGroup();

	JPanel row0 = new JPanel();
	JLabel cardNumLabel = new JLabel(" Card number ", JLabel.LEFT);
	JTextField cardNum = new JTextField("", 10);

	JPanel row1 = new JPanel();
	JLabel bankLabel = new JLabel("      Bank       ", JLabel.LEFT);
	JTextField bank = new JTextField("", 10);

	JPanel row2 = new JPanel();
	JLabel expirationLabel = new JLabel("  Expiration   ", JLabel.LEFT);
	JTextField expiration = new JTextField("", 10);

	JPanel row3 = new JPanel();
	JLabel streetNumLabel = new JLabel("Street number", JLabel.LEFT);
	JTextField streetNum = new JTextField("", 10);

	JPanel row4 = new JPanel();
	JLabel streetNameLabel = new JLabel("  Street name  ", JLabel.LEFT);
	JTextField streetName = new JTextField("", 10);

	JPanel row5 = new JPanel();
	JLabel aptLabel = new JLabel("   Apt number  ", JLabel.LEFT);
	JTextField apt = new JTextField("", 10);

	JPanel row6 = new JPanel();
	JLabel zipLabel = new JLabel("    Zip code     ", JLabel.LEFT);
	JTextField zip = new JTextField("", 10);

	JPanel row7 = new JPanel();
	JLabel cityLabel = new JLabel("       City        ", JLabel.LEFT);
	JTextField city = new JTextField("", 10);

	JPanel row8 = new JPanel();
	JLabel stateLabel = new JLabel("       State       ", JLabel.LEFT);
	JTextField state = new JTextField("", 10);

	JPanel row9 = new JPanel();
	JButton add = new JButton("Add");

	public AddCreditcard(int id) {
		super("Add a new card");
		setSize(480, 570);
		setResizable(false);
		GridLayout layout = new GridLayout(10, 6, 100, 10);
		setLayout(layout);

		// put on row 0
		FlowLayout layout0 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row0.setLayout(layout0);
		row0.add(cardNumLabel);
		row0.add(cardNum);
		cardNum.setEditable(true);
		add(row0);

		// put on row 1
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(bankLabel);
		row1.add(bank);
		bank.setEditable(true);
		add(row1);

		// put on row 2
		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row2.setLayout(layout2);
		row2.add(expirationLabel);
		row2.add(expiration);
		expiration.setEditable(true);
		add(row2);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(streetNumLabel);
		row3.add(streetNum);
		streetNum.setEditable(true);
		add(row3);

		// put on row 4
		FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row4.setLayout(layout4);
		row4.add(streetNameLabel);
		row4.add(streetName);
		streetName.setEditable(true);
		add(row4);

		// put on row 5
		FlowLayout layout5 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row5.setLayout(layout5);
		row5.add(aptLabel);
		row5.add(apt);
		apt.setEditable(true);
		add(row5);

		// put on row 6
		FlowLayout layout6 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row6.setLayout(layout6);
		row6.add(zipLabel);
		row6.add(zip);
		zip.setEditable(true);
		add(row6);

		// put on row 7
		FlowLayout layout7 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row7.setLayout(layout7);
		row7.add(cityLabel);
		row7.add(city);
		city.setEditable(true);
		add(row7);

		// put on row 8
		FlowLayout layout8 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row8.setLayout(layout8);
		row8.add(stateLabel);
		row8.add(state);
		state.setEditable(true);
		add(row8);

		// put on row 9
		FlowLayout layout9 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row9.setLayout(layout9);
		row9.add(add);
		add(row9);

		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long cardN = Long.parseLong(cardNum.getText());
				String bankName = bank.getText();
				int exp = Integer.parseInt(expiration.getText());
				int strNum = Integer.parseInt(streetNum.getText());
				String strName = streetName.getText();
				int aptNum = Integer.parseInt(apt.getText());
				int zipC = Integer.parseInt(zip.getText());
				String cityName = city.getText();
				String stateName = state.getText();

				if (CreditCard.add(id, cardN, bankName, exp, strNum, strName, aptNum, zipC, cityName,
						stateName) == true)
					new Message("Added", "Credit card added");
				setVisible(false);
			}
		});

		setVisible(true);
	}

}
