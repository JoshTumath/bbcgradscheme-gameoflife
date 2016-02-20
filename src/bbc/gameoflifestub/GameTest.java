package bbc.gameoflifestub;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

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

    @Test(expected = FailedEvolutionException.class)
    public void testEvolutionWithNoLife() throws FailedEvolutionException {
        game.initializeGrid(new Life(new HashSet<Cell>()));
        game.evolve();
    }
}
