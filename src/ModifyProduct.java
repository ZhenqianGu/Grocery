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
import javax.swing.UIManager;

public class ModifyProduct extends JFrame{
	ButtonGroup option = new ButtonGroup();
	
	JPanel row = new JPanel();
	JLabel nameLabel = new JLabel("  Name  ", JLabel.LEFT);
	JTextField n = new JTextField("", 10);
	
	JPanel row0 = new JPanel();
	JLabel cateLabel = new JLabel("Category", JLabel.LEFT);
	JTextField cate = new JTextField("", 10);
	
	JPanel row1 = new JPanel();
	JLabel sizeLabel = new JLabel("New size", JLabel.LEFT);
	JTextField size = new JTextField("", 10);

	JPanel row3 = new JPanel();
	JButton modify = new JButton("Modify");

	public ModifyProduct() {
		super("Modify");
		setSize(380, 280);
		setResizable(false);
		GridLayout layout = new GridLayout(4, 6, 100, 10);
		setLayout(layout);

		
		FlowLayout layout6 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row.setLayout(layout6);
		row.add(nameLabel);
		row.add(n);
		size.setEditable(true);
		add(row);
		
		FlowLayout layout0 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row0.setLayout(layout0);
		row0.add(cateLabel);
		row0.add(cate);
		size.setEditable(true);
		add(row0);
		
		
		// put on row 1
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(sizeLabel);
		row1.add(size);
		size.setEditable(true);
		add(row1);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(modify);
		add(row3);

		modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newName = n.getText();
				String newCate = cate.getText();
				int newSize = Integer.parseInt(size.getText());
				Staff.modify(newName, newCate, newSize);
				setVisible(false);
			}
		});
		
		setVisible(true);
	}
	
}
