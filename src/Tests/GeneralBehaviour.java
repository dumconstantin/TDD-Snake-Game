package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class GeneralBehaviour {
	int xMax = 16;
	int yMax = 16;
	
	class Cell {
		int x = 0;
		int y = 0;
		String status = "0";
		public Cell(int x, int y, String status) {
			this.x = x;
			this.y = y;
			this.status = status;
		}
	}
	
	public HashMap<Integer, HashMap<Integer, String>> getMap(int xMax, int yMax) {
		return getMap(xMax, yMax, new ArrayList<Cell>());
	}
	
	public HashMap<Integer, HashMap<Integer, String>> getMap(int xMax, int yMax, ArrayList<Cell> occupiedCells) {
		HashMap<Integer, HashMap<Integer, String>> map = new HashMap<Integer, HashMap<Integer, String>>();
		
		for (int x = 0; x < xMax; x += 1) {
			map.put(x, new HashMap<Integer, String>());
			for (int y = 0; y < yMax; y += 1) {
				map.get(x).put(y, "0");
			}
		}
		
		for (Cell cell : occupiedCells) {
			map.get(cell.x).put(cell.y, cell.status);
		}
		
		return map;
	}
	
	@Test
	public void isInsideBoundriesMove() {
		int moveX = 0;
		int moveY = 0;
		HashMap<Integer, HashMap<Integer, String>> map = getMap(xMax, yMax);
		
		assertTrue("Move is inside boundries", true == map.containsKey(moveX) && true == map.get(moveX).containsKey(moveY));
	}

	@Test
	public void isOutsideBoundriesMove() {
		int moveX = 17;
		int moveY = 0;
		
		HashMap<Integer, HashMap<Integer, String>> map = getMap(xMax, yMax);
		assertFalse("Move is outside boundries", true == map.containsKey(moveX) && true == map.get(moveX).containsKey(moveY));
	}
	
	@Test
	public void isMoveInCellSnakeBody() {
		int moveX = 0;
		int moveY = 0;
		ArrayList<Cell> occupiedCells = new ArrayList<Cell>();
		occupiedCells.add(new Cell(moveX, moveY, "*"));
		HashMap<Integer, HashMap<Integer, String>> map = getMap(xMax, yMax, occupiedCells);

		assertTrue("Move should go to a false location", map.get(moveX).get(moveY) == "*");
	}
	
	@Test
	public void isTokenCellValidMove() {
		int moveX = 0;
		int moveY = 0;
		ArrayList<Cell> occupiedCells = new ArrayList<Cell>();
		occupiedCells.add(new Cell(2, 3, "#"));
		HashMap<Integer, HashMap<Integer, String>> map = getMap(xMax, yMax, occupiedCells);
		
		assertTrue("Move should go to a valid location", map.get(moveX).get(moveY) == "0" || map.get(moveX).get(moveY) == "#");
	}
	
	@Test
	public void isScoreIncremented() {
		int moveX = 5;
		int moveY = 5;
		int score = 0;
		ArrayList<Cell> occupiedCells = new ArrayList<Cell>();
		occupiedCells.add(new Cell(moveX, moveY, "#"));
		HashMap<Integer, HashMap<Integer, String>> map = getMap(xMax, yMax, occupiedCells);
		
		if ("#" == map.get(moveX).get(moveY)) {
			score += 1;
		}
		
		assertTrue("score was incremented", 1 == score);
	}
	
	public void isSnakeIncremented() {
		int moveX = 5;
		int moveY = 5;
		
		ArrayList<Cell> occupiedCells = new ArrayList<Cell>();
		occupiedCells.add(new Cell(moveX, moveY, "#"));
		HashMap<Integer, HashMap<Integer, String>> map = getMap(xMax, yMax, occupiedCells);
	}
}
