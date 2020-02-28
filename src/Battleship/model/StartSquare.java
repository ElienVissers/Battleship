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
    private int size;

    StartSquare(int x, int y, boolean horizontal, int size) {
        this.coordinates = new int[]{x, y};
        this.horizontal = horizontal;
        this.size = size;
    }
    public int[] getCoordinates() {
        return coordinates;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getSize() {
        return size;
    }
}
