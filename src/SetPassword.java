
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

public class SetPassword extends JFrame {
	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel fLabel = new JLabel("First  input", JLabel.LEFT);
	JTextField f = new JTextField("", 10);

	JPanel row2 = new JPanel();
	JLabel sLabel = new JLabel("Second input", JLabel.LEFT);
	JTextField s = new JTextField("", 10);

	JPanel row3 = new JPanel();
	JButton ok = new JButton("Sign up");

	public SetPassword(String firstName, String middleName, String lastName) {
		super("Set Password ");
		setSize(380, 180);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout layout = new GridLayout(3, 6, 100, 10);
		setLayout(layout);

		// put on row 1
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(fLabel);
		row1.add(f);
		f.setEditable(true);
		add(row1);

		// put on row 2
		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row2.setLayout(layout2);
		row2.add(sLabel);
		row2.add(s);
		s.setEditable(true);
		add(row2);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(ok);
		add(row3);

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String first = f.getText();
				String second = s.getText();

				if (!first.equals(second)) {
					new Message("Error", "2 inputs are different. ");
				} else {
					
					Customer.register(firstName, middleName, lastName, first);
					setVisible(false);
				}
			}
		});

		setVisible(true);
	}

}
