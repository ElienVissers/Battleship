package Battleship.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
}
