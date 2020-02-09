package Battleship.model;

import java.util.List;
import java.util.Map;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020 15:50
 *
 * controls the behaviour of a player
 */

public abstract class Player {
    abstract Map<Ship, List<Square>> positionShips();
    abstract Square fireRocket();
    abstract String getName();
}
