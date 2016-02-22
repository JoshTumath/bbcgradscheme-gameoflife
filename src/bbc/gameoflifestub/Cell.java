package bbc.gameoflifestub;

public class Cell {
    private final int x;
    private final int y;

    public Cell(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int hashCode() {
        return 31 * 31 * x + 31 * y;
    }

    public boolean equals(final Object other) {
        final Cell otherCell = (Cell) other;

        return otherCell.x == x && otherCell.y == y;
    }
    
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
