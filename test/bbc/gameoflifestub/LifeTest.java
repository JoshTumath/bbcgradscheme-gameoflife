package bbc.gameoflifestub;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LifeTest {
    protected Set<Cell> cells;
    protected Life life;

    @Before
    public void setUp() {
        cells = new HashSet<Cell>();
        life = new Life(cells);
    }

    @Test
    public void testInitialisation() {
        cells.add(new Cell(1, 1));
        cells.add(new Cell(2, 2));

        assertEquals(2, life.getLiveCells().size());
    }

    @Test
    public void testUnderpopulation() {
        assertFalse(life.cellShouldSurvive(0));
        assertFalse(life.cellShouldSurvive(1));
    }

    @Test
    public void testSurvival() {
        assertTrue(life.cellShouldSurvive(2));
        assertTrue(life.cellShouldSurvive(3));
    }

    @Test
    public void testOvercrowding() {
        assertFalse(life.cellShouldSurvive(4));
        assertFalse(life.cellShouldSurvive(5));
        assertFalse(life.cellShouldSurvive(6));
        assertFalse(life.cellShouldSurvive(7));
        assertFalse(life.cellShouldSurvive(8));
    }

    @Test
    public void testCountingNeighboursOfLonelyCell() {
        assertEquals(0, life.countLiveCellNeighbours(new Cell(0, 0)));
        assertEquals(0, life.countLiveCellNeighbours(new Cell(-10000, -20000)));
        assertEquals(0, life.countLiveCellNeighbours(new Cell(10000, 20000)));
    }

    @Test
    public void testCountingNeighboursOfNeighbouredCell() {
        cells.add(new Cell(1, 1));
        cells.add(new Cell(2, 2));
        assertEquals(1, life.countLiveCellNeighbours(new Cell(0, 0)));
        assertEquals(1, life.countLiveCellNeighbours(new Cell(1, 1)));
        assertEquals(1, life.countLiveCellNeighbours(new Cell(2, 2)));
        assertEquals(2, life.countLiveCellNeighbours(new Cell(1, 2)));
        assertEquals(2, life.countLiveCellNeighbours(new Cell(2, 1)));

        cells.add(new Cell(100, 100));
        cells.add(new Cell(101, 100));
        cells.add(new Cell(102, 100));
        cells.add(new Cell(100, 102));
        cells.add(new Cell(101, 102));
        cells.add(new Cell(102, 102));
        assertEquals(4, life.countLiveCellNeighbours(new Cell(100, 101)));
        assertEquals(6, life.countLiveCellNeighbours(new Cell(101, 101)));
        assertEquals(4, life.countLiveCellNeighbours(new Cell(102, 101)));
    }
    
    @Test
    public void testGettingEmptyNeighbouringCells() {
        List<Cell> emptyCells;
        
        emptyCells = life.getEmptyNeighbouringCells(new Cell(2, 2));
        assertEquals(8, emptyCells.size());
        assertTrue(emptyCells.contains(new Cell(1, 1)));
        assertTrue(emptyCells.contains(new Cell(1, 2)));
        assertTrue(emptyCells.contains(new Cell(1, 3)));
        assertTrue(emptyCells.contains(new Cell(2, 1)));
        assertTrue(emptyCells.contains(new Cell(2, 3)));
        assertTrue(emptyCells.contains(new Cell(3, 1)));
        assertTrue(emptyCells.contains(new Cell(3, 2)));
        assertTrue(emptyCells.contains(new Cell(3, 3)));
        
        cells.add(new Cell(1, 1));
        cells.add(new Cell(1, 3));
        cells.add(new Cell(2, 1));
        cells.add(new Cell(2, 3));
        emptyCells = life.getEmptyNeighbouringCells(new Cell(2, 2));
        assertEquals(4, emptyCells.size());
        assertTrue(emptyCells.contains(new Cell(1, 2)));
        assertTrue(emptyCells.contains(new Cell(3, 1)));
        assertTrue(emptyCells.contains(new Cell(3, 2)));
        assertTrue(emptyCells.contains(new Cell(3, 3)));
    }
}
