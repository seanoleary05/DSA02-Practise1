package com.example.practise1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    private Stage window;
    Scene scene;
    Button button;
    Label label = new Label();
    ImageView imageView = new ImageView();
    ImageView imageView2 = new ImageView();


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        button = new Button("Click me");
        choiceBox.getItems().add("Hello");
        choiceBox.getItems().add("World");
        choiceBox.getItems().add("Open Image");
        choiceBox.getItems().add("Open Image As Greyscale");
        choiceBox.setValue("Starting Message");

        button.setOnAction(e -> {
            String option = choiceBox.getValue();
            if (option != null) {
                executeOption(option);
            }
        });
        VBox root = new VBox(10);
        root.getChildren().addAll(choiceBox, button, imageView, imageView2);
        Scene scene = new Scene(root, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    private void executeOption(String option) {
        switch (option) {
            case "Open Image":
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open File");
                fileChooser.setInitialDirectory(new File("C:/"));
                File file = fileChooser.showOpenDialog(new Stage());
                if (file != null) {
                    label.setText(file.getName());
                    Image image1 = new Image(file.getPath());
                    imageView.setImage(image1);
                    imageView.setFitHeight(300);
                    imageView.setFitWidth(300);


                }

            case "Open Image As Greyscale":
                Label label = new Label();
                FileChooser fileChooser2 = new FileChooser();
                fileChooser2.setTitle("Open File");
                fileChooser2.setInitialDirectory(new File("C:/"));
                File file2 = fileChooser2.showOpenDialog(new Stage());

                if (file2 != null) {
                    label.setText(file2.getName());
                    try {  // Important: Wrap in a try-catch for image loading errors
                        Image image2 = new Image(file2.toURI().toString()); // Use toURI().toString() for better compatibility
                        int width = (int) image2.getWidth();
                        int height = (int) image2.getHeight();

                        WritableImage wImage1 = new WritableImage(width, height);
                        PixelReader pr = image2.getPixelReader();
                        PixelWriter pw = wImage1.getPixelWriter();

                        for (int y = 0; y < height; y++) {
                            for (int x = 0; x < width; x++) {
                                Color c = pr.getColor(x, y);
                                double r = c.getRed();
                                double g = c.getGreen();
                                double b = c.getBlue();
                                double grey = (r + g + b) / 3;
                                Color nc = new Color(grey, grey, grey, 1.0);
                                pw.setColor(x, y, nc); // Set the color for each pixel
                            }
                        }

                        imageView2.setImage(wImage1);
                        imageView2.setFitHeight(300);
                        imageView2.setFitWidth(300);


                    } catch (Exception e) {
                        // Handle potential image loading errors (e.g., file not found, invalid format)
                        System.err.println("Error loading image: " + e.getMessage());
                        // Optionally, display an error message to the user.
                        label.setText("Error loading image"); // Or a more informative message
                    }


                }
        }
    }
}












