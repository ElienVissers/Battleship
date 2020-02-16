package Battleship.view.preparegamescreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 15.02.2020 17:54
 */
public class PrepareGameScreenPresenter {

    private BattleshipModel model;
    private PrepareGameScreenView view;
    private UISettings uiSettings;

    private Label selectedLabel;

    public PrepareGameScreenPresenter(BattleshipModel model, PrepareGameScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        updateView();
        EventHandlers();
    }

    private void updateView() {
        setActivePlayerName();
        setShipLabels(loadCounters());

        //TODO how to update?? important for model? actually not! --> IF SHIP = POSITIONED, EVENT?
        //updateAmounts(counter2, counter3, counter4, counter5);
    }

    private void EventHandlers() {
        addHoverHandlers(view.getShipLabel2());
        addHoverHandlers(view.getShipLabel3());
        addHoverHandlers(view.getShipLabel4());
        addHoverHandlers(view.getShipLabel5());
        addClickHandler(view.getShipLabel2());
        addClickHandler(view.getShipLabel3());
        addClickHandler(view.getShipLabel4());
        addClickHandler(view.getShipLabel5());
    }

    public void windowsHandlers() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { UISettings.getCloseAlert(event, view.getScene()); }});
    }

    private void setActivePlayerName() {
        view.getActivePlayerLabel().setText("Prepare you fleet for battle, " + model.getActivePlayerName());
        if (model.getActivePlayerColor().equals("red")) {
            view.getActivePlayerLabel().setStyle("-fx-text-fill: #983C32; -fx-font-size: 20;");
        } else if (model.getActivePlayerColor().equals("blue")) {
            view.getActivePlayerLabel().setStyle("-fx-text-fill: #36589B; -fx-font-size: 20;");
        }
    }

    private int[] loadCounters() {
        int[] counters = new int[4];
        for (int number: model.getAvailableShips()) {
            switch (number) {
                case 2: counters[0]++; break;
                case 3: counters[1]++; break;
                case 4: counters[2]++; break;
                case 5: counters[3]++; break;
            }
        }
        return counters;
    }

    private void setShipLabels(int[] counters) {
        Image ship2 = new Image("/images/ship_" + model.getActivePlayerColor() + "_2.png", 0, 65, true, true);
        Image ship3 = new Image("/images/ship_" + model.getActivePlayerColor() + "_3.png", 0, 65, true, true);
        Image ship4 = new Image("/images/ship_" + model.getActivePlayerColor() + "_4.png", 0, 65, true, true);
        Image ship5 = new Image("/images/ship_" + model.getActivePlayerColor() + "_5.png", 0, 65, true, true);
        view.getShipLabel2().setGraphic(new ImageView(ship2));
        view.getShipLabel3().setGraphic(new ImageView(ship3));
        view.getShipLabel4().setGraphic(new ImageView(ship4));
        view.getShipLabel5().setGraphic(new ImageView(ship5));
        view.getShipLabel2().setText("starfighter (" + counters[0] + ")\t\t");
        view.getShipLabel3().setText("stardiscoverer (" + counters[1] + ")\t");
        view.getShipLabel4().setText("stardestroyer (" + counters[2] + ")\t");
        view.getShipLabel5().setText("starcruiser (" + counters[3] + ")\t\t");
    }

    /*
    private void updateAmounts(int[] counters) {
        view.getShipLabel2().setText("starfighter (" + counters[0] + ")");
        view.getShipLabel3().setText("stardiscoverer (" + counters[1] + ")");
        view.getShipLabel4().setText("stardestroyer (" + counters[2] + ")");
        view.getShipLabel5().setText("starcruiser (" + counters[3] + ")");
    }
    */

    private void addHoverHandlers(Label label) {
        label.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (label.equals(selectedLabel)) {
                    t.consume();
                } else {
                    label.setStyle("-fx-background-color:darkgrey;");
                }
            }
        });
        label.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (label.equals(selectedLabel)) {
                    t.consume();
                } else {
                    label.setStyle("-fx-background-color:#2D2D2D;");
                }
            }
        });
    }

    private void addClickHandler(Label label) {
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                selectedLabel = label;
                view.getShipLabel2().setStyle("-fx-background-color:#2D2D2D;");
                view.getShipLabel3().setStyle("-fx-background-color:#2D2D2D;");
                view.getShipLabel4().setStyle("-fx-background-color:#2D2D2D;");
                view.getShipLabel5().setStyle("-fx-background-color:#2D2D2D;");
                label.setStyle("-fx-background-color:darkgrey;");
            }
        });
    }

}
