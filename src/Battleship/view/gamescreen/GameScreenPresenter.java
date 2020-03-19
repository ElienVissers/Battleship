package Battleship.view.gamescreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import Battleship.view.victoryscreen.VictoryScreenPresenter;
import Battleship.view.victoryscreen.VictoryScreenView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.WindowEvent;
import javafx.scene.Node;
import javafx.util.Duration;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Presenter class of the GameScreen.
 *
 * @author Elien Vissers-Similon
 * @version 1.0 29.02.2020 10:19
 */

public class GameScreenPresenter {

    private BattleshipModel model;
    private GameScreenView view;
    private UISettings uiSettings;

    private MediaPlayer mediaPlayer;

    private int[] currentTargetCoordinates;
    private int[] sunkenShipValues;
    private boolean sunkenShipFlag;

    public GameScreenPresenter(BattleshipModel model, GameScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;

        this.currentTargetCoordinates = new int[2];
        this.sunkenShipValues = new int[6];
        this.sunkenShipFlag = false;

        createGridPane(view.getFirstGrid(), "red");
        createGridPane(view.getSecondGrid(), "blue");
        view.getFirstSunkenStatsLabel().setText("sunken ships at " + model.getActivePlayer().getName() + "'s battlefield: 0/" + model.getNumberOfShips());
        view.getSecondSunkenStatsLabel().setText("sunken ships at " + model.getPassivePlayer().getName() + "'s battlefield: 0/" + model.getNumberOfShips());

        updateView();
        addEventHandlers();
    }

    private void addEventHandlers() {
        addCellHoverHandler(view.getFirstGrid());
        addCellHoverHandler(view.getSecondGrid());
        addCellClickHandler(view.getFirstGrid());
        addCellClickHandler(view.getSecondGrid());
    }

    private void updateView() {
        setActivePlayerName();
        if (sunkenShipFlag) {
            new Timeline(new KeyFrame(Duration.millis(350), ae -> updateSunkenView())).play();
        }
    }

    private void updateSunkenView() {
        loadStats();
        if (model.getActivePlayer().getColor().equals("red")) {
            removeFlames(view.getFirstGrid(), sunkenShipValues[2], sunkenShipValues[3], sunkenShipValues[4], sunkenShipValues[5]);
            setSunkenShip(view.getFirstGrid(), sunkenShipValues[2], sunkenShipValues[3], sunkenShipValues[4], sunkenShipValues[5]);
        } else if (model.getActivePlayer().getColor().equals("blue")) {
            removeFlames(view.getSecondGrid(), sunkenShipValues[2], sunkenShipValues[3], sunkenShipValues[4], sunkenShipValues[5]);
            setSunkenShip(view.getSecondGrid(), sunkenShipValues[2], sunkenShipValues[3], sunkenShipValues[4], sunkenShipValues[5]);
        }
        sunkenShipFlag = false;
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
            view.getFirstSunkenStatsLabel().setText("sunken ships at " + model.getActivePlayer().getName() + "'s battlefield: " + model.getActiveSinkCounter() + UISettings.getFileSeparator() + model.getNumberOfShips());
        } else if (model.getActivePlayer().getColor().equals("blue")) {
            view.getSecondSunkenStatsLabel().setText("sunken ships at " + model.getActivePlayer().getName() + "'s battlefield: " + model.getActiveSinkCounter() + UISettings.getFileSeparator() + model.getNumberOfShips());
        }
    }

    private void addCellHoverHandler(GridPane grid) {
        for (Node targetNode : grid.getChildren()) {
            Integer targetColumnIndex = GridPane.getColumnIndex(targetNode);
            Integer targetRowIndex = GridPane.getRowIndex(targetNode);
            targetNode.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    if ((model.getActivePlayer().getColor().equals("red") && grid.equals(view.getFirstGrid()))
                            || (model.getActivePlayer().getColor().equals("blue") && grid.equals(view.getSecondGrid()))
                            || (targetColumnIndex == 0 || targetRowIndex == 0)
                            || (targetNode.getStyleClass().contains("grid-pane-miss"))
                            || (targetNode.getStyleClass().contains("grid-pane-hit"))) {
                        t.consume();
                    } else {
                        targetNode.getStyleClass().add("grid-pane-selected");
                        currentTargetCoordinates[0] = targetColumnIndex;
                        currentTargetCoordinates[1] = targetRowIndex;
                    }
                }
            });
            targetNode.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    targetNode.getStyleClass().remove("grid-pane-selected");
                }
            });
        }
    }

    private void addCellClickHandler(GridPane grid) {
        for (Node targetNode : grid.getChildren()) {
            targetNode.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    if (targetNode.getStyleClass().contains("grid-pane-miss") || targetNode.getStyleClass().contains("grid-pane-hit")
                            || currentTargetCoordinates[0] == 0 || currentTargetCoordinates[1] == 0) {
                        t.consume();
                    } else {
                        int[] returnValue = model.activePlayerPlays(currentTargetCoordinates[0], currentTargetCoordinates[1]);
                        String musicFile;
                        if (returnValue[0] == 1) {
                            targetNode.getStyleClass().add("grid-pane-hit");
                            Image flameImage = new Image(UISettings.getFileSeparator() + "images" + UISettings.getFileSeparator() + "flame_" + model.getActivePlayer().getColor() + ".png", 50, 50, true, true);
                            ImageView flameView = new ImageView(flameImage);
                            grid.add(flameView, currentTargetCoordinates[0], currentTargetCoordinates[1]);
                            musicFile = "hit.mp3";
                            if (returnValue[1] == 1) {
                                sunkenShipValues = returnValue;
                                sunkenShipFlag = true;
                                if (model.isGameOver()) {
                                    new Timeline(new KeyFrame(Duration.millis(1000), ae -> openVictoryScreen())).play();
                                }
                            }
                        } else {
                            targetNode.getStyleClass().add("grid-pane-miss");
                            musicFile = "miss.mp3";
                        }
                        Arrays.fill(currentTargetCoordinates, 0);
                        updateView();
                        Path musicPath = Paths.get("resources" + UISettings.getFileSeparator() +"other"+ UISettings.getFileSeparator() + musicFile);
                        Media sound = new Media(musicPath.toUri().toString());
                        mediaPlayer = new MediaPlayer(sound);
                        mediaPlayer.play();
                    }
                }
            });
        }
    }

    private void setSunkenShip(GridPane grid, int size, int x, int y, int direction) {
        Image shipImage = null;
        if (direction == 0) {
            switch (size) {
                case 2: shipImage = new Image(UISettings.getFileSeparator() + "images" + UISettings.getFileSeparator() + "ship_destroyed_2.png", 100, 50, true, true); break;
                case 3: shipImage = new Image(UISettings.getFileSeparator() + "images" + UISettings.getFileSeparator() + "ship_destroyed_3.png", 150, 50, true, true); break;
                case 4: shipImage = new Image(UISettings.getFileSeparator() + "images" + UISettings.getFileSeparator() + "ship_destroyed_4.png", 200, 50, true, true); break;
                case 5: shipImage = new Image(UISettings.getFileSeparator() + "images" + UISettings.getFileSeparator() + "ship_destroyed_5.png", 250, 50, true, true); break;
            }
        } else {
            switch (size) {
                case 2: shipImage = new Image(UISettings.getFileSeparator() + "images" + UISettings.getFileSeparator() + "ship_destroyed_2_vert.png", 50, 100, true, true); break;
                case 3: shipImage = new Image(UISettings.getFileSeparator() + "images" + UISettings.getFileSeparator() + "ship_destroyed_3_vert.png", 50, 150, true, true); break;
                case 4: shipImage = new Image(UISettings.getFileSeparator() + "images" + UISettings.getFileSeparator() + "ship_destroyed_4_vert.png", 50, 200, true, true); break;
                case 5: shipImage = new Image(UISettings.getFileSeparator() + "images" + UISettings.getFileSeparator() + "ship_destroyed_5_vert.png", 50, 250, true, true); break;
            }
        }
        ImageView shipView = new ImageView(shipImage);
        int rowSpan;
        int colSpan;
        if (direction == 0) {
            rowSpan = 1;
            colSpan = size;
        } else {
            rowSpan = size;
            colSpan = 1;
        }
        grid.add(shipView, x, y, colSpan, rowSpan);
    }

    private void removeFlames(GridPane grid, int size, int startX, int startY, int direction) {
        ArrayList<Node> nodeList = new ArrayList<>();
        for (Node n : grid.getChildren()) {
            int nodeX = GridPane.getColumnIndex(n);
            int nodeY = GridPane.getRowIndex(n);
            if (direction == 0 && n instanceof ImageView) {
                switch (size) {
                    case 2:
                        if((nodeX == startX && nodeY == startY) || (nodeX - 1 == startX && nodeY == startY)) {
                            nodeList.add(n);
                        }
                        break;
                    case 3:
                        if((nodeX == startX && nodeY == startY) || (nodeX - 1 == startX && nodeY == startY) || (nodeX - 2 == startX && nodeY == startY)) {
                            nodeList.add(n);
                        }
                        break;
                    case 4:
                        if((nodeX == startX && nodeY == startY) || (nodeX - 1 == startX && nodeY == startY) || (nodeX - 2 == startX && nodeY == startY)
                                        || (nodeX - 3 == startX && nodeY == startY)) {
                            nodeList.add(n);
                        }
                        break;
                    case 5:
                        if((nodeX == startX && nodeY == startY) || (nodeX - 1 == startX && nodeY == startY) || (nodeX - 2 == startX && nodeY == startY)
                                        || (nodeX - 3 == startX && nodeY == startY) || (nodeX - 4 == startX && nodeY == startY)) {
                            nodeList.add(n);
                        }
                        break;
                }
            } else if (direction == 1 && n instanceof ImageView){
                switch (size) {
                    case 2:
                        if(nodeX == startX && nodeY == startY || nodeX == startX && nodeY - 1 == startY) {
                            nodeList.add(n);
                        }
                        break;
                    case 3:
                        if((nodeX == startX && nodeY == startY) || (nodeX == startX && nodeY - 1 == startY) || (nodeX == startX && nodeY - 2 == startY)) {
                            nodeList.add(n);
                        }
                        break;
                    case 4:
                        if((nodeX == startX && nodeY == startY) || (nodeX == startX && nodeY - 1 == startY) || (nodeX == startX && nodeY - 2 == startY)
                                        || (nodeX == startX && nodeY - 3 == startY)) {
                            nodeList.add(n);
                        }
                        break;
                    case 5:
                        if((nodeX == startX && nodeY == startY) || (nodeX == startX && nodeY - 1 == startY) || (nodeX == startX && nodeY - 2 == startY)
                                        || (nodeX == startX && nodeY - 3 == startY) || (nodeX == startX && nodeY - 4 == startY)) {
                            nodeList.add(n);
                        }
                        break;
                }
            }
        }
        for (Node n : nodeList) {
            grid.getChildren().remove(n);
        }
    }

    private void openVictoryScreen() {
        Path musicPath = Paths.get("resources" + UISettings.getFileSeparator() +"other"+ UISettings.getFileSeparator() + "victory.mp3");
        Media sound = new Media(musicPath.toUri().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        VictoryScreenView victoryScreenView = new VictoryScreenView(uiSettings);
        VictoryScreenPresenter victoryScreenPresenter = new VictoryScreenPresenter(model, victoryScreenView, uiSettings);
        view.getScene().setRoot(victoryScreenView);
        victoryScreenPresenter.windowsHandler();
    }

}