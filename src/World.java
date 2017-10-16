import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


	public World() {
		window = new Window(windowWidth, windowHeight, topPanelHeight);

		newLevel();

	}

	public void newLevel() {
		initializeSquares();
		setupButtons(buttons);
		window.paintSquaresOntoTopPanel(squares);
	}

	private void initializeSquares() {
		createSquares();
		calculateNeighbors();

		obtainSquare(squares[0][0]);

	}

	private void createSquares() {
		for (int i = 0; i < squares.length; i++) {
			for (int t = 0; t < squares[i].length; t++) {
				int c = rand.nextInt(colors.length);
				squares[i][t] = new Square(squareWidth, squareHeight, i, t,
						colors[c]);
			}
		}
	}

	private void calculateNeighbors() {
		for (int i = 0; i < squares.length; i++) {
			for (int t = 0; t < squares[i].length; t++) {
				Square s = squares[i][t];
				try { // TODO fix error handling here

					s.addNeighbor(squares[i - 1][t]);

				} catch (Exception e) {
				}
				try {

					s.addNeighbor(squares[i + 1][t]);
				} catch (Exception e) {
				}
				try {
					s.addNeighbor(squares[i][t - 1]);

				} catch (Exception e) {
				}
				try {

					s.addNeighbor(squares[i][t + 1]);

				} catch (Exception e) {
					// Out of bounds exception
				}
			}
		}
	}

	private void obtainSquare(Square s) {
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
		removeRedundentCheckableSquares();
		
	}

	private void getNewSquares() {
		int startingCheckableSquaresSize = checkableSquares.size();
		for (int i = 0; i < startingCheckableSquaresSize; i++) {
			Square s = checkableSquares.get(i);
			for (int t = 0; t < s.neighbors.size(); t++) {
				Square ss = s.neighbors.get(t);
				if (squaresMatchColor(s, ss) && !ss.owned) {
					obtainSquare(ss);
				}
			}
		}
	}

	private boolean squaresMatchColor(Square s, Square ss) {
		return (s.color == ss.color);
	}

	private void removeRedundentCheckableSquares() {
		for (int i = 0; i < checkableSquares.size(); i++) {
			Square s = checkableSquares.get(i);
			if (allNeighborsOfSquareAreOwned(s)) {
				s.checkable = false;
				checkableSquares.remove(s);
				i--;
			}
		}
	}

	private boolean allNeighborsOfSquareAreOwned(Square s) {
		boolean allNeighborsAreOwned = true;
		for (Square ss : s.neighbors) {
			if (!ss.owned) {
				allNeighborsAreOwned = false;
			}
		}
		return allNeighborsAreOwned;
	}

}
