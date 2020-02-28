package Battleship.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020 15:55
 *
 * a computer controlled player;
 *
 * TODO possible game extension
 */

public class ComputerPlayer extends Player {

    private String name;
    private String color;
    private List<StartSquare> startShipList;

    public ComputerPlayer(String name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() { return color; }

    @Override
    public List<StartSquare> getStartShipList() {
        return startShipList;
    }

    @Override
    public void positionShip(int[] currentShipCoordinates) {

    }

    @Override
    public Square fireRocket() {
        return null;
    }
}
