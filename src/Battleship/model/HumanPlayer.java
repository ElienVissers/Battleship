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
    private String color;
    private Map<Ship, StartSquare> startShipMap;

    public HumanPlayer(String name, String color) {
        this.name = name;
        this.color = color;
        this.startShipMap = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() { return color; }

    public Map<Ship, StartSquare> getStartShipMap() {
        return startShipMap;
    }

    @Override
    public void positionShip(int[] currentShipCoordinates) {
        //updates the shipMap
        Ship currentShip = null;
        switch (currentShipCoordinates[2]) {
            case 2: currentShip = Ship.STARFIGHTER; break;
            case 3: currentShip = Ship.STARDISCOVERER; break;
            case 4: currentShip = Ship.STARDESTROYER; break;
            case 5: currentShip = Ship.STARCRUISER; break;
        }
        int startX = currentShipCoordinates[0];
        int startY = currentShipCoordinates[1];
        boolean horizontal;
        if (currentShipCoordinates[3] == 0) {
            horizontal = true;
        } else {
            horizontal = false;
        }
        StartSquare startSquare = new StartSquare(startX, startY);
        startSquare.setHorizontal(horizontal);
        startShipMap.put(currentShip, startSquare);
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
//        Scanner keyboard = new Scanner(System.in);
//        System.out.println("Which cell do you want to target?");
//        String test = keyboard.next();
//        Square testSquare = new Square(test);
//        System.out.println(name + " fires a rocket on " + testSquare.getCoordinates() + ".");
//        return testSquare;
        return null;
    }
}
