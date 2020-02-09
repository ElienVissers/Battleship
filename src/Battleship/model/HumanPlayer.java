package Battleship.model;

import java.util.*;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020 15:54
 *
 * a player controlled by the GUI
 */

public class HumanPlayer extends Player {

    private String name;
    private List<Ship> ships;

    public HumanPlayer(String name, List<Ship> ships) {
        this.name = name;
        this.ships = ships;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<Ship, List<Square>> positionShips() {
        System.out.println(name + " positions ships.");

     /*
        TODO
         Map<Ship, List<Cell>> positionMap = new HashMap<>();
         for (Ship ship : ships) {
            --> separate method positionShip() [SINGULAR]
              ship.getSize();
              --> position a correctly sized ship on the map (graphically)
              --> this way, the coordinates of all cells are generated
              --> save the cells in an ArrayList coordinatesList
              positionMap.put(ship, coordinatesList)
         }
         return positionMap;
     */

        //FIXME replace testMap with the new positionMap!
        Map<Ship, List<Square>> testMap = new HashMap<>();
        List<Square> list = new ArrayList<>();
        list.add(new Square("A3"));
        list.add(new Square("A4"));
        testMap.put(Ship.ROWBOAT, list);
        return testMap;
    }

    @Override
    public Square fireRocket() {
        /*
        TODO
         --> create a GUI to ask the player for a cell on the enemy's grid
         Cell targetCell = ...;
         return targetCell;
        */

        //FIXME replace testCell with the new targetCell!
        /* TODO
            implement to uppercase for char
            Check that number or char isn't above max or min
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Which cell do you want to target?");
        String test = keyboard.next();
        Square testSquare = new Square(test);
        System.out.println(name + " fires a rocket on " + testSquare.getCoordinates() + ".");
        return testSquare;
    }
}
