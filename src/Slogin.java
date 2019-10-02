package cs425;

import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Slogin extends JFrame {
	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel accountLabel = new JLabel(" Account ", JLabel.LEFT);
	JTextField account = new JTextField("", 10);

	JPanel row2 = new JPanel();
	JLabel passwordLabel = new JLabel("Password", JLabel.LEFT);
	JTextField password = new JTextField("", 10);

	JPanel row4 = new JPanel();
	JButton login = new JButton("Login");

	public Slogin() {
		super("Staff Login");
		setSize(380, 250);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout layout = new GridLayout(3, 6, 100, 10);
		setLayout(layout);

		// put on row 1
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(accountLabel);
		row1.add(account);
		account.setEditable(true);
		add(row1);

		// put on row 2
		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row2.setLayout(layout2);
		row2.add(passwordLabel);
		row2.add(password);
		password.setEditable(true);
		add(row2);

		// put on row 4
		FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row4.setLayout(layout4);
		row4.add(login);
		add(row4);

		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int accountNum = Integer.parseInt(account.getText());
				String pass = password.getText();

				if (pass.equals(Staff.password(accountNum))) {
					int a = Integer.parseInt(account.getText());
					StaffMenu sm = new StaffMenu(a);
				} else {
					new Message("Error", "Account number and password not match. ");
					setVisible(true);
				}

				setVisible(false);
			}
		});

		setVisible(true);
	}

	
}
