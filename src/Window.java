import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Window extends JFrame{

	
	
	public Window(int windowWidth, int windowHeight){
		
		super("Flood Game");
		
		setupWindow(windowWidth, windowHeight);
		
		//window will consist of 2 stacked JPanels: top for game board, bottom for JButton inputs
		//200 was picked as bottom panel height for aesthetics
		JPanel topPanel = setupPanel(windowWidth, windowHeight-200, Color.black); 
		JPanel bottomPanel = setupPanel(windowWidth, 200, Color.gray);
		
		
		this.addPanel(topPanel);
		this.addPanel(bottomPanel);
								
	}
	
	private void setupWindow(int windowWidth, int windowHeight){
		this.setSize(windowWidth, windowHeight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	private JPanel setupPanel(int panelWidth, int panelHeight, Color backgroundColor){
		JPanel panel = new JPanel();
		panel.setBackground(backgroundColor);
		panel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		return panel;
	}
	
	private void addPanel(JPanel panel){
		this.add(panel);
		this.validate();
	}
	
}
