package Battleship.view.gamescreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.WindowEvent;

import java.util.List;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 29.02.2020 10:19
 */

public class GameScreenPresenter {

    private BattleshipModel model;
    private GameScreenView view;
    private UISettings uiSettings;

    private int[] currentTargetCoordinates;
    private List<List<int[]>> activeShipCoordinatesList;

    public GameScreenPresenter(BattleshipModel model, GameScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;

        this.currentTargetCoordinates = new int[2];

        createGridPane(view.getFirstGrid(), "red");
        createGridPane(view.getSecondGrid(), "blue");
        view.getFirstSunkenStatsLabel().setText("sunken ships: 0/" + model.getNUMBER_OF_SHIPS());
        view.getSecondSunkenStatsLabel().setText("sunken ships: 0/" + model.getNUMBER_OF_SHIPS());

        updateView();
        addEventHandlers();
    }

    private void addEventHandlers() {
        addCellHoverHandler();
        addCellClickHandler();
    }

    private void updateView() {
        activeShipCoordinatesList = model.getActivePlayer().getShipCoordinates();
        setActivePlayerName();
        setSunkenShips(view.getFirstGrid());
        setSunkenShips(view.getSecondGrid());
        loadStats();
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
        if (model.getActivePlayer().getColor().equals("red")) {
            view.getFirstPlayerLabel().setText("Select your target, " + model.getActivePlayer().getName() + "!");
            view.getSecondPlayerLabel().setText("");
        } else if (model.getActivePlayer().getColor().equals("blue")) {
            view.getFirstPlayerLabel().setText("");
            view.getSecondPlayerLabel().setText("Select your target, " + model.getActivePlayer().getName() + "!");
        }
    }

    private void createGridPane(GridPane grid, String color) {
        int gridSize = model.getGridSize();
        for (int i = 0; i < gridSize + 1; i++) {
            ColumnConstraints column = new ColumnConstraints(50);
            RowConstraints row = new RowConstraints(50);
            grid.getColumnConstraints().add(column);
            grid.getRowConstraints().add(row);
        }
        for (int i = 0; i < gridSize + 1; i++) {
            for (int j = 0; j < gridSize + 1; j++) {
                Label empty = new Label();
                empty.setPrefSize(50, 50);
                grid.add(empty, i, j);
                if (i == 0 && j == 0) {
                    empty.getStyleClass().add("grid-edge-corner");
                } else if (i == 0) {
                    empty.getStyleClass().add("grid-edge-left");
                } else if (j == 0) {
                    empty.getStyleClass().add("grid-edge-top");
                }
            }
        }
        char letter = 'A';
        for (int i = 1; i <= gridSize; i++) {
            Label letterText = new Label(Character.toString(letter));
            Label numberText = new Label(Integer.toString(i));
            if (color.equals("red")) {
                letterText.getStyleClass().addAll("red-text", "grid-legend");
                numberText.getStyleClass().addAll("red-text", "grid-legend");
            } else if (color.equals("blue")) {
                letterText.getStyleClass().addAll("blue-text", "grid-legend");
                numberText.getStyleClass().addAll("blue-text", "grid-legend");
            }
            grid.add(letterText, i, 0);
            grid.add(numberText, 0, i);
            letter++;
        }
        grid.getStyleClass().add("grid-pane");
    }

    private void loadStats() {
        if (model.getActivePlayer().getColor().equals("red")) {
            view.getSecondSunkenStatsLabel().setText("sunken ships: " + model.getPassiveSinkCounter() + "/" + model.getNUMBER_OF_SHIPS());
        } else if (model.getActivePlayer().getColor().equals("blue")) {
            view.getFirstSunkenStatsLabel().setText("sunken ships: " + model.getPassiveSinkCounter() + "/" + model.getNUMBER_OF_SHIPS());
        }

    }

    private void setSunkenShips(GridPane grid) {
        //addShip
        //position passive hits/misses/sunken ships
    }

    private void addCellHoverHandler() {
//        for (Node targetNode : view.getGrid().getChildren()) {
//            Integer targetColumnIndex = GridPane.getColumnIndex(targetNode);
//            Integer targetRowIndex = GridPane.getRowIndex(targetNode);
//            //TODO if cell has been targeted before OR if cell has not been targeted before
//            targetNode.setOnMouseEntered(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent t) {
//                    for (Node n : view.getGrid().getChildren()) {
//                        Integer columnIndex = GridPane.getColumnIndex(n);
//                        Integer rowIndex = GridPane.getRowIndex(n);
//                        if (getSelectedShipSize() == 0 || counters[getSelectedShipSize() -2 ] == 0) {
//                            t.consume();
//                        } else {
//                            highlightShipSize(getSelectedShipSize(), isHorizontal(), columnIndex, rowIndex, targetColumnIndex, targetRowIndex, n);
//                        }
//                    }
//                }
//            });
//            targetNode.setOnMouseExited(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent t) {
//                    for (Node n : view.getGrid().getChildren()) {
//                        n.getStyleClass().remove("grid-pane-selected");
//                    }
//                }
//            });
//        }
    }

    private void addCellClickHandler() {
//        for (Node targetNode : view.getEnemyGrid().getChildren()) {
//            targetNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent t) {
//                    if (/*targetNode was targeted before OR coordinates contain zero*/) {
//                        t.consume();
//                    } else {
//                        if (model.activePlayerPlays(targetNodeX, targetNodeY)[0] == 1) {
//                            //hit, mark flame on grid
//                            if (model.getPassiveBoard().hasSunken()) {
//                                showSunkenShip();
//                                if (isGameOver()) {
//                                    model.endGame();
//                                    //TODO create VictoryScreen + set a timeout of 5 seconds?
////                                    VictoryScreenView victoryScreenView = new VictoryScreenView(uiSettings);
////                                    VictoryScreenPresenter victoryScreenPresenter = new VictoryScreenPresenter(model, victoryScreenView, uiSettings);
////                                    view.getScene().setRoot(victoryScreenView);
////                                    victoryScreenPresenter.windowsHandler();
//                                }
//                            }
//                        } else {
//                            //miss, mark highlight on grid
//                        }
//                    }
//                    //disable a second click and remove highlights:
////                    Arrays.fill(currentCoordinates, -1);
////                    for (Node n : view.getGrid().getChildren()) {
////                        n.getStyleClass().remove("grid-pane-selected");
////                    }
//                    updateView();
                        //togglePlayer() als allerlaatste!! (na de updateView)
//                }
//            });
//        }
    }

    private void addShip() {
//        Image shipImage = null;
//        if (horizontal) {
//            switch (shipSize) {
//                case 2: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_2.png", 100, 50, true, true); break;
//                case 3: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_3.png", 150, 50, true, true); break;
//                case 4: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_4.png", 200, 50, true, true); break;
//                case 5: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_5.png", 250, 50, true, true); break;
//            }
//        } else {
//            switch (shipSize) {
//                case 2: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_2_vert.png", 50, 100, true, true); break;
//                case 3: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_3_vert.png", 50, 150, true, true); break;
//                case 4: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_4_vert.png", 50, 200, true, true); break;
//                case 5: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_5_vert.png", 50, 250, true, true); break;
//            }
//        }
//        ImageView shipView = new ImageView(shipImage);
//        int rowSpan;
//        int colSpan;
//        if (horizontal) {
//            rowSpan = 1;
//            colSpan = shipSize;
//        } else {
//            rowSpan = shipSize;
//            colSpan = 1;
//        }
//        view.getPassiveGrid().add(shipView, x, y, colSpan, rowSpan);
    }
}