package com.example.practise1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    private Stage window;
    Scene scene;
    Button button;
    Label label = new Label();
    ImageView imageView = new ImageView();


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
        root.getChildren().addAll(choiceBox, button, imageView);
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

        }
    }

    public static Image toGreyScale(Image sourceImage) {
        Label label = new Label();
        FileChooser fileChooser2 = new FileChooser();
        fileChooser2.setTitle("Open File");
        fileChooser2.setInitialDirectory(new File("C:/"));
        File file2 = fileChooser2.showOpenDialog(new Stage());
        if (file2 != null) {
            label.setText(file2.getName());
            Image image1 = new Image(file2.getPath());
            PixelReader pr = image1.getPixelReader();
            int width = (int) image1.getWidth();
            int height = (int) image1.getHeight();
            WritableImage wImage1 = new WritableImage(width, height);
                    /*Color c = pr.getColor(0,0);
                    double r = c.getRed();
                   */
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = pr.getArgb(x, y);


                    int argb = pr.getArgb(0, 0);
                    int r = (argb >> 16) & 0xFF;
                    int g = (argb >> 24) & 0xFF;
                    int b = argb & 0xFF;

                    int grayLevel = (int) (0.2162 * r + 0.7152 * g + 0.0722 * b) / 3;
                    int gray = (grayLevel << 16) + (grayLevel << 8);

                    wImage1.getPixelWriter().setArgb(x, y, gray);
                }

            }
            return wImage1;
        }
    }












