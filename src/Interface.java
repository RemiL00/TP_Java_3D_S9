import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
public class Interface extends Application {
        @Override
    public void start(Stage primaryStage) throws Exception {
            World world = new World("data/airport-codes_no_comma.csv");
            primaryStage.setTitle("Earth");
            Earth earth = new Earth();
            Group root = new Group(earth);
            Scene ihm = new Scene(root, 600, 400, true);

            PerspectiveCamera camera = new PerspectiveCamera(true);
            camera.setTranslateZ(-1000);
            camera.setNearClip(0.1);
            camera.setFarClip(2000.0);
            camera.setFieldOfView(35);
            ihm.setCamera(camera);

            primaryStage.setScene(ihm);
            primaryStage.show();

            Translate cameraTranslate = new Translate(0, 0, 0);
            camera.getTransforms().add(cameraTranslate);
            final double[] lastMouseY = {0};

            ihm.addEventHandler(MouseEvent.ANY, event -> {
                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    lastMouseY[0] = event.getSceneY();
                    System.out.println("Clicked on : (" + event.getSceneX() + ", " + event.getSceneY() + ")");
                }
                if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    double deltaY = event.getSceneY() - lastMouseY[0];
                    lastMouseY[0] = event.getSceneY();
                    cameraTranslate.setZ(cameraTranslate.getZ() + deltaY * 1);
                }
            });

            ihm.addEventHandler(MouseEvent.ANY, event -> {
                if (event.getButton() == MouseButton.SECONDARY && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                    PickResult pickResult = event.getPickResult();
                    System.out.println("PickResult: " + pickResult);
                    if (pickResult.getIntersectedNode() != null) {
                        System.out.println("Intersected Node: " + pickResult.getIntersectedNode());
                        Point2D click = pickResult.getIntersectedTexCoord();
                        if (click != null) {
                            System.out.println("Intersected TexCoord: " + click);
                            double longitude=360*(click.getX()-0.5);
                            double latitude=180*(0.5-click.getY());
                            System.out.println("x="+longitude+" y ="+latitude);

                            Aeroport aeroport = world.findNearest(longitude,latitude);
                            if (aeroport != null) {
                                System.out.println(aeroport);

                                //earth.displayRedSphere(aeroport);
                                //ThreadScrapOnlineFlight tsolf = new ThreadScrapOnlineFlight(earth,a,w,listOfFlight);
                                //tsolf.start();
                            } else {
                                System.out.println("Aucun aéroport trouvé à ces coordonnées.");
                            }
                        } else {
                            System.out.println("No texture coordinates available at the clicked position.");
                        }
                    } else {
                        System.out.println("No intersected node.");
                    }
                }
            });
    }


    public static void main(String[] args) {
        launch(args);
    }
}

