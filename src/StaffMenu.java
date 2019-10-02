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

public class StaffMenu extends JFrame {

	ButtonGroup option = new ButtonGroup();
	JPanel row1 = new JPanel();
	JLabel customerLabel = new JLabel("Staff", JLabel.LEFT);

	JPanel row2 = new JPanel();
	JButton browseButton = new JButton("  Browse product list  ");

	JPanel row3 = new JPanel();
	JButton searchButton = new JButton("      Search product    ");

	JPanel row4 = new JPanel();
	JButton addButton = new JButton("       Add product       ");

	JPanel row5 = new JPanel();
	JButton modifyButton = new JButton("    Modify a product    ");

	JPanel row6 = new JPanel();
	JButton deleteButton = new JButton("    Delete a product    ");
 
	JPanel row7 = new JPanel();
	JButton addWareButton = new JButton("   Add to warehouse   ");

	JPanel row8 = new JPanel();
	JButton priceButton = new JButton("          Set price          ");

	JPanel row9 = new JPanel();
	JButton cusButton = new JButton("       Customer list        ");

	public StaffMenu(int id) {

		setSize(380, 530);
		setResizable(false);
		GridLayout layout = new GridLayout(9, 5, 100, 10);
		setLayout(layout);

		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row1.setLayout(layout1);
		row1.add(customerLabel);
		add(row1);

		// put on row 2
		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row2.setLayout(layout2);
		row2.add(browseButton);
		add(row2);

		// put on row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row3.setLayout(layout3);
		row3.add(searchButton);
		add(row3);

		// put on row 4
		FlowLayout layout4 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row4.setLayout(layout4);
		row4.add(addButton);
		add(row4);

		// put on row 5
		FlowLayout layout5 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row5.setLayout(layout5);
		row5.add(modifyButton);
		add(row5);

		// put on row 6
		FlowLayout layout6 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row6.setLayout(layout6);
		row6.add(deleteButton);
		add(row6);

		// put on row 7
		FlowLayout layout7 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row7.setLayout(layout7);
		row7.add(addWareButton);
		add(row7);

		// put on row 8
		FlowLayout layout8 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row8.setLayout(layout8);
		row8.add(priceButton);
		add(row8);
		// put on row 9
		FlowLayout layout9 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row9.setLayout(layout9);
		row9.add(cusButton);
		add(row9);
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product.browse();
			}
		});

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchProduct sp = new SearchProduct();
			}
		});

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProduct ap = new AddProduct();
			}
		});

		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyProduct mp = new ModifyProduct();
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteProduct dp = new DeleteProduct();
			}
		});

		addWareButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToWarehouse aw = new AddToWarehouse();
			}
		});
		
		priceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetPrice sp = new SetPrice();
			}
		});
		
		cusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Staff.customerList();
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
		StaffMenu.setLookAndFeel();
		StaffMenu lo = new StaffMenu(1);
	}
}
