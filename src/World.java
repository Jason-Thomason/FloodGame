import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;

public class World {

	public static Random rand = new Random();

	public static int rows = 50, columns = 50;

	public static Window window;
	int windowWidth = 500;
	int windowHeight = 700;
	int topPanelHeight = 500;
	
	public static Color[] colors = { Color.cyan, Color.yellow, Color.green,
			Color.red };
	public static String[] colorNames = { "Cyan", "Yellow", "Green", "Red" };

	public static Square[][] squares = new Square[rows][columns];
	private double squareWidth = windowWidth / columns;
	private double squareHeight = topPanelHeight / rows;
	public static JButton[] buttons = new JButton[colors.length];

	public static ArrayList<Square> checkableSquares = new ArrayList<Square>();
	public static ArrayList<Square> ownedSquares = new ArrayList<Square>();
	public static ArrayList<Square> nonOwnedSquares = new ArrayList<Square>();
	

	public World() {
		window = new Window(windowWidth, windowHeight, topPanelHeight);
		
		/*window.addKeyListener(new KeyListener() {
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
		});*/
		
		newLevel();
	
	}

	public void newLevel() {
		initializeSquares();
		setupButtons(buttons);
		window.paintSquaresOntoTopPanel(squares);
	}

	private void initializeSquares() {
		for (int i = 0; i < squares.length; i++) {
			for (int t = 0; t < squares[i].length; t++) {
				int c = rand.nextInt(colors.length);
				squares[i][t] = new Square(squareWidth, squareHeight, squareWidth * i, squareHeight * t, colors[c]);
				nonOwnedSquares.add(squares[i][t]);
			}
		}
		obtainSquare(squares[0][0]);
	}
	
	private void obtainSquare(Square s) {
		nonOwnedSquares.remove(s);
		s.owned = true;
		ownedSquares.add(s);
		s.checkable = true;
		checkableSquares.add(s);
	}
	
	private void setupButtons(JButton[] buttons) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton((String) colorNames[i]);
			buttons[i].setPreferredSize(new Dimension(100, 100));
		}
		window.addButtonsToBottomPanel(buttons);
		setButtonActions();
		
	}
	
	private void setButtonActions() {
		for (final JButton b : buttons) {
			b.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					changeColor(colors[java.util.Arrays.asList(buttons)
							.indexOf(b)]);
				}
			});
		}
	}

	public void changeColor(Color c) {
		for (Square s : ownedSquares) {
			s.color = c;
		}
		window.paintSquaresOntoTopPanel(ownedSquares);
		getNewSquares();
		boolean disconnected = false;
		while (!disconnected) {
			for (int i = 0; i < checkableSquares.size(); i++) {
				Square s = checkableSquares.get(i);
				for (int t = 0; t < nonOwnedSquares.size(); t++) {
					Square sss = nonOwnedSquares.get(t);
					if (s.x == sss.x && Math.abs(s.y - sss.y) == s.height) {
						if (s.color == sss.color && !sss.owned) {
							sss.owned = true;
							sss.checkable = true;
							nonOwnedSquares.remove(sss);
							ownedSquares.add(sss);
							checkableSquares.add(sss);
							i--;
						}
					} else if (s.y == sss.y && Math.abs(s.x - sss.x) == s.width) {
						if (s.color == sss.color && !sss.owned) {
							sss.owned = true;
							sss.checkable = true;
							nonOwnedSquares.remove(sss);
							ownedSquares.add(sss);
							checkableSquares.add(sss);
							i--;
						}
					}
					if (i == checkableSquares.size() - 1
							&& t == nonOwnedSquares.size() - 1) {
						disconnected = true;
					}
				}

			}

		}
		// This will help so the program doesn't check squares that are
		// surrounded in owned squares
		for (int i = 0; i < squares.length; i++) {
			for (int t = 0; t < squares[i].length; t++) {
				Square s = squares[i][t];
				// Checks the squares of each side of s
				// Try loop for when there is no square to the side (Index out
				// of Bounds)
				try {
					if (ownedSquares.get(i - columns).owned
							&& ownedSquares.get(i + columns).owned
							&& ownedSquares.get(i - 1).owned
							&& ownedSquares.get(i + 1).owned && s.checkable) {
						s.checkable = false;
						checkableSquares.remove(s);
					} else if (!ownedSquares.get(i - columns).owned
							|| !ownedSquares.get(i + columns).owned
							|| !ownedSquares.get(i - 1).owned
							|| !ownedSquares.get(i + 1).owned) {
						s.checkable = true;
						if (!checkableSquares.contains(s)) {
							checkableSquares.add(s);
						}
					}
				} catch (Exception e) {

				}
			}
		}
	}
	
	private void getNewSquares() {
		for (Square s : checkableSquares) {
			for (Square ss : nonOwnedSquares) {
				if(squaresAreObtainableMatch(s, ss)) {
					obtainSquare(ss);
				}
			}
		}
	}
	
	private boolean squaresAreObtainableMatch(Square s, Square ss) {
		if(Math.abs(s.getColumn() - ss.getColumn()) == 1 &&  s.getRow() == ss.getRow()) {
			return true;
		}else if (Math.abs(s.getRow() - ss.getRow()) == 1 && s.getColumn() == ss.getColumn()){
			return true;
		}else {
			return false;
		}
}
	
}
