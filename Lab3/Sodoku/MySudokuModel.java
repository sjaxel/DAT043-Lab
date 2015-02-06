import java.util.*;
import java.lang.*;
import java.awt.*;
import java.beans.*;
import java.io.*;

public class MySudokuModel implements SudokuModel {
	static final int ROWS = 9;
	static final int COLS = 9;
	static String testsud3 = "7.....4...2..7..8...3..8.799..5..3...6..2..9...1.97..6...3..9...3..4..6...9..1.35";
	private int[][] sud;
	private boolean findunique;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	@Override public String toString() {
		return getBoard();
	}
	//Constructor classes.
	MySudokuModel() {
		sud = new int[9][9];
	}
	
	
	MySudokuModel(MySudokuModel another) {
		sud = new int[9][9];
		int i, j;
		for (i=0; i < ROWS; i++){
			for (j=0; j < COLS; j++) {
				this.setBoard(i, j, another.getBoard(i, j));
			}
		}		
	}
	
	//Propery change methods
     public void addPropertyChangeListener(PropertyChangeListener listener) {
         this.pcs.addPropertyChangeListener(listener);
     }

     public void removePropertyChangeListener(PropertyChangeListener listener) {
         this.pcs.removePropertyChangeListener(listener);
     }

	
	//Clear board method
	public void clear(){
		MySudokuModel oldsud = new MySudokuModel(this);
		int i, j;
		for (i=0; i < ROWS; i++) {
			for (j=0; j < COLS; j++) {
				sud[i][j] = 0;
			}
		}
		// Property event fire
		this.pcs.firePropertyChange("Clear", oldsud, this);
	}
	//Get sudoku array
	int[][] getSud() {
		return this.sud;
	}
	
	//Set board method 1
	public void setBoard(int row, int col, int val) {
		if (isLegal(row, col, val)) {
			Integer oldval = sud[row][col];
			Integer inteVal = val;
			sud[row][col] = val;
			this.pcs.firePropertyChange("setBoard", null, this);
		}
		else
			throw new IllegalArgumentException("Illegal value: " + val + "Pos: " + row + col);
	}
	
	//Set board method from string input.
	public void setBoard(String raw_input) {
		int i, j, val;
		MySudokuModel oldsud = new MySudokuModel(this);
		String input = raw_input.replaceAll("\\.", "0");
		String[] tokens = input.split("[\\n]");
		if (true) {
			for (i=0; i < ROWS; i++){
				for (j=0; j < COLS; j++) {
					val = Character.getNumericValue(tokens[i].charAt(j));
					if (isLegal(i, j, val))
						sud[i][j] = val;
					else
						throw new IllegalArgumentException("Illegal value in input file");
				}
			}
		}
		else
			throw new NumberFormatException("Incorrect formating");
		this.pcs.firePropertyChange("setBoard", oldsud, this);
		
	}
	//For parsing differnt kinds of string inputs
	public void setBoard(String input, boolean p) {
		int i = 0;
		String str = "";
		if (p == true) {
			String zero_input = input.replaceAll("\\.", "0");
			while (i < 81) {
				str = str + zero_input.substring(i, (i+9)) + "\n";
				i = i + 9;
			}
			setBoard(str);	
		}
	}
	
	//Get board value of row/col
	public int getBoard(int row, int col) {
		return sud[row][col];
	}
	
	//String getboard
	public String getBoard() {
		int i, j;
		String sodoku = "";
		for (i=0; i < ROWS; i++){
				for (j=0; j < COLS; j++) {
					sodoku = sodoku + getBoard(i, j);
				}
			sodoku = sodoku + "\n";
		}
		return sodoku;
	}
	
	//Checks if it's a legal value at location.
	public boolean isLegal(int row, int col, int val) {
		boolean inRow = arrayCheck(getRow(row), val);
		boolean inCol = arrayCheck(getCol(col), val);
		boolean inBlock = arrayCheck(getBlock(row, col), val);
		if ((val >= 0) && (val < 10)) {
			if (val == 0)
				return true;
			else if (inRow || inCol || inBlock)
				return false;
			else
				return true;
		}
		else {
			return false;
		}
	}
	
	//Helper function for checking arrays.
	boolean arrayCheck(int[] r, int val) {
		int i;
		for (i=0; i < COLS; i++) {
			if (r[i] == val) {
				return true;
			}
		}
		return false;
	}
	
	int arrayCount(int[] r, int val) {
		int i;
		int match = 0;
		for (i=0; i < r.length; i++) {
			if (r[i] == val) {
				match++;
			}
		}
		return match;
	}	
	
	//Board values
	int[] getRow(int row) {
		return sud[row];
	}
	

	int[] getCol(int col) {
		int i;
		int[] colc = new int[9];
		for (i=0; i < ROWS; i++) {
			colc[i] = sud[i][col];
		}
		return colc;
	}
	
	int[] getBlock(int row, int col) {
		int i, j;
		int index = 0;
		int lower_row = (row-(row % 3));
		int lower_col = (col-(col % 3));
		int[] block = new int[9];
		for (i=lower_row; i < (lower_row + 3); i++) {
			for (j=lower_col; j < (lower_col + 3); j++) {
				block[index] = sud[i][j];
				index++;
			}
		}
		return block;
	}
	
	//Solver part
	
	//Checks if sudoku contains zeroes aka is it solved.
	public boolean isSolved() {
		int i;
		for (i=0; i < ROWS; i++) {
			if (arrayCheck(sud[i], 0))
				return false;
		}
		return true;
	}
	int zeroCounter(int row, int col) {
		int row_zero, col_zero, block_zero;
		row_zero = arrayCount(getRow(row), 0);
		col_zero = arrayCount(getCol(col), 0);
		block_zero = arrayCount(getBlock(row, col), 0);
		return row_zero + col_zero + block_zero;
	}

	Point zeroFinder() {
		int i, j, zeroes;
		boolean first = true;
		Point bp = new Point();
		for (i=0; i < ROWS; i++) {
			for (j=0; j < COLS; j++) {
				if (sud[i][j] == 0) {
					zeroes = zeroCounter(i, j);
					if (first == true) {
						bp.setLocation(i, j);
						first = false;
					}		
					else if (zeroes < zeroCounter(bp.x, bp.y)) {
						bp.setLocation(i, j);
					}
				}
			}
		}
		return bp;	
	}
	
	public boolean solve() {
		int i, row, col;
		MySudokuModel oldsud = new MySudokuModel(this);
		Point bp;
		if (isSolved())
			return true;
		else {
			bp = zeroFinder();
			for (i=1; i < 10; i++) {
				if (isLegal(bp.x, bp.y, i)) {
					setBoard(bp.x, bp.y, i);
					if (solve())
						if (findunique == true)
							findunique = false;
						else {
							this.pcs.firePropertyChange("setBoard", oldsud, this);
							return true;
						}
					else
						setBoard(bp.x, bp.y, 0);
					
				}
			}
		return false;
		}
	}
	
	public boolean isSolvable() {
		MySudokuModel r = new MySudokuModel(this);
		return r.solve();
	}
	
	public boolean isUnique() {
		findunique = true;
		return !solve();
	}

}
















