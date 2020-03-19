package Battleship.model;

/**
 * The custom unchecked runtime exception of the application.
 *
 * @author Elien Vissers-Similon
 * @version 1.0 08.03.2020
 */
public class BattleshipException extends RuntimeException {
        BattleshipException(Throwable cause) {
            super(cause);
        }
}
