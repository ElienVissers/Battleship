package Battleship.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020 15:50
 *
 * controls the behaviour of a player
 */

public class Player {

    static class StartSquare {
        private int[] coordinates;
        private boolean horizontal;
        private int size;

        StartSquare(int x, int y, boolean horizontal, int size) {
            this.coordinates = new int[]{x, y};
            this.horizontal = horizontal;
            this.size = size;
        }
        int[] getCoordinates() {
            return coordinates;
        }

        boolean isHorizontal() {
            return horizontal;
        }

        int getSize() {
            return size;
        }
    }

    private List<StartSquare> startShipList;
    private String name;
    private String color;
    private boolean isComputer;

    Player(String name, String color, boolean isComputer) {
        this.name = name;
        this.color = color;
        this.isComputer = isComputer;
        this.startShipList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public void positionShip(int[] currentShipCoordinates) {
        int startX = currentShipCoordinates[0];
        int startY = currentShipCoordinates[1];
        int size = currentShipCoordinates[2];
        boolean horizontal = currentShipCoordinates[3] == 0;
        StartSquare startSquare = new StartSquare(startX, startY, horizontal, size);
        startShipList.add(startSquare);
    }

    public List<List<int[]>> getShipCoordinates() {
        List<List<int[]>> shipCoordinates = new ArrayList<>();
        for (StartSquare startSquare : startShipList) {
            int[] startCoordinates = startSquare.getCoordinates();
            List<int[]> coordinates = new ArrayList<>();
            coordinates.add(startCoordinates);
            int x = startCoordinates[0];
            int y = startCoordinates[1];
            if (startSquare.isHorizontal()) {
                switch (startSquare.getSize()) {
                    case 2: coordinates.add(new int[]{x + 1, y}); break;
                    case 3: coordinates.add(new int[]{x + 1, y}); coordinates.add(new int[]{x + 2, y}); break;
                    case 4: coordinates.add(new int[]{x + 1, y}); coordinates.add(new int[]{x + 2, y}); coordinates.add(new int[]{x + 3, y}); break;
                    case 5: coordinates.add(new int[]{x + 1, y}); coordinates.add(new int[]{x + 2, y}); coordinates.add(new int[]{x + 3, y}); coordinates.add(new int[]{x + 4, y}); break;
                }
            } else {
                switch (startSquare.getSize()) {
                    case 2: coordinates.add(new int[]{x, y + 1}); break;
                    case 3: coordinates.add(new int[]{x, y + 1}); coordinates.add(new int[]{x, y + 2}); break;
                    case 4: coordinates.add(new int[]{x, y + 1}); coordinates.add(new int[]{x, y + 2}); coordinates.add(new int[]{x, y + 3}); break;
                    case 5: coordinates.add(new int[]{x, y + 1}); coordinates.add(new int[]{x, y + 2}); coordinates.add(new int[]{x, y + 3}); coordinates.add(new int[]{x, y + 4}); break;
                }
            }
            shipCoordinates.add(coordinates);
        }
        return shipCoordinates;
    }

}
