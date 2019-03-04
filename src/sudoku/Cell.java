package sudoku;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Cell extends JPanel
{
	private boolean[] placements;
	
	public Cell(int row, int col)
	{
		placements = new boolean[9];
	}
	
	public void updatePlacements(int[][] board)
	{
		// stub
	}
}
