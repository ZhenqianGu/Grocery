
package cs425;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CardList extends JPanel implements ItemListener, ActionListener, Runnable{

	public CardList() {

		super(new GridLayout(1, 0));

		JScrollPane scrollPane = new JScrollPane(CreditCard.cardTable);

		JFrame frame = new JFrame("Credit cards");
		frame.add(new JScrollPane(CreditCard.cardTable));
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
