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

public class Login extends JFrame {
	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel accountLabel = new JLabel(" Account ", JLabel.LEFT);
	JTextField account = new JTextField("", 10);

	JPanel row2 = new JPanel();
	JLabel passwordLabel = new JLabel("Password", JLabel.LEFT);
	JTextField password = new JTextField("", 10);

	JPanel row4 = new JPanel();
	JButton login = new JButton("Login");
	JButton register = new JButton("Sign up");

	public Login() {
		super("Customer Login");
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
		row4.add(register);
		add(row4);

		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int accountNum = Integer.parseInt(account.getText());
				String pass = password.getText();

				if (pass.equals(Customer.getPassword(accountNum))) {
					CustomerMenu cm = new CustomerMenu(accountNum);
				} else {
					new Message("Error", "Account number and password not match. ");
					setVisible(true);
				}

				setVisible(false);
			}
		});
		
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register re = new Register();

				setVisible(false);
			}
		});
		
		

		setVisible(true);
	}

	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception exc) {
			// ignore error
		}
	}

	public static void main(String[] arguments) {
		Login.setLookAndFeel();
		Login lo = new Login();
	}
}
