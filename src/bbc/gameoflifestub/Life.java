package bbc.gameoflifestub;

import java.util.Set;

public class Life {
    private Set<Cell> liveCells;

    public Life(Set<Cell> initialLiveCells) {
        this.liveCells = initialLiveCells;
    }

    // Read-only access to the game state
    public Set<Cell> getLiveCells() {
        return this.liveCells;
    }

    /**
     * Counts the number of live cells that are adjacent to a given cell.
     * 
     * @param cell
     *            the cell to check around
     * @return number of live neighbouring cells
     */
    public int countLiveCellNeighbours(Cell cell) {
        int result = 0;

        for (int yModifier = -1; yModifier <= 1; yModifier++) {
            for (int xModifier = -1; xModifier <= 1; xModifier++) {
                // Skip checking the same cell
                if (!(xModifier == 0 && yModifier == 0)
                        && liveCells.contains(new Cell(cell.getX() + xModifier, cell.getY() + yModifier))) {
                    result++;
                }
            }
        }

        return result;
    }

    /**
     * Check whether a cell should survive in the next evolution, given the
     * number of live neighbours around it.
     * 
     * @param numNeighbours
     *            number of live neighbours around a cell
     * @return true if the cell will survive
     */
    public boolean cellShouldSurvive(int numNeighbours) {
        return !isUnderpopulated(numNeighbours) && !isOvercrowded(numNeighbours);
    }

    /**
     * Check if a live cell will die from underpopulation.
     * 
     * @param numNeighbours
     *            number of live neighbours around a cell
     * @return true if the cell will survive
     */
    private boolean isUnderpopulated(int numNeighbours) {
        return numNeighbours < 2;
    }

    /**
     * Check if a live cell will die from overcrowding.
     * 
     * @param numNeighbours
     *            number of live neighbours around a cell
     * @return true if the cell will survive
     */
    private boolean isOvercrowded(int numNeighbours) {
        return numNeighbours > 3;
    }
}
