package cs425;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CustomerList extends JPanel implements ItemListener, ActionListener, Runnable {

	public CustomerList() {

		super(new GridLayout(1, 0));

		JScrollPane scrollPane = new JScrollPane(Staff.customerTable);

		JFrame frame = new JFrame("Customers");
		frame.add(new JScrollPane(Staff.customerTable));
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
