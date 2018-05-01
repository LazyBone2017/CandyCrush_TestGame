package de.jonas.schroeter;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Random;

/**
 * Created by Jonas Schroeter on 01.05.2018 for "CandyCrush".
 */
public class GameObject extends ImageView {


    public GameObject(String image){
        this.setImage(new Image(image));
        this.setOnMouseClicked(event -> {
            this.update();
        });
        this.setId("object" + this.getCoords()[0] + "_" + this.getCoords()[1]);
    }
    public static GameObject addGameObject(double x, double y, String image){
        GameObject gameObject = new GameObject(image);
        gameObject.setLayoutX(x);
        gameObject.setLayoutY(y);
        Main.root.getChildren().add(gameObject);
        return gameObject;
    }
    public void setColorRandomly(){
        String[] colors = {"red", "pink", "yellow", "green", "blue", "black", "orange"};
        this.setImage(new Image("de/jonas/schroeter/img/" + colors[new Random().nextInt(colors.length)] + ".png"));
    }
    public int[] getCoords(){
        int[] coords = new int[2];
        coords[0] = (int)(this.getLayoutX() - Main.startX) / 100;
        coords[1] = (int)(this.getLayoutY() - Main.startY) / 100;
        return coords;
    }
    public void update(){
        Main.root.getChildren().remove(this);
    }
    public static GameObject getByCoords(int[] coords){
        return (GameObject)Main.scene.lookup("#object" + coords[0] + "_" + coords[1]);
    }
}
