
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

public class DeleteAddress extends JFrame {
	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel streetNumLabel = new JLabel("    Street number    ", JLabel.LEFT);
	JTextField streetNum = new JTextField("", 10);

	JPanel row2 = new JPanel();
	JLabel streetNameLabel = new JLabel("     Street name      ", JLabel.LEFT);
	JTextField streetName = new JTextField("", 10);

	JPanel row3 = new JPanel();
	JLabel aptLabel = new JLabel("Apartment number", JLabel.LEFT);
	JTextField apt = new JTextField("", 10);

	JPanel row4 = new JPanel();
	JLabel zipLabel = new JLabel("        Zipcode        ", JLabel.LEFT);
	JTextField zip = new JTextField("", 10);

	JPanel row5 = new JPanel();
	JButton delete = new JButton("Delete");

	public DeleteAddress(int id) {
		super("Delete address");
		setSize(380, 350);
		setResizable(false);
		GridLayout layout = new GridLayout(5, 6, 100, 10);
		setLayout(layout);

		// put on row 1
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(streetNumLabel);
		row1.add(streetNum);
		streetNum.setEditable(true);
		add(row1);

		// put on row 2
		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row2.setLayout(layout2);
		row2.add(streetNameLabel);
		row2.add(streetName);
		streetName.setEditable(true);
		add(row2);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(aptLabel);
		row3.add(apt);
		apt.setEditable(true);
		add(row3);

		// put on row 4
		FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row4.setLayout(layout4);
		row4.add(zipLabel);
		row4.add(zip);
		zip.setEditable(true);
		add(row4);

		// put on row 5
		FlowLayout layout5 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row5.setLayout(layout3);
		row5.add(delete);
		add(row5);

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int curStreetNum = Integer.parseInt(streetNum.getText());
				String curStreetName = streetName.getText();
				int curAptNum = Integer.parseInt(apt.getText());
				int curZipCode = Integer.parseInt(zip.getText());
				if (Address.delete(id, curStreetNum, curStreetName, curAptNum, curZipCode) == true)
					new Message("Deleted", "This address is deleted. ");

				setVisible(false);

			}
		});

		setVisible(true);
	}

}
