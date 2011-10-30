package br.ufrj.dcc.so.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.ufrj.dcc.so.Main;

public class MainFrame extends JFrame {
	
	private JPanel threadsPanel;
	private JPanel botoesPanel;
	private JPanel statusPanel;
	private JPanel mainPanel;
	
	public MainFrame() {
		super("Prog2");
	}
	
	public void init() {
		/* init threads */
		Main m = Main.instance();
		m.initThreads();
		
		/* init gui */
		mainPanel = new JPanel();
		threadsPanel = new ThreadsPanel();

		mainPanel.setOpaque(true);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		setContentPane(mainPanel);
		
		initBotoesPanel();
		initStatusPanel();
		
		add(threadsPanel);
		
		setSize(500, 500);
		
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initStatusPanel() {
		statusPanel = new JPanel();
		
		final JLabel numProduzidosLabel = new JLabel("0");
		final JLabel numConsumidosLabel = new JLabel("0");
		
		statusPanel.add(new JLabel("Num produzidos: "));
		statusPanel.add(numProduzidosLabel);
		statusPanel.add(new JLabel("Num consumidos: "));
		statusPanel.add(numConsumidosLabel);
		
		new Thread("Atualizador status") {
			public void run() {
				while (true) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							numProduzidosLabel.setText(""+Main.getNumProduzidos());
							numConsumidosLabel.setText(""+Main.getNumConsumidos());
						}
					});
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
			};
		}.start();
		
		add(statusPanel);
	}

	private void initBotoesPanel() {
		botoesPanel = new JPanel();
		
		final JButton execButton = new JButton("Iniciar");
		
		execButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.instance().exec();
				
				execButton.setEnabled(false);
			}
		});
		
		botoesPanel.add(execButton);
		
		add(botoesPanel);
	}
}
