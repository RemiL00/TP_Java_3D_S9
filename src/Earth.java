import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Sphere;

import java.util.Collection;

public class Earth extends Group {
    private Sphere sph;

    public Earth(){
        sph = new Sphere(300);
        this.getChildren().add(sph);
    }
}
