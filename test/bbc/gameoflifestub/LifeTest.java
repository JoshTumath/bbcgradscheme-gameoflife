package bbc.gameoflifestub;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LifeTest {
    protected Set<Cell> setOfCells;
    protected Life life;

    @Before
    public void setUp() {
        setOfCells = new HashSet<Cell>();
        life = new Life(setOfCells);
    }

    @Test
    public void testInitialisation() {
        setOfCells.add(new Cell(1, 1));
        setOfCells.add(new Cell(2, 2));

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
        setOfCells.add(new Cell(1, 1));
        setOfCells.add(new Cell(2, 2));
        assertEquals(1, life.countLiveCellNeighbours(new Cell(0, 0)));
        assertEquals(1, life.countLiveCellNeighbours(new Cell(1, 1)));
        assertEquals(1, life.countLiveCellNeighbours(new Cell(2, 2)));
        assertEquals(2, life.countLiveCellNeighbours(new Cell(1, 2)));
        assertEquals(2, life.countLiveCellNeighbours(new Cell(2, 1)));

        setOfCells.add(new Cell(100, 100));
        setOfCells.add(new Cell(101, 100));
        setOfCells.add(new Cell(102, 100));
        setOfCells.add(new Cell(100, 102));
        setOfCells.add(new Cell(101, 102));
        setOfCells.add(new Cell(102, 102));
        assertEquals(4, life.countLiveCellNeighbours(new Cell(100, 101)));
        assertEquals(6, life.countLiveCellNeighbours(new Cell(101, 101)));
        assertEquals(4, life.countLiveCellNeighbours(new Cell(102, 101)));
    }
}
