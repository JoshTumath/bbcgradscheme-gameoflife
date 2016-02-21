package bbc.gameoflifestub;

import java.util.Set;

public class Game {
    private Life life;

    /**
     * Set the initial layout of the grid with the information (e.g. positions)
     * of all the living cells.
     * 
     * @param life
     *            life that has been set with data of all the living cells
     */
    public void initializeGrid(Life life) {
        this.life = life;
    }
    
    // Method should only be used for testing.
    protected Life getLife() {
        return life;
    }

    /**
     * Iterates the grid to an 'evolved' state where the life or death of a cell
     * in the grid changes based on rules relating to how many living cells
     * there are neighbouring each individual cell.
     * 
     * <p>
     * The four rules for the life and survival of each cell are the following:
     * 
     * <ol>
     * <li>If a live cell has less than two neighbours, it dies.
     * <li>If a live cell has more than three neighbours, it dies.
     * <li>If a live cell has two or three neighbours, it stays alive.
     * <li>If an empty cell has only three neighbours, the cell becomes alive.
     * </ol>
     * 
     * @throws FailedEvolutionException
     *             if the grid is not initialized
     */
    public void evolve() throws FailedEvolutionException {
        if (life == null) {
            throw new FailedEvolutionException("Grid contains no live cells");
        }

        Set<Cell> evolvedCells = newInstanceOfSet();

        for (Cell cell : life.getLiveCells()) {
            // To satisfy rules 1, 2 and 3, we check if this cell can survive.
            if (life.cellShouldSurvive(life.countLiveCellNeighbours(cell))) {
                evolvedCells.add(new Cell(cell.getX(), cell.getY()));
            }

            // To satisfy rule 4, we check if surrounding empty cells can
            // become alive.
            for (Cell emptyCell : life.getEmptyNeighbouringCells(cell)) {
                if (life.cellShouldBeCreated(life.countLiveCellNeighbours(emptyCell))) {
                    evolvedCells.add(new Cell(emptyCell.getX(), emptyCell.getY()));
                }
            }
        }
        
        life = new Life(evolvedCells);
    }

    /**
     * Creates a new instance of Set&lt;Cell&gt;, based on what type of
     * Set&lt;Cell&gt; is used already in the existing Life to store the set of
     * live cells.
     * 
     * @return a new, empty Set&lt;Cell&gt;
     */
    @SuppressWarnings("unchecked")
    private Set<Cell> newInstanceOfSet() {
        Set<Cell> result = null;

        try {
            // We don't know what implementation of the Set interface is being
            // used (e.g. a HashSet class), so we derive it from the Set that is
            // used in the existing Life object.
            result = (Set<Cell>) life.getLiveCells().getClass().newInstance();
        } catch (InstantiationException e) {
            throw new FailedEvolutionException(e);
        } catch (IllegalAccessException e) {
            throw new FailedEvolutionException(e);
        }

        return result;
    }
}
