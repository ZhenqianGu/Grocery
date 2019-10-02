package cs425;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ProductList extends JPanel implements ItemListener, ActionListener, Runnable {

	public ProductList() {

		super(new GridLayout(1, 0));

		JScrollPane scrollPane = new JScrollPane(Product.productTable);

		JFrame frame = new JFrame("Available product");
		frame.add(new JScrollPane(Product.productTable));
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
