package Battleship.model;

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
    private List<Ship> ships;

    public ComputerPlayer(String name, List<Ship> ships) {
        this.name = name;
        this.ships = ships;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<Ship, List<Square>> positionShips() {
        return null;
    }

    @Override
    public Square fireRocket() {
        return null;
    }
}
