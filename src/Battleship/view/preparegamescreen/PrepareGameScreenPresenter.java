package Battleship.view.preparegamescreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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
        createGridPane(view.getGrid());

        //set a dummy spaceship
        setDummyShip();

        //TODO how to update? important for model? actually not! --> IF SHIP = POSITIONED, EVENT?
        //updateAmounts(counter2, counter3, counter4, counter5);
    }

    //TODO remove later
    private void setDummyShip() {
        ImageView ship = new ImageView(new Image("/images/ship_red_2.png"));
        ship.setFitHeight(50);
        ship.setFitWidth(100);
        view.getGrid().add(ship, 5, 5, 2, 1);
    }


    private void EventHandlers() {
        addClickHandler(view.getShipLabel2());
        addClickHandler(view.getShipLabel3());
        addClickHandler(view.getShipLabel4());
        addClickHandler(view.getShipLabel5());
        addCellHoverHandler();
    }

    public void windowsHandlers() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { UISettings.getCloseAlert(event, view.getScene()); }});
    }

    private void setActivePlayerName() {
        view.getActivePlayerLabel().setText("Prepare you fleet for battle, " + model.getActivePlayerName());
        view.getActivePlayerLabel().getStyleClass().add("title");
        if (model.getActivePlayerColor().equals("red")) {
            view.getActivePlayerLabel().getStyleClass().add("red-text");
        } else if (model.getActivePlayerColor().equals("blue")) {
            view.getActivePlayerLabel().getStyleClass().add("blue-text");
        }
    }

    private void createGridPane(GridPane grid) {
        int gridSize = model.getGridSize();
        for (int i = 0; i < gridSize; i++) {
            ColumnConstraints column = new ColumnConstraints(50);
            RowConstraints row = new RowConstraints(50);
            grid.getColumnConstraints().add(column);
            grid.getRowConstraints().add(row);
        }
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Label empty = new Label();
                empty.setPrefSize(50,50);
                grid.add(empty, i, j);
            }
        }
        grid.getStyleClass().add("grid-pane");
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

    private void addClickHandler(Label label) {
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                selectedLabel = label;
                view.getShipLabel2().getStyleClass().remove("ship-label-selected");
                view.getShipLabel3().getStyleClass().remove("ship-label-selected");
                view.getShipLabel4().getStyleClass().remove("ship-label-selected");
                view.getShipLabel5().getStyleClass().remove("ship-label-selected");
                label.getStyleClass().add("ship-label-selected");
            }
        });
    }

    private void addCellHoverHandler() {
        for (Node targetNode : view.getGrid().getChildren()) {
            //ATTENTION: the gridlines are also a child... this sets a null value at the Integer targetIndex
            //SOLUTION: filter out the null values with additional if-clauses
            Integer targetColumnIndex = GridPane.getColumnIndex(targetNode);
            Integer targetRowIndex = GridPane.getRowIndex(targetNode);
            if (targetColumnIndex != null && targetRowIndex != null) {
                targetNode.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        for (Node n : view.getGrid().getChildren()) {
                            //TODO highlight based on shipsize
                            Integer columnIndex = GridPane.getColumnIndex(n);
                            Integer rowIndex = GridPane.getRowIndex(n);
                            if (columnIndex != null && rowIndex != null) {
                                if (columnIndex == targetColumnIndex && rowIndex == targetRowIndex) {
                                    n.getStyleClass().add("grid-pane-selected");
                                }
                            }
                        }
                    }
                });
                targetNode.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        for (Node n : view.getGrid().getChildren()) {
                            n.getStyleClass().remove("grid-pane-selected");
                        }
                    }
                });
            }
        }
    }

}
