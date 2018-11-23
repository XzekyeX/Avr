package net.teamfps.avr;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

// Jos Data Dir on Out niin ulos tulo state on joko 1 tai 0
// Jos Data Dir on In niin ulos tulo state on joko T = 0 tai P = 1
class State extends JPanel {
	private static final long serialVersionUID = 1L;
	protected final String name;
	protected int direction = 0; // Out tai In // DDR Out = 1; In = 0
	protected int state = 0; //
	protected JCheckBox dir, value, selected;

	protected ActionListener listener;
	protected ActionEvent event = new ActionEvent(this, 0, "Action");

	public State(String name) {
		this.name = name;
		this.setLayout(new GridLayout(3, 0));
		this.dir = new JCheckBox("In");
		this.value = new JCheckBox("T");
		this.selected = new JCheckBox(this.name);
		this.add(this.selected);
		this.add(this.dir);
		this.add(this.value);
		this.dir.addActionListener((e) -> action(e));
		this.value.addActionListener((e) -> action(e));
		this.selected.addActionListener((e) -> action(e));
	}

	private void action(ActionEvent e) {
		direction = dir.isSelected() ? 1 : 0;
		state = value.isSelected() ? 1 : 0;
		dir.setText(dir.isSelected() ? "Out" : "In");
		value.setText(dir.isSelected() ? value.isSelected() ? "1" : "0" : value.isSelected() ? "P" : "T");
		if (listener != null) listener.actionPerformed(event);
	}

	public boolean isSelected() {
		return this.selected.isSelected();
	}

	public void setListener(ActionListener listener) {
		this.listener = listener;
	}

	public int getState() {
		return this.state;
	}

	public int getDirection() {
		return this.direction;
	}

	public String getName() {
		return this.name;
	}
}