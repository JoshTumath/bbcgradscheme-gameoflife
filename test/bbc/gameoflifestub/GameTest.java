package bbc.gameoflifestub;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    protected Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test(expected = FailedEvolutionException.class)
    public void testEvolutionWithNoInitializing() throws FailedEvolutionException {
        game.evolve();
    }

    @Test
    public void testUnderpopulation() {
        HashSet<Cell> cells;
        
        cells = new HashSet<Cell>();
        cells.add(new Cell(1, 1));
        game.initializeGrid(new Life(cells));
        game.evolve();
        assertTrue(game.getLife().getLiveCells().isEmpty());
        
        cells = new HashSet<Cell>();
        cells.add(new Cell(1, 1));
        cells.add(new Cell(1, 2));
        game.initializeGrid(new Life(cells));
        game.evolve();
        assertTrue(game.getLife().getLiveCells().isEmpty());
    }
    
    @Test
    public void testOverpopulation() {
        HashSet<Cell> cells;
        
        cells = new HashSet<Cell>();
        cells.add(new Cell(1, 1));
        cells.add(new Cell(1, 3));
        cells.add(new Cell(2, 2));
        cells.add(new Cell(3, 1));
        cells.add(new Cell(3, 2));
        game.initializeGrid(new Life(cells));
        game.evolve();
        assertFalse(game.getLife().getLiveCells().contains(new Cell(2, 2)));
        
        cells = new HashSet<Cell>();
        cells.add(new Cell(1, 1));
        cells.add(new Cell(1, 2));
        cells.add(new Cell(1, 3));
        cells.add(new Cell(2, 1));
        cells.add(new Cell(2, 2));
        cells.add(new Cell(2, 3));
        cells.add(new Cell(3, 1));
        cells.add(new Cell(3, 2));
        cells.add(new Cell(3, 3));
        game.initializeGrid(new Life(cells));
        game.evolve();
        assertFalse(game.getLife().getLiveCells().contains(new Cell(2, 2)));
    }
    
    @Test
    public void testSurvival() {
        HashSet<Cell> cells;
        
        cells = new HashSet<Cell>();
        cells.add(new Cell(1, 1));
        cells.add(new Cell(1, 2));
        cells.add(new Cell(2, 1));
        game.initializeGrid(new Life(cells));
        game.evolve();
        assertTrue(game.getLife().getLiveCells().contains(new Cell(1, 1)));
        
        cells = new HashSet<Cell>();
        cells.add(new Cell(1, 1));
        cells.add(new Cell(1, 2));
        cells.add(new Cell(2, 1));
        cells.add(new Cell(2, 2));
        game.initializeGrid(new Life(cells));
        game.evolve();
        assertTrue(game.getLife().getLiveCells().contains(new Cell(1, 1)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(1, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 1)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 2)));
    }
    
    @Test
    public void testCreationOfLife() {
        HashSet<Cell> cells;
        
        cells = new HashSet<Cell>();
        cells.add(new Cell(1, 1));
        cells.add(new Cell(1, 2));
        cells.add(new Cell(1, 3));
        cells.add(new Cell(2, 2));
        game.initializeGrid(new Life(cells));
        game.evolve();
        assertEquals(7, game.getLife().getLiveCells().size());
        assertTrue(game.getLife().getLiveCells().contains(new Cell(1, 1)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(1, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(1, 3)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(0, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 1)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 3)));
    }

    public void testEvolutionWithNoLife() throws FailedEvolutionException {
        game.initializeGrid(new Life(new HashSet<Cell>()));
        game.evolve();
        
        assertTrue(game.getLife().getLiveCells().isEmpty());
    }
    
    @Test
    public void testHorizontalLine() {
        HashSet<Cell> cells = new HashSet<Cell>();
        cells.add(new Cell(2, 1));
        cells.add(new Cell(2, 2));
        cells.add(new Cell(2, 3));
        game.initializeGrid(new Life(cells));
        game.evolve();
        assertEquals(3, game.getLife().getLiveCells().size());
        assertTrue(game.getLife().getLiveCells().contains(new Cell(1, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(3, 2)));
        
        game.evolve();
        assertEquals(3, game.getLife().getLiveCells().size()); 
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 1)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 3)));   
        
        game.evolve();
        assertEquals(3, game.getLife().getLiveCells().size());
        assertTrue(game.getLife().getLiveCells().contains(new Cell(1, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(3, 2)));
        
        game.evolve();
        assertEquals(3, game.getLife().getLiveCells().size());
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 1)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 3)));
    }
    
    @Test
    public void testGlider() {
        HashSet<Cell> cells;
        
        cells = new HashSet<Cell>();
        cells.add(new Cell(1, 2));
        cells.add(new Cell(2, 3));
        cells.add(new Cell(3, 1));
        cells.add(new Cell(3, 2));
        cells.add(new Cell(3, 3));
        game.initializeGrid(new Life(cells));
        game.evolve();
        assertEquals(5, game.getLife().getLiveCells().size());
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 1)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 3)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(3, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(3, 3)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(4, 2)));
        
        game.evolve();
        assertEquals(5, game.getLife().getLiveCells().size());
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 3)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(3, 1)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(3, 3)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(4, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(4, 3)));
        
        game.evolve();
        assertEquals(5, game.getLife().getLiveCells().size());
        assertTrue(game.getLife().getLiveCells().contains(new Cell(2, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(3, 3)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(3, 4)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(4, 2)));
        assertTrue(game.getLife().getLiveCells().contains(new Cell(4, 3)));
    }
}
