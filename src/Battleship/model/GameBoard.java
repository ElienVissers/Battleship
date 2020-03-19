package Battleship.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class can add a list of ships to the board, checks whether certain target coordinates would hit a ship on the board and knows when a ship has sunken.
 * It contains the inner class "Square" which serves as a "smart coordinate" on the gameboard.
 *
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020
 */

class GameBoard {

    /**
     * The class Square is an inner class of GameBoard and serves as a "smart coordinate" of the gameboard.
     * It contains the coordinates, whether or not the coordinate has been hit and if it also serves as the "StartSquare" of a ship.
     */
    public static class Square {
        private int[] coordinates;
        private boolean wasTargeted;
        private boolean isStartSquare;

        Square(int x, int y, boolean isStartSquare) {
            this.coordinates = new int[]{x, y};
            this.wasTargeted = false;
            this.isStartSquare = isStartSquare;
        }

        void setTargeted() {
            wasTargeted = true;
        }

        boolean wasTargeted() {
            return wasTargeted;
        }

        boolean isStartSquare() {
            return isStartSquare;
        }

        int[] getCoordinates() {
            return coordinates;
        }
    }

    private List<List<Square>> shipList;
    private int sinkCounter;
    private int sinkSize;
    private int sinkX;
    private int sinkY;
    private int sinkDirection;

    /**
     * Generates a new GameBoard. Initialises the fields.
     */
    GameBoard() {
        sinkCounter = 0;
        sinkSize = 0;
        sinkX = 0;
        sinkY = 0;
        sinkDirection = 0;
        shipList = new ArrayList<>();
    }

    /**
     * Adds a list of ships to the gameboard:
     * 1. for every coordinate, a new Square is created
     * 2. for every ship, the coordinates are added to the List in the GameBoard class
     *
     * @param shipCoordinates The list of ship coordinates to be placed on the gameboard
     */
    void addShips(List<List<int[]>> shipCoordinates) {
        for (List<int[]> aShipCoordinateList : shipCoordinates) {
            List<Square> aShip = new ArrayList<>();
            for (int[] aCoordinate : aShipCoordinateList) {
                Square square;
                if (aCoordinate.length == 2) {
                    square = new Square(aCoordinate[0], aCoordinate[1], false);
                } else {
                    square = new Square(aCoordinate[0], aCoordinate[1], true);
                }
                aShip.add(square);
            }
            shipList.add(aShip);
        }
    }

    /**
     * Checks whether or not a ship is positioned at a target coordinate.
     * If a ship has been positioned at this coordinate, that Square will be updated with setTargeted().
     *
     * @param x The target x-coordinate
     * @param y The target y-coordinate
     */
    boolean hitOrMiss(int x, int y) {
        boolean hit = false;
        for (List<Square> aShipCoordinateList : shipList) {
            for (Square square : aShipCoordinateList) {
                if (square.getCoordinates()[0] == x && square.getCoordinates()[1] == y) {
                    square.setTargeted();
                    hit = true;
                }
            }
        }
        return hit;
    }

    /**
     * Checks whether or not a ship has been sunken, because of the last target hit.
     * If a ship was destroyed, the relevant fields of the GameBoard class are updated with that ship's information.
     *
     * @param x The target x-coordinate
     * @param y The target y-coordinate
     */
    boolean hasSunken(int x, int y) {
        boolean sunken = false;
        sinkDirection = 0;
        for (List<Square> aShipCoordinateList : shipList) {
            boolean targetedShip = false;
            int shipSize = aShipCoordinateList.size();
            int counter = 0;
            int startX = 0;
            int startY = 0;
            ArrayList<Integer> tempX = new ArrayList<>();
            for (Square square : aShipCoordinateList) {
                if (square.getCoordinates()[0] == x && square.getCoordinates()[1] == y) {
                    targetedShip = true;
                }
                if (square.wasTargeted()) {
                    counter++;
                    tempX.add(square.getCoordinates()[0]);
                }
                if (square.isStartSquare()) {
                    startX = square.getCoordinates()[0];
                    startY = square.getCoordinates()[1];
                }
            }
            if (targetedShip && counter == shipSize) {
                sunken = true;
                sinkCounter++;
                sinkSize = shipSize;
                sinkX = startX;
                sinkY = startY;
                {
                    counter = 0;
                    int tempX0 = tempX.get(0);
                    for (int xCoord : tempX) {
                        if (xCoord == tempX0) counter++;
                    }
                    if (counter == tempX.size()) sinkDirection = 1;
                }
            }
        }
        return sunken;
    }

    int getSinkCounter() { return sinkCounter; }
    int getSinkSize() { return sinkSize; }
    int getSinkX() { return sinkX; }
    int getSinkY() { return sinkY; }
    int getSinkDirection() { return sinkDirection; }
}
