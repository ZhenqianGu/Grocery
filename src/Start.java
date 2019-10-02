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



public class Start extends JFrame {

	JPanel row2 = new JPanel();
	JButton cusButton = new JButton("      Costomer      ");
	
	JPanel row3 = new JPanel();
	JButton staffButton = new JButton("          Staff          ");
	
	
	public Start() {
		super("Select");
		setSize(380, 150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout layout = new GridLayout(2, 2, 100, 10);
		setLayout(layout);

		// put on row 2
		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row2.setLayout(layout2);
		row2.add(cusButton);
		add(row2);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(staffButton);
		add(row3);
		
		cusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lo = new Login();
			}
		});
		
		staffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Slogin sl = new Slogin();
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
		Start.setLookAndFeel();
		Start s = new Start();
	}
}
