package sudoku;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameState
{
	private int[][] solution;
	private Cell[][] gameBoard;
	private ArrayList<Change> history;
	
	public GameState(int[][] newSolution)
	{
		solution = newSolution;
	}
	
	public void setGameBoard(Cell[][] newGameBoard)
	{
		gameBoard = newGameBoard;
	}
	
	public Cell[][] getGameBoard()
	{
		return gameBoard;
	}
	
	public int[][] getSolution()
	{
		return solution;
	}
	
	public void undo()
	{
		// stub
	}
	
	public void redo()
	{
		// stub
	}
	
	public void save(File f) throws IOException
	{
		// stub
	}
	
	public void load(File f) throws IOException
	{
		// stub
	}
}
