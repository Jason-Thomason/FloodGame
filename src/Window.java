import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Window extends JFrame{

	public static int width = 500, height = 700;
	public static JPanel jPanel1 = new JPanel();
	public static JPanel jPanel2 = new JPanel();
	
	public Window(){
		super("Flood Game");
		
		setupWindow();
		setupPanels();
		
	}
	
	private void setupWindow(){
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	private void setupPanels(){
		jPanel1.setBackground(Color.black);
		jPanel1.setPreferredSize(new Dimension(500, 500));
		this.add(jPanel1);
		this.validate();
		
		jPanel2.setBackground(Color.gray);
		jPanel2.setPreferredSize(new Dimension(500, 200));
		this.add(jPanel2, BorderLayout.SOUTH);
		this.validate();
	}
	
	
}
