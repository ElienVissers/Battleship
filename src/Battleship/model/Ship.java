package Battleship.model;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020 15:08
 *
 * controls the characteristics of the possible ships: ships can span 2 to 5 cells on the grid
 */

public enum Ship {
    STARFIGHTER(2), STARDISCOVERER(3), STARDESTROYER(4), STARCRUISER(5);

    private int size;

    Ship(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name().substring(0, 1) + name().substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        String name = name().substring(0, 1) + name().substring(1).toLowerCase();
        return name + " spans over " + size + " cells on the grid.";
    }

    /*
        TODO
         Start-Cell geven, op basis daarvan de andere coordinaten laten "berekenen" ?
         90 degrees rotate, Start-Cell as basepoint (= rear end of ship) ?
     */
}
