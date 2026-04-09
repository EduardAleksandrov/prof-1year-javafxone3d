package com.example;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.IOException;


import javafx.scene.*;
import javafx.scene.shape.Box;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void cube(Stage stage) throws IOException {
        // --- 1. Настройка 3D контента ---
        
        // 1. 3D Content
        Box box = new Box(100, 100, 100);
        PhongMaterial material = new PhongMaterial(Color.ORANGE);
        material.setSpecularColor(Color.WHITE);
        box.setMaterial(material);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-400);
        camera.setNearClip(0.1);
        camera.setFarClip(1000.0);

        Group world = new Group(box);
        
        // 2. SubScene (Set depthBuffer to true for 3D)
        SubScene subScene = new SubScene(world, 600, 400, true, SceneAntialiasing.BALANCED);
        subScene.setCamera(camera);
        subScene.setFill(Color.DARKSLATEGRAY);

        // 3. 2D Overlay
        Button btn = new Button("Go to Secondary");
        btn.setOnAction(e -> {
            System.out.println("Button Clicked!");
            try{
                App.setRoot("primary");
            } catch(IOException ex){
                System.out.println(ex.toString());
            }
        });
        
        StackPane gui = new StackPane(btn);
        gui.setAlignment(Pos.BOTTOM_CENTER);
        gui.setPickOnBounds(false);

        // 4. Combine and Show
        StackPane root = new StackPane(subScene, gui);
        scene = new Scene(root, 600, 400);

        RotateTransition rt = new RotateTransition(Duration.millis(3000), box);
        rt.setAxis(Rotate.Y_AXIS);
        rt.setByAngle(360);
        rt.setCycleCount(RotateTransition.INDEFINITE);
        rt.play();
    
        stage.setScene(scene);
    }

}