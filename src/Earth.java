import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class Earth extends Group {
    private Sphere sph;
    private Rotate ry;

    public Earth(){
        sph = new Sphere(300);
        PhongMaterial material = new PhongMaterial();
        Image earthTexture = new Image("file:C:\\Users\\remil\\IdeaProjects\\TP_Java_3D_S9\\data\\earth_lights_4800.png");
        material.setDiffuseMap(earthTexture);
        sph.setMaterial(material);

        ry = new Rotate(0,Rotate.Y_AXIS);
        this.getTransforms().add(ry);

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

    public Sphere createSphere(Aeroport a,Color color){
        return createSphere(a.getLatitude(),a.getLongitude(),color);
    }

    public Sphere createSphere(double latitude, double longitude,Color color){
        PhongMaterial col = new PhongMaterial();
        col.setSpecularColor(color);
        col.setDiffuseColor(color);

        Sphere coloredSphere = new Sphere(2);
        coloredSphere.setMaterial(col);

        coloredSphere.setTranslateZ(-sph.getRadius());

        Rotate rPhi = new Rotate (-longitude,
                -coloredSphere.getTranslateX(),-coloredSphere.getTranslateY(),
                -coloredSphere.getTranslateZ(),Rotate.Y_AXIS);

        coloredSphere.getTransforms().add(rPhi);
        Rotate rTheta = new Rotate (-latitude*60.0/90.0,
                -coloredSphere.getTranslateX(),-coloredSphere.getTranslateY(),
                -coloredSphere.getTranslateZ(),Rotate.X_AXIS);
        coloredSphere.getTransforms().add(rTheta);

        return coloredSphere;
    }
    private Sphere redSphere;
    public void displayRedSphere(Aeroport a){
        redSphere=createSphere(a,Color.RED);
        this.getChildren().add(redSphere);
    }

    public void displayYellowSphere(Aeroport a) {
        if (a == null) {
            System.out.println("Aéroport invalide pour la sphère jaune.");
            return;
        }

        Sphere yellowSphere = createSphere(a, Color.YELLOW);
        this.getChildren().add(yellowSphere);

        System.out.println("Sphère jaune ajoutée pour l'aéroport : " + a.getIATA());
    }


}
