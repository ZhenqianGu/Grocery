package cs425;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SearchList extends JPanel implements ItemListener, ActionListener, Runnable{

	public SearchList() {

		super(new GridLayout(1, 0));

		JScrollPane scrollPane = new JScrollPane(Product.searchTable);

		JFrame frame = new JFrame("Matched product");
		frame.add(new JScrollPane(Product.searchTable));
		frame.pack();
		frame.setVisible(true);
		frame.setSize(450, 500);

	}

	@Override
	public void run() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
	}
}
