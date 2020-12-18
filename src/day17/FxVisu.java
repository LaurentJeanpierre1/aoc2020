package day17;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

// option VM nécessaire :  -Dprism.forceGPU=true
// -> illisble, stop here
public class FxVisu extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  public Parent createContent() throws Exception {

    // Build the Scene Graph
    Group root = new Group();

    // Box
    new Thread(()->{
    for (int k=-5; k<8; ++k) {
      for (int i = -5; i < 8; ++i) {
        for (int j = -5; j < 8; ++j) {
          Sphere testBox = new Sphere(0.25);//Box(0.5, 0.5, 0.5);
          testBox.setMaterial(new PhongMaterial(Color.RED.deriveColor(k*45, 1, 1, 1)));
          //testBox.setDrawMode(DrawMode.LINE);
          testBox.setTranslateX(i); // Horizontal
          testBox.setTranslateY(j); // Vertical
          testBox.setTranslateZ(k*2); // Profondeur
          Platform.runLater(() -> {
            root.getChildren().add(testBox);
          });
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
    }).start();
    // Create and position camera
    PerspectiveCamera camera = new PerspectiveCamera(true);
    camera.getTransforms().addAll (
        new Rotate(-20, Rotate.Y_AXIS),
        new Rotate(-20, Rotate.X_AXIS),
        new Translate(0, 0, -50));

    root.getChildren().add(camera);

    // Use a SubScene
    SubScene subScene = new SubScene(root, 800,800,true,SceneAntialiasing.BALANCED);
    subScene.setFill(Color.ALICEBLUE);
    subScene.setCamera(camera);
    Group group = new Group();
    group.getChildren().add(subScene);
    return group;
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setResizable(false);
    Scene scene = new Scene(createContent());
    primaryStage.setScene(scene);
    primaryStage.show();
  }}
