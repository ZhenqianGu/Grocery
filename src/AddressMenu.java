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



public class AddressMenu extends JFrame {
	
	ButtonGroup option = new ButtonGroup();
	JPanel row1 = new JPanel();
	JLabel addressLabel = new JLabel("Address", JLabel.LEFT);

	JPanel row2 = new JPanel();
	JButton addButton = new JButton("     Add an address     ");
	
	JPanel row3 = new JPanel();
	JButton deleteButton = new JButton("    Delete an address    ");
	
	JPanel row4 = new JPanel();
	JButton modifyButton = new JButton("    Modify an address    ");
	
	public AddressMenu(int id) {
		
		setSize(320, 350);
		setResizable(false);
		GridLayout layout = new GridLayout(4, 6, 100, 10);
		setLayout(layout);

		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(addressLabel);
		add(row1);

		// put on row 2
		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row2.setLayout(layout2);
		row2.add(addButton);
		add(row2);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(deleteButton);
		add(row3);

		// put on row 4
		FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row4.setLayout(layout4);
		row4.add(modifyButton);
		add(row4);


		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAddress aa = new AddAddress(id);
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteAddress da= new DeleteAddress(id);
			}
		});
		
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean ready = false;
				ModifyAdd1 m1 = new ModifyAdd1(id);
				
			}
		});
		
		
		setVisible(true);
	}
	
}
