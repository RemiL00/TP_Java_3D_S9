import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class Interface extends Application {
        @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Earth");
        Earth earth = new Earth();
        Group root = new Earth();
        Pane pane = new Pane(root);
        Scene ihm = new Scene(pane, 600, 400,true);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        ihm.setCamera(camera);

        primaryStage.setScene(ihm);
        primaryStage.show();

//            ihm.addEventHandler(MouseEvent.ANY, event -> {
//                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
//                    System.out.println("Clicked on : (" event.getSceneX()+ ", "+ event.getSceneY()+")";
//                }
//                if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
//                    camera.getTransforms().add(...); // A vous de compl´eter
//                }
//            });

        }


    public static void main(String[] args) {
        launch(args);
    }
}

