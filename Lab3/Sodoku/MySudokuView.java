import java.beans.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class MySudokuView extends JPanel implements PropertyChangeListener{
	private SudokuModel sud;
	private SudokuController control;
	private SudokuGrid grid;

	
	//Constructor class
	public MySudokuView(SudokuModel s, SudokuController ctrl){
		sud = s;
		control = ctrl;
		sud.addPropertyChangeListener(this);
		grid = new SudokuGrid(sud);
		add(grid.get());
	}
	
	// Listerner class
	public void propertyChange(PropertyChangeEvent c) {
		removeAll();
		sud = (SudokuModel)c.getNewValue();
		grid = new SudokuGrid(sud);
		add(grid.get());
		revalidate();
		repaint();
	}
	


	//The basic sudoku square.
	public class Square extends JTextField implements KeyListener  {
		int row;
		int col;
		Square(String value, int n, int r, int c) {
			super(value, n);
			row = r;
			col = c;
			addKeyListener(this);
		}

		public int row() {
			return row;
		}

		public int col() {
			return col;
		}
		
		public String toString() {
			return "" + row + col;
		}
		
		public void keyReleased(KeyEvent e) {
			char k = e.getKeyChar();
			if (!control.input(row, col, k))
				Toolkit.getDefaultToolkit().beep(); 
				setText("");

		}
		public void keyPressed(KeyEvent e) {
			
		}
		public void keyTyped(KeyEvent e) {
			
		}
	}
	
	public class SudokuGrid {
		private JPanel grid;
		private int i, j;
		private Square newSquare;
		
		SudokuGrid(SudokuModel sud) {
			grid = new JPanel(new GridLayout(9,9));
			for (i=0; i < 9; i++) {
				for (j=0; j < 9; j++) {
					String str = "" + sud.getBoard(i, j);
					if (sud.getBoard(i, j) != 0) {
						newSquare = new Square(str, 1, i, j);
						newSquare.setBackground(Color.lightGray);
					}
					else
						newSquare = new Square(null, 1, i, j);
					
					newSquare.setFont(new Font("Garamond", Font.BOLD,16));
					newSquare.setHorizontalAlignment(JTextField.CENTER);			
					grid.add(newSquare);				
				
				}
			}
		}
		JPanel get() {
			return grid;
		}

	}
	
}
