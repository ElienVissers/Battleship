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

        Square(int x, int y) {
            this.coordinates = new int[]{x, y};
            this.wasTargeted = false;
        }

        void setTargeted() {
            wasTargeted = true;
        }

        boolean wasTargeted() {
            return wasTargeted;
        }

        int[] getCoordinates() {
            return coordinates;
        }
    }

    private List<List<Square>> shipList;
    private int sinkCounter;

    GameBoard() {
        sinkCounter = 0;
        shipList = new ArrayList<>();
    }

    void addShips(List<List<int[]>> shipCoordinates) {
        for (List<int[]> aShipCoordinateList : shipCoordinates) {
            List<Square> aShip = new ArrayList<>();
            for (int[] aCoordinate : aShipCoordinateList) {
                Square square = new Square(aCoordinate[0], aCoordinate[1]);
                aShip.add(square);
            }
            shipList.add(aShip);
        }
    }

    //TODO GamePresenter: mark the cell as targeted with css (hit/miss), plus remove that clickhandler from the gridpane, call this function
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

    //TODO loop through the shipList, if all coordinates of 1 ship have been hit, then return true --> this will trigger the GameScreenPresenter to show and black out the ship
    boolean hasSunken(int x, int y) {
        boolean sunken = false;
        for (List<Square> aShipCoordinateList : shipList) {
            boolean targetedShip = false;
            int shipSize = aShipCoordinateList.size();
            int counter = 0;
            for (Square square : aShipCoordinateList) {
                if (square.getCoordinates()[0] == x && square.getCoordinates()[1] == y) {
                    targetedShip = true;
                }
                if (square.wasTargeted()) {
                    counter++;
                }
            }
            if (targetedShip && counter == shipSize) {
                sunken = true;
            }
        }
        return sunken;
    }

    int getSinkCounter() {
        return sinkCounter;
    }
}
