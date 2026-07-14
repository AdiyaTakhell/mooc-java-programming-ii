package asteroids;

import java.util.Random;
import javafx.scene.shape.Polygon;

public class PolygonFactory {

    public Polygon createPolygon() {
        Random rnd = new Random();
        double size = 10 + rnd.nextInt(20);

        Polygon polygon = new Polygon();
        double c1 = Math.sin(Math.toRadians(0)) * size;
        double s1 = Math.cos(Math.toRadians(0)) * size;
        double c2 = Math.sin(Math.toRadians(72)) * size;
        double s2 = Math.cos(Math.toRadians(72)) * size;
        double c3 = Math.sin(Math.toRadians(144)) * size;
        double s3 = Math.cos(Math.toRadians(144)) * size;
        double c4 = Math.sin(Math.toRadians(216)) * size;
        double s4 = Math.cos(Math.toRadians(216)) * size;
        double c5 = Math.sin(Math.toRadians(288)) * size;
        double s5 = Math.cos(Math.toRadians(288)) * size;

        polygon.getPoints().addAll(
            c1 + rnd.nextInt(5), s1 + rnd.nextInt(5),
            c2 + rnd.nextInt(5), s2 + rnd.nextInt(5),
            c3 + rnd.nextInt(5), s3 + rnd.nextInt(5),
            c4 + rnd.nextInt(5), s4 + rnd.nextInt(5),
            c5 + rnd.nextInt(5), s5 + rnd.nextInt(5)
        );

        return polygon;
    }
}
