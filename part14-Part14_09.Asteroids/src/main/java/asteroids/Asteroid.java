package asteroids;

import java.util.Random;

public class Asteroid extends Character {

    public Asteroid(int x, int y) {
        super(new PolygonFactory().createPolygon(), x, y);

        Random rnd = new Random();
        super.getCharacter().setRotate(rnd.nextInt(360));

        int accelerationAmount = 1 + rnd.nextInt(10);
        for (int i = 0; i < accelerationAmount; i++) {
            accelerate();
        }
    }
}
