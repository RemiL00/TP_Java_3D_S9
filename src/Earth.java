import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class Earth extends Group {
    private Sphere sph;
    private Rotate ry;

    public Earth(){
        sph = new Sphere(300);
        PhongMaterial material = new PhongMaterial();
        Image earthTexture = new Image("file:C:/Users/Remi/Documents/PERSO/Cours ENSEA/3eme année/Java/TP_Java_3D_S9/data/earth_lights_4800.png");
        material.setDiffuseMap(earthTexture);
        sph.setMaterial(material);

        ry = new Rotate(0,Rotate.Y_AXIS);
        sph.getTransforms().add(ry);

        this.getChildren().add(sph);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                //System.out.println("Valeur de time : " + time);
                double angle = (time / 1_000_000_000.0) * 360 / 15;
                ry.setAngle(angle % 360);
            }
        };
        animationTimer.start();
    }
}
