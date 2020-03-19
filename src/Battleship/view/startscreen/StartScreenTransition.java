package Battleship.view.startscreen;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.util.Duration;

/**
 * A transition-class to set up the loading bar at the StartScreen.
 *
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020
 */
public class StartScreenTransition extends Transition {

    private final StartScreenView view;

    StartScreenTransition(StartScreenView view, int maxDuration) {
        this.view = view;
        this.setCycleDuration(Duration.seconds(maxDuration));
        this.setCycleCount(1);
        this.setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double frac) {
        this.view.getTimeDisplay().setText(String.format("Loading: %.1f", frac * 100));
        this.view.getTimeProgress().setProgress(frac);
    }
}
