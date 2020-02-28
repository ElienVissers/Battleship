package Battleship.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020 15:50
 *
 * controls the behaviour of a player
 */

public abstract class Player {
    public abstract void positionShip(int[] currentShipCoordinates);
    public abstract Square fireRocket();
    public abstract String getName();
    public abstract String getColor();

    public abstract List<StartSquare> getStartShipList();

    public List<List<int[]>> getShipCoordinates() {
        List<List<int[]>> shipCoordinates = new ArrayList<>();
        for (StartSquare startSquare : getStartShipList()) {
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
