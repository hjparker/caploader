package org.compsys704;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CapLoader extends JFrame {
	private JPanel panel;
	
	public CapLoader() {
//		this.setPreferredSize(new Dimension(200, 300));
		panel = new Canvas();
		panel.setPreferredSize(new Dimension(360, 350));
		panel.setBackground(Color.WHITE);
		JButton enable = new JButton("enable");
		enable.addActionListener(new SignalClient(Ports.ENABLE));
		JButton request = new JButton("request");
		request.addActionListener(new SignalClient(Ports.REQUEST));
		JButton refill = new JButton("refill");
		refill.addActionListener(new SignalClient(Ports.REFILL));
		JPanel ss = new JPanel();
		ss.add(enable);
		ss.add(request);
		ss.add(refill);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		this.add(panel,c);
		c.gridx = 0;
		c.gridy = 1;
		this.add(ss,c);


		this.setTitle("Cap Loader");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		CapLoader cl = new CapLoader();
		cl.pack();
		cl.setVisible(true);
		
		SignalServer server = new SignalServer();
		new Thread(server).start();
		while(true){
			try {
				cl.repaint();
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}