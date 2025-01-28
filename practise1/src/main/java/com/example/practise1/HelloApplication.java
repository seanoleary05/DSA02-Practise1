package com.example.practise1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        choiceBox.getItems().add("Open File");
        choiceBox.setValue("Starting Message");

       button.setOnAction(e -> {
           String option = choiceBox.getValue();
           if (option != null) {
               executeOption(option);
           }
       });
        VBox root = new VBox(10);
        root.getChildren().addAll(choiceBox,button,imageView);
        Scene scene = new Scene(root, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void executeOption(String option){
        switch(option){
            case "Open File":
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
        }
    }



    }



