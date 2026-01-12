package org.abik.game2048;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyEvent;

public class Game2048 extends Application {

    private static final int SIZE = 4;
    private static final int TILE_SIZE = 100;
    private GameLogic gameLogic;
    private GridPane grid;
    private GameUI gameUI;

    @Override
    public void start(Stage primaryStage) {
        gameUI = new GameUI();
        gameLogic = new GameLogic();
        grid = new GridPane();

        drawBoard();

        // Создаем StackPane, добавляем игровую сетку и поверх нее UI с сообщением
        StackPane root = new StackPane(grid, gameUI.getRoot());
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::handleInput);

        primaryStage.setTitle("2048 Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Обработка нажатий клавиш
    private void handleInput(KeyEvent event) {
        switch (event.getCode()) {
            case W -> gameLogic.moveUp();
            case A -> gameLogic.moveLeft();
            case S -> gameLogic.moveDown();
            case D -> gameLogic.moveRight();
        }
        updateUI();

        // Проверка на победу или проигрыш
        if (gameLogic.hasWon()) {
            gameUI.showMessage("You Won!", Color.rgb(65, 220, 13));
            System.out.println("You won!");
        } else if (gameLogic.hasLost()) {
            gameUI.showMessage("Game Over!", Color.rgb(220, 13, 13));
            System.out.println("You lost!");

        }
    }

    // Метод для отображения массива на экране
    private void drawBoard() {
        grid.getChildren().clear();  // Очищаем старые элементы перед перерисовкой

        int[][] board = gameLogic.getBoard();  // Получаем текущее состояние из GameLogic

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setStroke(Color.BLACK);

                // Устанавливаем цвет в зависимости от значения
                int value = board[row][col];
                switch (value) {
                    case 0 -> tile.setFill(Color.rgb(255, 255, 255));
                    case 2 -> tile.setFill(Color.rgb(238, 228, 218));
                    case 4 -> tile.setFill(Color.rgb(237, 224, 200));
                    case 8 -> tile.setFill(Color.rgb(242, 177, 121));
                    case 16 -> tile.setFill(Color.rgb(245, 149, 99));
                    case 32 -> tile.setFill(Color.rgb(246, 124, 96));
                    case 64 -> tile.setFill(Color.rgb(246, 94, 59));
                    case 128 -> tile.setFill(Color.rgb(237, 207, 115));
                    case 256 -> tile.setFill(Color.rgb(237, 204, 98));
                    case 512 -> tile.setFill(Color.rgb(237, 200, 80));
                    case 1024 -> tile.setFill(Color.rgb(237, 197, 63));
                    case 2048 -> tile.setFill(Color.rgb(237 ,194 , 45));
                }

                // Создаем StackPane для плитки
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(tile);
                Text number = new Text();

                if (value != 0) {
                    number.setText(String.valueOf(value));
                    number.setFont(Font.font(30));
                    number.setFill(Color.BLACK);
                }

                stackPane.getChildren().add(number);

                // Добавляем StackPane в сетку
                grid.add(stackPane, col, row);
            }
        }
    }

    // Метод для обновления интерфейса
    private void updateUI() {
        drawBoard();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
