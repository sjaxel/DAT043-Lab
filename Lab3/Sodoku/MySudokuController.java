import java.beans.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

 class MySudokuController extends JPanel implements SudokuController{
	SudokuModel sud;
	History history;
	
	MySudokuController (SudokuModel s){
		sud = s;
		history = new History();
		ButtonPane pane = new ButtonPane();
		add(pane);
		
	}
	
	public boolean input(int row, int col, char value) {
		int ivalue = ((int)value - 48);
		if (sud.isLegal(row, col, ivalue)) {
			int old = sud.getBoard(row, col);
			sud.setBoard(row, col, ivalue);
			history.make(row, col, ivalue, old);
			return true;
		}
		else {
			return false;
		}
	}
	
	class History {
		Move[] moves = new Move[100];
		int pos;
		
		History() {
			int pos = 0;
		}
		
		void make(int r, int c, int v, int old) {
			pos++;
			moves[pos] = new Move(r, c, v, old);		
		}
		void redo() {
			if (moves[pos+1] != null) {
				pos++;
				sud.setBoard(moves[pos].row, moves[pos].col, moves[pos].val);
			}
		}
		void undo() {
			if (pos > 0) {
				sud.setBoard(moves[pos].row, moves[pos].col, moves[pos].old);
				pos--;
			}
		}
				
		class Move extends History {
			public int row, col, val, old;
			Move(int r, int c, int v, int o) {
				row = r; col = c; val = v; old = o; 
			}
		public String toString() {
			return "R: " + row + "C: " + col + "V: " + val;
		}

		}
			
	}

	class ButtonPane extends JPanel {
		private JButton newbt, mksolvebt, solvebt, undobt, redobt;
		
		ButtonPane() {
			setLayout(new FlowLayout());
			newbt = new JButton("New");
			mksolvebt = new JButton("Make solvable");
			solvebt = new JButton("Solve");
			undobt = new JButton("Undo");
			redobt = new JButton("Redo");
			add(newbt); add(mksolvebt); add(solvebt);
			add(undobt); add(redobt);
			
			newbt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("NewButton");
				}
     
			});
			
			mksolvebt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					while ((sud.isSolvable() != true) && (history.pos > 0)) {
						history.undo();
					}
				}
     
			});			
			solvebt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					while ((sud.isSolvable() != true) && (history.pos > 0)) {
						history.undo();
					}
					if (history.pos > 0)
						sud.solve();
				}
     
			});	
			undobt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					history.undo();
				}
     
			});	
			redobt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					history.redo();
				}
     
			});				
		}
		

	}
	
	
}
