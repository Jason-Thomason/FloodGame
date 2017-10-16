import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

	private JPanel topPanel;
	private JPanel bottomPanel;

	public Window(int windowWidth, int windowHeight, int topPanelHeight) {

		super("Flood Game");

		setupWindow(windowWidth, windowHeight);

		topPanel = setupPanel(windowWidth, topPanelHeight, Color.black);

		int bottomPanelHeight = windowHeight - topPanelHeight;
		bottomPanel = setupPanel(windowWidth, bottomPanelHeight, Color.gray);

		this.add(topPanel);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.validate();

		addEscKey();

	}

	private void setupWindow(int windowWidth, int windowHeight) {
		this.setSize(windowWidth, windowHeight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
	}

	private JPanel setupPanel(int panelWidth, int panelHeight,
			Color backgroundColor) {
		JPanel panel = new JPanel();
		panel.setBackground(backgroundColor);
		panel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		return panel;
	}

	private void addEscKey() {
		addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void addButtonsToBottomPanel(JButton[] buttons) {
		for (int i = 0; i < buttons.length; i++) {
			bottomPanel.add(buttons[i]);
			bottomPanel.validate();
		}
	}

	public void paintSquaresOntoTopPanel(Square[][] squares) {
		for (Square[] s : squares) {
			for (Square ss : s) {
				ss.paint(topPanel.getGraphics());
				topPanel.validate();
			}
		}

		// In Case paint must be run twice
		/*for (Square[] s : squares) {
			for (Square ss : s) {
				ss.paint(topPanel.getGraphics());
				topPanel.validate();
			}
		}*/

	}

	public void paintSquaresOntoTopPanel(ArrayList<Square> squares) {
		for (Square s : squares) {
			s.paint(topPanel.getGraphics());
			topPanel.validate();
		}
	}

}
