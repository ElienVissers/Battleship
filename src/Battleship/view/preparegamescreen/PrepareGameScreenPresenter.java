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
    private Label[] shipLabels;
    private boolean direction = true;

    public PrepareGameScreenPresenter(BattleshipModel model, PrepareGameScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;

        this.selectedLabel = new Label();
        this.shipLabels = new Label[4];
        this.shipLabels[0] = view.getShipLabel2();
        this.shipLabels[1] = view.getShipLabel3();
        this.shipLabels[2] = view.getShipLabel4();
        this.shipLabels[3] = view.getShipLabel5();

        updateView();
        EventHandlers();
    }

    private void updateView() {
        setActivePlayerName();
        setShipLabels(loadCounters());
        createGridPane(view.getGrid());
        //load the ships from getActivePlayer getShipMap en zet ze op het gridpane!
        //set a dummy spaceship
        setDummyShip();
    }

    //TODO remove later
    private void setDummyShip() {
        ImageView ship = new ImageView(new Image("/images/ship_red_2.png"));
        ship.setFitHeight(50);
        ship.setFitWidth(100);
        view.getGrid().add(ship, 5, 5, 2, 1);
    }


    private void EventHandlers() {
        for (Label shipLabel : shipLabels) {
            addSelectShipHandler(shipLabel);
        }
        addCellHoverHandler();
    }

    public void windowsHandlers() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                UISettings.getCloseAlert(event, view.getScene());
            }
        });
    }

    private void setActivePlayerName() {
        view.getActivePlayerLabel().setText("Prepare you fleet for battle, " + model.getActivePlayer().getName());
        view.getActivePlayerLabel().getStyleClass().add("title");
        if (model.getActivePlayer().getColor().equals("red")) {
            view.getActivePlayerLabel().getStyleClass().add("red-text");
        } else if (model.getActivePlayer().getColor().equals("blue")) {
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
                empty.setPrefSize(50, 50);
                grid.add(empty, i, j);
            }
        }
        grid.getStyleClass().add("grid-pane");
    }

    private int[] loadCounters() {
        int[] counters = new int[4];
        for (int number : model.getAvailableShips()) {
            switch (number) {
                case 2:
                    counters[0]++;
                    break;
                case 3:
                    counters[1]++;
                    break;
                case 4:
                    counters[2]++;
                    break;
                case 5:
                    counters[3]++;
                    break;
            }
        }
        return counters;
    }

    private void setShipLabels(int[] counters) {
        Image ship2 = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_2.png", 0, 65, true, true);
        Image ship3 = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_3.png", 0, 65, true, true);
        Image ship4 = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_4.png", 0, 65, true, true);
        Image ship5 = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_5.png", 0, 65, true, true);
        view.getShipLabel2().setGraphic(new ImageView(ship2));
        view.getShipLabel3().setGraphic(new ImageView(ship3));
        view.getShipLabel4().setGraphic(new ImageView(ship4));
        view.getShipLabel5().setGraphic(new ImageView(ship5));
        view.getShipLabel2().setText("starfighter (" + counters[0] + ")\t\t");
        view.getShipLabel3().setText("stardiscoverer (" + counters[1] + ")\t");
        view.getShipLabel4().setText("stardestroyer (" + counters[2] + ")\t");
        view.getShipLabel5().setText("starcruiser (" + counters[3] + ")\t\t");
    }

    private void addSelectShipHandler(Label label) {
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                selectedLabel = label;
                for (Label shipLabel : shipLabels) {
                    shipLabel.getStyleClass().remove("ship-label-selected");
                }
                label.getStyleClass().add("ship-label-selected");
            }
        });
    }

    private void addCellHoverHandler() {
        for (Node targetNode : view.getGrid().getChildren()) {
            Integer targetColumnIndex = GridPane.getColumnIndex(targetNode);
            Integer targetRowIndex = GridPane.getRowIndex(targetNode);
            targetNode.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    for (Node n : view.getGrid().getChildren()) {
                        Integer columnIndex = GridPane.getColumnIndex(n);
                        Integer rowIndex = GridPane.getRowIndex(n);
                        if (getSelectedShipSize() == 0) {
                            t.consume();
                        }
                        highlightShipSize(getSelectedShipSize(), getDirection(), columnIndex, rowIndex, targetColumnIndex, targetRowIndex, n);
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

    private int getSelectedShipSize() {
        int selectedSize = 0;
        if (selectedLabel.equals(view.getShipLabel2())) {
            selectedSize = 2;
        } else if  (selectedLabel.equals(view.getShipLabel3())) {
            selectedSize = 3;
        } else if  (selectedLabel.equals(view.getShipLabel4())) {
            selectedSize = 4;
        } else if  (selectedLabel.equals(view.getShipLabel5())) {
            selectedSize = 5;
        }
        return selectedSize;
    }

    private boolean getDirection() {
        return direction;
    }

    private void highlightShipSize(int shipSize, boolean horizontal, int columnIndex, int rowIndex, int targetColumnIndex, int targetRowIndex, Node n) {
        switch (shipSize) {
            case 2:
                if (horizontal && targetColumnIndex <= (model.getGridSize() - 2)) {
                    if (rowIndex == targetRowIndex &&
                            (columnIndex == targetColumnIndex || columnIndex == (targetColumnIndex + 1))) {
                        n.getStyleClass().add("grid-pane-selected");
                    }
                } else if (!horizontal && targetRowIndex <= (model.getGridSize() - 2)) {
                    if (columnIndex == targetColumnIndex &&
                            (rowIndex == targetRowIndex || rowIndex == (targetRowIndex + 1))) {
                        n.getStyleClass().add("grid-pane-selected");
                    }
                }
                break;
            case 3:
                if (horizontal && targetColumnIndex <= (model.getGridSize() - 3)) {
                    if (rowIndex == targetRowIndex &&
                            (columnIndex == targetColumnIndex || columnIndex == (targetColumnIndex + 1) || columnIndex == (targetColumnIndex + 2))) {
                        n.getStyleClass().add("grid-pane-selected");
                    }
                } else if (!horizontal && targetRowIndex <= (model.getGridSize() - 3)) {
                    if (columnIndex == targetColumnIndex &&
                            (rowIndex == targetRowIndex || rowIndex == (targetRowIndex + 1) || rowIndex == (targetRowIndex + 2))) {
                        n.getStyleClass().add("grid-pane-selected");
                    }
                }
                break;
            case 4:
                if (horizontal && targetColumnIndex <= (model.getGridSize() - 4)) {
                    if (rowIndex == targetRowIndex &&
                            (columnIndex == targetColumnIndex || columnIndex == (targetColumnIndex + 1) || columnIndex == (targetColumnIndex + 2) || columnIndex == (targetColumnIndex + 3))) {
                        n.getStyleClass().add("grid-pane-selected");
                    }
                } else if (!horizontal && targetRowIndex <= (model.getGridSize() - 4)) {
                    if (columnIndex == targetColumnIndex &&
                            (rowIndex == targetRowIndex || rowIndex == (targetRowIndex + 1) || rowIndex == (targetRowIndex + 2) || rowIndex == (targetRowIndex + 3))) {
                        n.getStyleClass().add("grid-pane-selected");
                    }
                }
                break;
            case 5:
                if (horizontal && targetColumnIndex <= (model.getGridSize() - 5)) {
                    if (rowIndex == targetRowIndex &&
                            (columnIndex == targetColumnIndex || columnIndex == (targetColumnIndex + 1) || columnIndex == (targetColumnIndex + 2) || columnIndex == (targetColumnIndex + 3) || columnIndex == (targetColumnIndex + 4))) {
                        n.getStyleClass().add("grid-pane-selected");
                    }
                } else if (!horizontal && targetRowIndex <= (model.getGridSize() - 5)) {
                    if (columnIndex == targetColumnIndex &&
                            (rowIndex == targetRowIndex || rowIndex == (targetRowIndex + 1) || rowIndex == (targetRowIndex + 2) || rowIndex == (targetRowIndex + 3) || rowIndex == (targetRowIndex + 4))) {
                        n.getStyleClass().add("grid-pane-selected");
                    }
                }
                break;
        }
    }

    //TODO when clicking in a cell --> updateView() wordt aangeroepen in de eventhandler (zie Mastermind voorbeeld)
    private void positionShip(/*coordinates from GridPane*/) {
        //adds or changes a ship in the shipMap of the activePlayer
        model.getActivePlayer().positionShip(/*coordinates from gridpane*/);
        //updateAmounts(counter2, counter3, counter4, counter5);
        /*
        private void updateAmounts(int[] counters) {
            view.getShipLabel2().setText("starfighter (" + counters[0] + ")");
            view.getShipLabel3().setText("stardiscoverer (" + counters[1] + ")");
            view.getShipLabel4().setText("stardestroyer (" + counters[2] + ")");
            view.getShipLabel5().setText("starcruiser (" + counters[3] + ")");
        }
        */
    }

    //TODO when pressing "DONE" button
    private void prepareBoard() {
        //updates the GameBoard of the activePlayer
        model.getActivePlayerBoard().addShips(model.getActivePlayer().getShipMap());
        //continues to a new screen!!
    }
}