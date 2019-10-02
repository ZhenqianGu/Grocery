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

public class AddAddress extends JFrame {
	ButtonGroup option = new ButtonGroup();

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

	public AddAddress(int id) {
		super("Add a new address");
		setSize(480, 370);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout layout = new GridLayout(7, 2, 100, 10);
		setLayout(layout);

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
				int strNum = Integer.parseInt(streetNum.getText());
				String strName = streetName.getText();
				int aptNum = Integer.parseInt(apt.getText());
				int zipC = Integer.parseInt(zip.getText());
				String cityName = city.getText();
				String stateName = state.getText();

				Address.add(id, strNum, strName, aptNum, zipC, cityName, stateName);
				
				new Message("Added", "Address added");
				setVisible(false);
			}
		});

		setVisible(true);
	}
	
}
