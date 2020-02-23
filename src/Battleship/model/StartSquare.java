package Battleship.model;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020 18:43
 *
 * an element of the grid, which has its own coordinates and statuses
 */

public class StartSquare {
    private int[] coordinates;
    private boolean horizontal;

    public StartSquare(int x, int y) {
        this.coordinates = new int[]{x, y};
    }
    public int[] getCoordinates() {
        return coordinates;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean isHorizontal() {
        return horizontal;
    }
}
