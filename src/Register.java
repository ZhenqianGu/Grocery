package cs425;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Register extends JFrame {
	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel fLabel = new JLabel("First  name", JLabel.LEFT);
	JTextField f = new JTextField("", 10);

	JPanel row2 = new JPanel();
	JLabel mLabel = new JLabel("Middle name", JLabel.LEFT);
	JTextField m = new JTextField("", 10);

	JPanel row3 = new JPanel();
	JLabel lLabel = new JLabel("Last  name", JLabel.LEFT);
	JTextField l = new JTextField("", 10);
	
	JPanel row4 = new JPanel();
	JButton ok = new JButton("ok");

	public Register () {
		super("Sign up");
		setSize(380, 280);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout layout = new GridLayout(4, 6, 100, 10);
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
		row2.add(mLabel);
		row2.add(m);
		m.setEditable(true);
		add(row2);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(lLabel);
		row3.add(l);
		l.setEditable(true);
		add(row3);

		// put on row 4
		FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row4.setLayout(layout4);
		row4.add(ok);
		add(row4);

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fName = f.getText();
				String mName = m.getText();
				String lName = l.getText();
				SetPassword sp = new SetPassword(fName, mName, lName);
				
				setVisible(false);
			}
		});
		
		setVisible(true);
	}
	
}
