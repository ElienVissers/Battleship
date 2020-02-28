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
    private List<StartSquare> startShipList;

    public HumanPlayer(String name, String color) {
        this.name = name;
        this.color = color;
        this.startShipList = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() { return color; }

    public List<StartSquare> getStartShipList() {
        return startShipList;
    }

    @Override
    public void positionShip(int[] currentShipCoordinates) {
        //updates startShipList
        int startX = currentShipCoordinates[0];
        int startY = currentShipCoordinates[1];
        int size = currentShipCoordinates[2];
        boolean horizontal = currentShipCoordinates[3] == 0;
        StartSquare startSquare = new StartSquare(startX, startY, horizontal, size);
        startShipList.add(startSquare);
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
