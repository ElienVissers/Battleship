package Battleship.model;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020 18:43
 *
 * an element of the grid, which has its own coordinates and statuses
 */

public class Square {
    private int[] coordinates;
    private boolean isShip;
    private boolean wasTargeted;

    public Square(int x, int y) {
        this.coordinates = new int[]{x, y};
        this.isShip = false;
        this.wasTargeted = false;
    }

    public void setShip() {
        isShip = true;
    }

    public boolean isShip() {
        return isShip;
    }

    public void setTargeted() {
        wasTargeted = true;
    }

    public boolean wasTargeted() {
        return wasTargeted;
    }

    public int[] getCoordinates() {
        return coordinates;
    }
}
