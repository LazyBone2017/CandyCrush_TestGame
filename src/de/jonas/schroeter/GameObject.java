package de.jonas.schroeter;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Jonas Schroeter on 01.05.2018 for "CandyCrush".
 */
public class GameObject extends ImageView {

    private String color;

    public GameObject(String image, double x, double y){
        this.setImage(new Image(image));
        color = "red";
        this.setLayoutX(x);
        this.setLayoutY(y);
        Main.root.getChildren().add(this);
        this.setOnMouseClicked(event -> {
            this.remove();
        });
        this.setId("object" + this.getCoords()[0] + "_" + this.getCoords()[1]);
    }
    public static GameObject addGameObject(double x, double y, String image){
        GameObject gameObject = new GameObject(image, x, y);
        return gameObject;
    }
    public void setColorRandomly(){
        String[] colors = {"red", "pink", "yellow", "green", "blue", "black", "orange"};
        String x = colors[new Random().nextInt(colors.length)];
        this.setImage(new Image("de/jonas/schroeter/img/" + x + ".png"));
        this.color = x;
    }
    public int[] getCoords(){
        int[] coords = new int[2];
        coords[0] = (int)(this.getLayoutX() - Main.startX) / 100;
        coords[1] = (int)(this.getLayoutY() - Main.startY) / 100;
        return coords;
    }
    public void remove(){
        int[] coords = this.getCoords();
        /*for(int i = 0; i < Main.root.getChildren().size(); i++){
            if(Main.root.getChildren().get(i).getId().equals(this.getId())){
                GameObject object = (GameObject)Main.root.getChildren().get(i);
                System.out.println(Arrays.toString(object.getCoords()));
                Main.root.getChildren().remove(i);
            }
        }*/
        Main.root.getChildren().remove(this);
        if(coords[1] > 0){
            coords[1] = coords[1] - 1;
            try{
                getByCoords(coords).update();
            }
            catch(NullPointerException e){

            }
        }

    }
    public void update(){
        int[] coords = this.getCoords();
        System.out.println(Arrays.toString(coords));
        if(Main.scene.lookup(("#object" + coords[0] + "_" + (coords[1] + 1))) == null && coords[1] + 1 < 9){
            this.setLayoutY(this.getLayoutY() + 100);
            this.setId("object" + this.getCoords()[0] + "_" + this.getCoords()[1]);
        }
        if(coords[1] > 0){
            coords[1] = coords[1] - 1;
            getByCoords(coords).update();
        }
        checkForRow();
    }
    public static GameObject getByCoords(int[] coords){
        return (GameObject)Main.scene.lookup("#object" + coords[0] + "_" + coords[1]);
    }
    public void checkForRow(){
        int[] coords = this.getCoords();
        System.out.println(Arrays.toString(coords));
        int xCoord = coords[0];
        ArrayList<GameObject> objects = new ArrayList<>();
        while(getByCoords(new int[]{xCoord, coords[1]}).color.equals(color)){
            objects.add(getByCoords(new int[]{xCoord, coords[1]}));
            xCoord++;
        }
        if(objects.size() > 2){
            for(GameObject o : objects)o.remove();
            System.out.println("Removed " + objects.size() + " objects! Color: " + objects.get(0).color);
        }
        objects.clear();
        xCoord = coords[0];
        while(getByCoords(new int[]{xCoord, coords[1]}).color.equals(color)){
            objects.add(getByCoords(new int[]{xCoord, coords[1]}));
            xCoord--;
        }
        if(objects.size() > 2){
            for(GameObject o : objects)o.remove();
            System.out.println("Removed " + objects.size() + " objects! Color: " + objects.get(0).color);
        }
    }
}
