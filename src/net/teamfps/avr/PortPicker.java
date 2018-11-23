package net.teamfps.avr;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PortPicker extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PortPicker frame = new PortPicker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PortPicker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Avr Port Hex Picker!");
		setBounds(100, 100, 600, 260);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel cards = new JPanel(new CardLayout());
		cards.add(new MicroController("ATtiny84", new Port("A", 8), new Port("B", 4)), "ATtiny84");
		cards.add(new MicroController("ATtiny85", new Port("B", 6)), "ATtiny85");
		cards.add(new MicroController("ATtiny2313A", new Port("A", 3), new Port("B", 8), new Port("D", 7)), "ATtiny2313A");
		cards.add(new MicroController("ATmega328P", new Port("B", 8), new Port("C", 7), new Port("D", 8)), "ATmega328P");

		String[] tiny = { "ATtiny84", "ATtiny85", "ATtiny2313A", "ATmega328P" };
		JComboBox<String> cb = new JComboBox<String>(tiny);
		cb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.show(cards, (String) e.getItem());
			}
		});
		contentPane.add(cb, BorderLayout.PAGE_START);
		contentPane.add(cards, BorderLayout.CENTER);
	}

}
