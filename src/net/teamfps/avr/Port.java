package net.teamfps.avr;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JTextField;

class Port extends JPanel {
	private static final long serialVersionUID = 1L;
	protected final String name;
	protected final int length;
	protected State[] states;
	protected JTextField ddr, port, hex;
	protected String portName;

	public Port(String name, int length) {
		this.name = name;
		this.length = length;
		this.states = new State[length];
		this.setLayout(new GridLayout(5, 0));
		this.portName = ("PORT" + name);
		// this.add(lblName);
		JPanel st = new JPanel();
		st.setLayout(new GridLayout(0, this.states.length));
		for (int i = 0; i < this.states.length; i++) {
			this.states[i] = new State(name + i);
			this.states[i].setListener((e) -> action(e));
			st.add(this.states[i]);
		}
		this.add(st);
		JPanel tf = new JPanel();
		tf.setLayout(new GridLayout(3, 0));
		this.ddr = new JTextField();
		this.port = new JTextField();
		this.hex = new JTextField();
		tf.add(ddr);
		tf.add(port);
		tf.add(hex);
		this.add(tf);
	}

	private void action(ActionEvent e) {
		String ddr = "DDR" + name + " = ";
		for (int i = 0; i < this.states.length; i++) {
			String dd = "(" + this.states[i].getDirection() + " << DD" + this.states[i].getName() + ")";
			ddr += i < this.states.length - 1 ? dd + " | " : dd + ";";
		}
		this.ddr.setText(ddr);

		String port = "PORT" + name + " = ";
		for (int i = 0; i < this.states.length; i++) {
			String dd = "(" + this.states[i].getState() + " << PORT" + this.states[i].getName() + ")";
			port += i < this.states.length - 1 ? dd + " | " : dd + ";";
		}
		this.port.setText(port);

		this.hex.setText("PORT" + name + " = 0x" + Hex() + ";");
	}

	public String Hex() {
		return Integer.toHexString(Bin());
	}

	public int Bin() {
		StringBuilder sb = new StringBuilder();
		for (int i = this.states.length - 1; i >= 0; i--) {
			sb.append(this.states[i].isSelected() ? "1" : "0");
		}
		return toInt(sb.toString());
	}

	public int toInt(String str) {
		try {
			int i = Integer.parseInt(str, 2);
			return i;
		} catch (NumberFormatException e) {
			System.err.println("NumberFormatException! Reason: " + str + " is not a integer!");
		}
		return 0;
	}

	public String getPortName() {
		return portName;
	}
}
