package de.jonas.schroeter;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Arrays;


/**
 * Created by Jonas Schroeter on 01.05.2018 for "CandyCrush".
 */
public class Main extends Application {

    public static Pane root = new Pane();
    public static Scene scene = new Scene(root,  Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
    public static double startX;
    public static double startY;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
        scene.setFill(Paint.valueOf("B5DBFF"));
        createGameField();
        createGameObjects();
    }
    private static void createGameField(){
        ImageView view = new ImageView();
        view.setImage(new Image("de/jonas/schroeter/img/gameField.png"));
        view.setId("gameField");
        double width = view.getImage().getWidth();
        view.setLayoutX((scene.getWidth() - width) / 2 + scene.getWidth() / 100 * 10);
        double height = view.getImage().getHeight();
        view.setLayoutY((scene.getHeight() - height) / 2);
        root.getChildren().add(view);
    }
    private static void createGameObjects(){
        Node gameField = scene.lookup("#gameField");
        startX = gameField.getLayoutX();
        startY = gameField.getLayoutY();
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 9; j++){
                GameObject object = GameObject.addGameObject(startX + 100 * (i), startY + 100 * (j), "de/jonas/schroeter/img/red.png");
                object.setColorRandomly();
            }
        }
    }
}
