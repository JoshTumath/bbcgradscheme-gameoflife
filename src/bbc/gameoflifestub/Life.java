package bbc.gameoflifestub;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Life {
    private final Set<Cell> liveCells;

    public Life(final Set<Cell> initialLiveCells) {
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
    public int countLiveCellNeighbours(final Cell cell) {
        int result = 0;

        for (Cell neighbouringCell : getNeighbouringCells(cell)) {
            if (liveCells.contains(new Cell(neighbouringCell.getX(), neighbouringCell.getY()))) {
                result++;
            }
        }

        return result;
    }

    /**
     * Looks at each neighbouring cell and returns those cells that are empty.
     * 
     * @param cell
     *            the cell to check around
     * @return list of cells that are empty
     */
    public List<Cell> getEmptyNeighbouringCells(final Cell cell) {
        final List<Cell> result = new LinkedList<Cell>();

        for (Cell neighbouringCell : getNeighbouringCells(cell)) {
            if (!liveCells.contains(new Cell(neighbouringCell.getX(), neighbouringCell.getY()))) {
                result.add(neighbouringCell);
            }
        }

        return result;
    }

    /**
     * Generate a list of the eight cells neighbouring a given cell.
     * 
     * @param cell
     *            the cell to check around
     * @return list of neighbouring cells
     */
    private List<Cell> getNeighbouringCells(final Cell cell) {
        final List<Cell> result = new LinkedList<Cell>();

        for (int yModifier = -1; yModifier <= 1; yModifier++) {
            for (int xModifier = -1; xModifier <= 1; xModifier++) {
                // Skip checking the same cell
                if (!(xModifier == 0 && yModifier == 0)) {
                    result.add(new Cell(cell.getX() + xModifier, cell.getY() + yModifier));
                }
            }
        }

        return result;
    }

    /**
     * Check whether a live cell should survive in the next evolution, given the
     * number of live neighbours around it.
     * 
     * @param numNeighbours
     *            number of live neighbours around a cell
     * @return true if the cell will survive
     */
    public boolean cellShouldSurvive(final int numNeighbours) {
        return !isUnderpopulated(numNeighbours) && !isOvercrowded(numNeighbours);
    }

    /**
     * Check whether an empty cell should have a live cell created in it in the
     * next evolution, given the number of live neighbours around it.
     * 
     * @param numNeighbours
     *            number of live neighbours around a cell
     * @return true if the cell should be created
     */
    public boolean cellShouldBeCreated(final int numNeighbours) {
        return numNeighbours == 3;
    }

    /**
     * Check if a live cell will die from underpopulation.
     * 
     * @param numNeighbours
     *            number of live neighbours around a cell
     * @return true if the cell will survive
     */
    private boolean isUnderpopulated(final int numNeighbours) {
        return numNeighbours < 2;
    }

    /**
     * Check if a live cell will die from overcrowding.
     * 
     * @param numNeighbours
     *            number of live neighbours around a cell
     * @return true if the cell will survive
     */
    private boolean isOvercrowded(final int numNeighbours) {
        return numNeighbours > 3;
    }
}
