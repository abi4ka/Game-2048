package org.abik.game2048;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.FontWeight;

public class GameUI {
    private StackPane root;
    private Label statusLabel;

    public GameUI() {
        root = new StackPane();
        setupUI();
    }

    private void setupUI() {
        statusLabel = new Label();
        statusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        statusLabel.setTextFill(Color.RED);
        statusLabel.setTextAlignment(TextAlignment.CENTER);
        statusLabel.setVisible(false);  // Hidden by default
        root.getChildren().add(statusLabel);
    }

    public StackPane getRoot() {
        return root;
    }

    public void showMessage(String message, Color color) {
        statusLabel.setText(message);
        statusLabel.setTextFill(color);
        statusLabel.setVisible(true);
    }
}
