package bbc.gameoflifestub;

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

    /**
     * Iterates the grid to an 'evolved' state where the life or death of a cell
     * in the grid changes based on rules relating to how many living cells
     * there are neighbouring each individual cell.
     * 
     * <p>
     * The four rules for the life and survival of each cell are the following:
     * 
     * <ul>
     * <li>If a live cell has less than two neighbours, it dies.
     * <li>If a live cell has more than three neighbours, it dies.
     * <li>If a live cell has two or three neighbours, it stays alive.
     * <li>If an empty cell has only three neighbours, the cell becomes alive.
     * </ul>
     * 
     * @throws FailedEvolutionException if the grid is not initialized or
     *                                  contains no live cells
     */
    public void evolve() throws FailedEvolutionException {
        if (life == null || life.getLiveCells().isEmpty()) {
            throw new FailedEvolutionException("Grid contains no live cells");
        }
        
        // TODO
    }
}
