package com.traindiorama.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Controller extends Application {

    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        //primaryを呼び出しsizを固定 正直いらない
        scene = new Scene(loadFXML("main"));
        Image imageIcon = new Image("file:src\\main\\resources\\com\\traindiorama\\icon.png");
        stage.getIcons().add(imageIcon);
        stage.setTitle("Diorama Controller");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
