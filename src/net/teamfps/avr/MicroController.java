package net.teamfps.avr;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MicroController extends JPanel {
	private static final long serialVersionUID = 1L;
	protected String name;
	protected List<Port> ports = new ArrayList<Port>();
	protected JComboBox<String> cb;

	public MicroController(String name, Port... port) {
		this.name = name;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new JLabel(this.name));
		JPanel cards = new JPanel(new CardLayout());
		for (Port p : port) {
			this.ports.add(p);
			cards.add(p, p.getPortName());
		}

		cb = new JComboBox<String>(toStr(ports));
		cb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.show(cards, (String) e.getItem());
			}
		});
		this.add(cb);
		this.add(cards);
	}

	private String[] toStr(List<Port> ports) {
		String[] result = new String[ports.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = ports.get(i).getPortName();
		}
		return result;
	}
}
