package Battleship.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020 16:16
 *
 * saves ship positions;
 * remembers where rockets land;
 * counts how many ships have sunk;
 */

class GameBoard {

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

        public boolean isStartSquare() {
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

    GameBoard() {
        sinkCounter = 0;
        sinkSize = 0;
        sinkX = 0;
        sinkY = 0;
        sinkDirection = 0;
        shipList = new ArrayList<>();
    }

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
