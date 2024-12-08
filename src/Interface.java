import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
                    if (pickResult.getIntersectedNode() != null) {
                        Point2D texCoord = pickResult.getIntersectedTexCoord();
                        if (texCoord != null) {
                            double longitude = 360 * (texCoord.getX() - 0.5);
                            double latitude = 180 * (0.5 - texCoord.getY());

                            System.out.println("Coordonnées cliquées : Latitude = " + latitude + ", Longitude = " + longitude);

                            Aeroport nearestAeroport = world.findNearest(latitude, longitude);
                            if (nearestAeroport != null) {
                                System.out.println("Aéroport le plus proche : " + nearestAeroport);
                                earth.displayRedSphere(nearestAeroport);

                                String apiUrl = "http://api.aviationstack.com/v1/flights"
                                        + "?access_key=9cb7d91fda05e27c1cd3ac65ad97407d"
                                        + "&arr_iata=" + nearestAeroport.getIATA();

                                // Exécuter la tâche HTTP dans un nouveau thread
                                RequestHttp task = new RequestHttp(apiUrl, world, earth);
                                Thread thread = new Thread(task);
                                thread.start();
                            } else {
                                System.out.println("Aucun aéroport trouvé.");
                            }

                        }
                    }
                }
            });


        }


    public static void main(String[] args) {
        launch(args);
    }
}

