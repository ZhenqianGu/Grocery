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

public class SetDrink extends JFrame{
	ButtonGroup option = new ButtonGroup();

	JPanel row1 = new JPanel();
	JLabel alLabel = new JLabel("Alcohol content", JLabel.LEFT);
	JTextField al = new JTextField("", 10);


	JPanel row3 = new JPanel();
	JButton ok = new JButton("Ok");

	public SetDrink(String name) {
		super("Set alcohol content");
		setSize(380, 180);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout layout = new GridLayout(3, 6, 100, 10);
		setLayout(layout);

		// put on row 1
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(alLabel);
		row1.add(al);
		al.setEditable(true);
		add(row1);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(ok);
		add(row3);

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int alc = Integer.parseInt(al.getText());
				Product.setDrink(name, alc);
				setVisible(false);
			}
		});
		
		setVisible(true);
	}
	
}
