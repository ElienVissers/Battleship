package Battleship.view.preparegamescreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import Battleship.view.toggleplayerscreen.TogglePlayerScreenPresenter;
import Battleship.view.toggleplayerscreen.TogglePlayerScreenView;

import javafx.event.ActionEvent;
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

import java.util.Arrays;
import java.util.List;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 15.02.2020 17:54
 */

public class PrepareGameScreenPresenter {

    private BattleshipModel model;
    private PrepareGameScreenView view;
    private UISettings uiSettings;

    private int[] counters;
    private Label selectedLabel;
    private Label[] shipLabels;
    private boolean horizontal = true;
    private int[] currentShipCoordinates;
    private List<List<int[]>> shipCoordinatesList;

    public PrepareGameScreenPresenter(BattleshipModel model, PrepareGameScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;

        this.counters = new int[4];
        this.selectedLabel = new Label();
        this.shipLabels = new Label[4];
        this.shipLabels[0] = view.getShipLabel2();
        this.shipLabels[1] = view.getShipLabel3();
        this.shipLabels[2] = view.getShipLabel4();
        this.shipLabels[3] = view.getShipLabel5();
        this.currentShipCoordinates = new int[4];

        setActivePlayerName();
        loadCounters();
        createGridPane(view.getGrid());
        updateView();
        addEventHandlers();
    }

    private void addEventHandlers() {
        for (Label shipLabel : shipLabels) {
            addSelectShipHandler(shipLabel);
        }
        addCellHoverHandler();
        addCellClickHandler();
        addShipDirectionHandler();
    }

    private void updateView() {
        setShipLabels(counters);
        shipCoordinatesList = model.getActivePlayer().getShipCoordinates();
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
        view.getActivePlayerLabel().setText("Prepare you fleet for battle, " + model.getActivePlayer().getName() + "!");
        view.getActivePlayerLabel().getStyleClass().add("title");
        if (model.getActivePlayer().getColor().equals("red")) {
            view.getActivePlayerLabel().getStyleClass().add("red-text");
        } else if (model.getActivePlayer().getColor().equals("blue")) {
            view.getActivePlayerLabel().getStyleClass().add("blue-text");
        }
    }

    private void createGridPane(GridPane grid) {
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
            if (model.getActivePlayer().getColor().equals("red")) {
                letterText.getStyleClass().addAll("red-text", "grid-legend");
                numberText.getStyleClass().addAll("red-text", "grid-legend");
            } else if (model.getActivePlayer().getColor().equals("blue")) {
                letterText.getStyleClass().addAll("blue-text", "grid-legend");
                numberText.getStyleClass().addAll("blue-text", "grid-legend");
            }
            grid.add(letterText, i, 0);
            grid.add(numberText, 0, i);
            letter++;
        }
        grid.getStyleClass().add("grid-pane");
    }

    private boolean isHorizontal() {
        return horizontal;
    }

    private void loadCounters() {
        for (BattleshipModel.Ship ship : model.getShips()) {
            switch (ship.getSize()) {
                case 2: counters[0]++; break;
                case 3: counters[1]++; break;
                case 4: counters[2]++; break;
                case 5: counters[3]++; break;
            }
        }
    }

    private void setShipLabels(int[] counters) {
        Image ship2 = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_2.png", 100, 50, true, true);
        Image ship3 = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_3.png", 150, 50, true, true);
        Image ship4 = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_4.png", 200, 50, true, true);
        Image ship5 = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_5.png", 250, 50, true, true);
        view.getShipLabel2().setGraphic(new ImageView(ship2));
        view.getShipLabel3().setGraphic(new ImageView(ship3));
        view.getShipLabel4().setGraphic(new ImageView(ship4));
        view.getShipLabel5().setGraphic(new ImageView(ship5));
        view.getShipLabel2().setText(BattleshipModel.Ship.TWO.getName() + " (" + counters[0] + ")\t\t");
        view.getShipLabel3().setText(BattleshipModel.Ship.THREE.getName() + " (" + counters[1] + ")\t");
        view.getShipLabel4().setText(BattleshipModel.Ship.FOUR.getName() + " (" + counters[2] + ")\t\t");
        view.getShipLabel5().setText(BattleshipModel.Ship.FIVE.getName() + " (" + counters[3] + ")\t\t");
        for (int i = 0; i < counters.length; i++) {
            if (counters[i] == 0) {
                switch (i) {
                    case 0: view.getShipLabel2().getStyleClass().remove("ship-label"); view.getShipLabel2().getStyleClass().add("ship-label-empty"); break;
                    case 1: view.getShipLabel3().getStyleClass().remove("ship-label"); view.getShipLabel3().getStyleClass().add("ship-label-empty"); break;
                    case 2: view.getShipLabel4().getStyleClass().remove("ship-label"); view.getShipLabel4().getStyleClass().add("ship-label-empty"); break;
                    case 3: view.getShipLabel5().getStyleClass().remove("ship-label"); view.getShipLabel5().getStyleClass().add("ship-label-empty"); break;
                }
            }
        }
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

    private void addShipDirectionHandler() {
        view.getRotateButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                horizontal = !horizontal;
                if (horizontal) {
                    view.getRotateButton().setText("HORIZONTAL");
                } else {
                    view.getRotateButton().setText("VERTICAL");
                }
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
                        if (getSelectedShipSize() == 0 || counters[getSelectedShipSize() -2 ] == 0) {
                            t.consume();
                        } else {
                            highlightShipSize(getSelectedShipSize(), isHorizontal(), columnIndex, rowIndex, targetColumnIndex, targetRowIndex, n);
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

    private void addCellClickHandler() {
        for (Node targetNode : view.getGrid().getChildren()) {
            targetNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    if (getSelectedShipSize() == 0 || counters[getSelectedShipSize() -2 ] == 0 || currentShipCoordinates[0] == -1) {
                        t.consume();
                    } else {
                        model.getActivePlayer().positionShip(currentShipCoordinates);
                        counters[currentShipCoordinates[2] - 2]--;
                        addShipToGrid();
                        int done = 0;
                        for (int counter : counters) {
                            if (counter == 0) done++;
                        }
                        if (done == 4) {
                            view.getDoneButton().getStyleClass().remove("button-disabled");
                            view.getDoneButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent t) {
                                    model.prepareBoard();
                                    TogglePlayerScreenView togglePlayerScreenView = new TogglePlayerScreenView(uiSettings);
                                    TogglePlayerScreenPresenter togglePlayerScreenPresenter = new TogglePlayerScreenPresenter(model, togglePlayerScreenView, uiSettings);
                                    view.getScene().setRoot(togglePlayerScreenView);
                                    togglePlayerScreenPresenter.windowsHandler();
                                }
                            });
                        }
                        //disable a second click and remove highlights:
                        Arrays.fill(currentShipCoordinates, -1);
                        for (Node n : view.getGrid().getChildren()) {
                            n.getStyleClass().remove("grid-pane-selected");
                        }
                        updateView();
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

    private void addShipToGrid() {
        Image shipImage = null;
        if (horizontal) {
            switch (currentShipCoordinates[2]) {
                case 2: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_2.png", 100, 50, true, true); break;
                case 3: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_3.png", 150, 50, true, true); break;
                case 4: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_4.png", 200, 50, true, true); break;
                case 5: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_5.png", 250, 50, true, true); break;
            }
        } else {
            switch (currentShipCoordinates[2]) {
                case 2: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_2_vert.png", 50, 100, true, true); break;
                case 3: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_3_vert.png", 50, 150, true, true); break;
                case 4: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_4_vert.png", 50, 200, true, true); break;
                case 5: shipImage = new Image("/images/ship_" + model.getActivePlayer().getColor() + "_5_vert.png", 50, 250, true, true); break;
            }
        }
        ImageView shipView = new ImageView(shipImage);
        int rowSpan;
        int colSpan;
        if (horizontal) {
            rowSpan = 1;
            colSpan = currentShipCoordinates[2];
        } else {
            rowSpan = currentShipCoordinates[2];
            colSpan = 1;
        }
        view.getGrid().add(shipView, currentShipCoordinates[0], currentShipCoordinates[1], colSpan, rowSpan);
    }

    private void highlightShipSize(int shipSize, boolean horizontal, int columnIndex, int rowIndex, int targetColumnIndex, int targetRowIndex, Node n) {
        if (targetColumnIndex > 0 && targetRowIndex > 0) {
            switch (shipSize) {
                case 2:
                    if (horizontal && targetColumnIndex <= (model.getGridSize() - 1)) {
                        if (rowIndex == targetRowIndex &&
                                (columnIndex == targetColumnIndex || columnIndex == (targetColumnIndex + 1))) {
                            for (List<int[]> aShipCoordinateList : shipCoordinatesList) {
                                for (int[] aCoordinate : aShipCoordinateList) {
                                    int x = aCoordinate[0];
                                    int y = aCoordinate[1];
                                    if ((x == targetColumnIndex && y == targetRowIndex)
                                            || (x == (targetColumnIndex + 1) && y == targetRowIndex)) {
                                        Arrays.fill(currentShipCoordinates, -1);
                                        return;
                                    }
                                }
                            }
                            n.getStyleClass().add("grid-pane-selected");
                        }
                        currentShipCoordinates[3] = 0;
                    } else if (!horizontal && targetRowIndex <= (model.getGridSize() - 1)) {
                        if (columnIndex == targetColumnIndex &&
                                (rowIndex == targetRowIndex || rowIndex == (targetRowIndex + 1))) {
                            for (List<int[]> aShipCoordinateList : shipCoordinatesList) {
                                for (int[] aCoordinate : aShipCoordinateList) {
                                    int x = aCoordinate[0];
                                    int y = aCoordinate[1];
                                    if ((x == targetColumnIndex && y == targetRowIndex)
                                            || (x == targetColumnIndex && y == (targetRowIndex + 1))) {
                                        Arrays.fill(currentShipCoordinates, -1);
                                        return;
                                    }
                                }
                            }
                            n.getStyleClass().add("grid-pane-selected");
                        }
                        currentShipCoordinates[3] = 1;
                    } else {
                        Arrays.fill(currentShipCoordinates, -1);
                        return;
                    }
                    currentShipCoordinates[2] = 2;
                    break;
                case 3:
                    if (horizontal && targetColumnIndex <= (model.getGridSize() - 2)) {
                        if (rowIndex == targetRowIndex &&
                                (columnIndex == targetColumnIndex || columnIndex == (targetColumnIndex + 1) || columnIndex == (targetColumnIndex + 2))) {
                            for (List<int[]> aShipCoordinateList : shipCoordinatesList) {
                                for (int[] aCoordinate : aShipCoordinateList) {
                                    int x = aCoordinate[0];
                                    int y = aCoordinate[1];
                                    if ((x == targetColumnIndex && y == targetRowIndex)
                                            || (x == (targetColumnIndex + 1) && y == targetRowIndex)
                                            || (x == (targetColumnIndex + 2) && y == targetRowIndex)) {
                                        Arrays.fill(currentShipCoordinates, -1);
                                        return;
                                    }
                                }
                            }
                            n.getStyleClass().add("grid-pane-selected");
                        }
                        currentShipCoordinates[3] = 0;
                    } else if (!horizontal && targetRowIndex <= (model.getGridSize() - 2)) {
                        if (columnIndex == targetColumnIndex &&
                                (rowIndex == targetRowIndex || rowIndex == (targetRowIndex + 1) || rowIndex == (targetRowIndex + 2))) {
                            for (List<int[]> aShipCoordinateList : shipCoordinatesList) {
                                for (int[] aCoordinate : aShipCoordinateList) {
                                    int x = aCoordinate[0];
                                    int y = aCoordinate[1];
                                    if ((x == targetColumnIndex && y == targetRowIndex)
                                            || (x == targetColumnIndex && y == (targetRowIndex + 1))
                                            || (x == targetColumnIndex && y == (targetRowIndex + 2))) {
                                        Arrays.fill(currentShipCoordinates, -1);
                                        return;
                                    }
                                }
                            }
                            n.getStyleClass().add("grid-pane-selected");
                        }
                        currentShipCoordinates[3] = 1;
                    } else {
                        Arrays.fill(currentShipCoordinates, -1);
                        return;
                    }
                    currentShipCoordinates[2] = 3;
                    break;
                case 4:
                    if (horizontal && targetColumnIndex <= (model.getGridSize() - 3)) {
                        if (rowIndex == targetRowIndex &&
                                (columnIndex == targetColumnIndex || columnIndex == (targetColumnIndex + 1) || columnIndex == (targetColumnIndex + 2) || columnIndex == (targetColumnIndex + 3))) {
                            for (List<int[]> aShipCoordinateList : shipCoordinatesList) {
                                for (int[] aCoordinate : aShipCoordinateList) {
                                    int x = aCoordinate[0];
                                    int y = aCoordinate[1];
                                    if ((x == targetColumnIndex && y == targetRowIndex)
                                            || (x == (targetColumnIndex + 1) && y == targetRowIndex)
                                            || (x == (targetColumnIndex + 2) && y == targetRowIndex)
                                            || (x == (targetColumnIndex + 3) && y == targetRowIndex)) {
                                        Arrays.fill(currentShipCoordinates, -1);
                                        return;
                                    }
                                }
                            }
                            n.getStyleClass().add("grid-pane-selected");
                        }
                        currentShipCoordinates[3] = 0;
                    } else if (!horizontal && targetRowIndex <= (model.getGridSize() - 3)) {
                        if (columnIndex == targetColumnIndex &&
                                (rowIndex == targetRowIndex || rowIndex == (targetRowIndex + 1) || rowIndex == (targetRowIndex + 2) || rowIndex == (targetRowIndex + 3))) {
                            for (List<int[]> aShipCoordinateList : shipCoordinatesList) {
                                for (int[] aCoordinate : aShipCoordinateList) {
                                    int x = aCoordinate[0];
                                    int y = aCoordinate[1];
                                    if ((x == targetColumnIndex && y == targetRowIndex)
                                            || (x == targetColumnIndex && y == (targetRowIndex + 1))
                                            || (x == targetColumnIndex && y == (targetRowIndex + 2))
                                            || (x == targetColumnIndex && y == (targetRowIndex + 3))) {
                                        Arrays.fill(currentShipCoordinates, -1);
                                        return;
                                    }
                                }
                            }
                            n.getStyleClass().add("grid-pane-selected");
                        }
                        currentShipCoordinates[3] = 1;
                    } else {
                        Arrays.fill(currentShipCoordinates, -1);
                        return;
                    }
                    currentShipCoordinates[2] = 4;
                    break;
                case 5:
                    if (horizontal && targetColumnIndex <= (model.getGridSize() - 4)) {
                        if (rowIndex == targetRowIndex &&
                                (columnIndex == targetColumnIndex || columnIndex == (targetColumnIndex + 1) || columnIndex == (targetColumnIndex + 2) || columnIndex == (targetColumnIndex + 3) || columnIndex == (targetColumnIndex + 4))) {
                            for (List<int[]> aShipCoordinateList : shipCoordinatesList) {
                                for (int[] aCoordinate : aShipCoordinateList) {
                                    int x = aCoordinate[0];
                                    int y = aCoordinate[1];
                                    if ((x == targetColumnIndex && y == targetRowIndex)
                                            || (x == (targetColumnIndex + 1) && y == targetRowIndex)
                                            || (x == (targetColumnIndex + 2) && y == targetRowIndex)
                                            || (x == (targetColumnIndex + 3) && y == targetRowIndex)
                                            || (x == (targetColumnIndex + 4) && y == targetRowIndex)) {
                                        Arrays.fill(currentShipCoordinates, -1);
                                        return;
                                    }
                                }
                            }
                            n.getStyleClass().add("grid-pane-selected");
                        }
                        currentShipCoordinates[3] = 0;
                    } else if (!horizontal && targetRowIndex <= (model.getGridSize() - 4)) {
                        if (columnIndex == targetColumnIndex &&
                                (rowIndex == targetRowIndex || rowIndex == (targetRowIndex + 1) || rowIndex == (targetRowIndex + 2) || rowIndex == (targetRowIndex + 3) || rowIndex == (targetRowIndex + 4))) {
                            for (List<int[]> aShipCoordinateList : shipCoordinatesList) {
                                for (int[] aCoordinate : aShipCoordinateList) {
                                    int x = aCoordinate[0];
                                    int y = aCoordinate[1];
                                    if ((x == targetColumnIndex && y == targetRowIndex)
                                            || (x == targetColumnIndex && y == (targetRowIndex + 1))
                                            || (x == targetColumnIndex && y == (targetRowIndex + 2))
                                            || (x == targetColumnIndex && y == (targetRowIndex + 3))
                                            || (x == targetColumnIndex && y == (targetRowIndex + 4))) {
                                        Arrays.fill(currentShipCoordinates, -1);
                                        return;
                                    }
                                }
                            }
                            n.getStyleClass().add("grid-pane-selected");
                        }
                        currentShipCoordinates[3] = 1;
                    } else {
                        Arrays.fill(currentShipCoordinates, -1);
                        return;
                    }
                    currentShipCoordinates[2] = 5;
                    break;
            }
            currentShipCoordinates[0] = targetColumnIndex;
            currentShipCoordinates[1] = targetRowIndex;
        }
    }
}