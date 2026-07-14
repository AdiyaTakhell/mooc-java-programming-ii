package asteroids;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AsteroidsApplication extends Application {

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setPrefSize(600, 400);

        Text text = new Text(10, 20, "Points: 0");
        pane.getChildren().add(text);

        AtomicInteger points = new AtomicInteger();

        Ship ship = new Ship(300, 200);
        List<Asteroid> asteroids = new ArrayList<>();
        List<Projectile> projectiles = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Random rnd = new Random();
            Asteroid asteroid = new Asteroid(rnd.nextInt(100), rnd.nextInt(100));
            asteroids.add(asteroid);
        }

        pane.getChildren().add(ship.getCharacter());
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getCharacter()));

        Scene scene = new Scene(pane);
        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();

        scene.setOnKeyPressed(event -> pressedKeys.put(event.getCode(), Boolean.TRUE));
        scene.setOnKeyReleased(event -> pressedKeys.put(event.getCode(), Boolean.FALSE));

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    ship.turnLeft();
                }
                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    ship.turnRight();
                }
                if (pressedKeys.getOrDefault(KeyCode.UP, false)) {
                    ship.accelerate();
                }
                if (pressedKeys.getOrDefault(KeyCode.SPACE, false) && projectiles.size() < 3) {
                    Projectile projectile = new Projectile((int) ship.getCharacter().getTranslateX(), (int) ship.getCharacter().getTranslateY());
                    projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
                    projectile.accelerate();
                    projectile.setMovement(projectile.getMovement().normalize().multiply(3));
                    projectiles.add(projectile);
                    pane.getChildren().add(projectile.getCharacter());
                }

                ship.move();
                asteroids.forEach(asteroid -> asteroid.move());
                projectiles.forEach(projectile -> projectile.move());

                asteroids.forEach(asteroid -> {
                    if (ship.collide(asteroid)) {
                        stop();
                    }
                });

                List<Projectile> projectilesToRemove = new ArrayList<>();
                List<Asteroid> asteroidsToRemove = new ArrayList<>();

                projectiles.forEach(projectile -> {
                    asteroids.forEach(asteroid -> {
                        if (projectile.collide(asteroid)) {
                            projectilesToRemove.add(projectile);
                            asteroidsToRemove.add(asteroid);
                        }
                    });
                });

                projectilesToRemove.forEach(p -> {
                    pane.getChildren().remove(p.getCharacter());
                    projectiles.remove(p);
                });

                asteroidsToRemove.forEach(a -> {
                    pane.getChildren().remove(a.getCharacter());
                    asteroids.remove(a);
                    text.setText("Points: " + points.addAndGet(1000));
                });

                if (Math.random() < 0.005) {
                    Random rnd = new Random();
                    Asteroid asteroid = new Asteroid(600, rnd.nextInt(400));
                    if (!asteroid.collide(ship)) {
                        asteroids.add(asteroid);
                        pane.getChildren().add(asteroid.getCharacter());
                    }
                }
            }
        }.start();

        stage.setTitle("Asteroids!");
        stage.setScene(scene);
        stage.show();
    }

    public static int partsCompleted() {
        return 4;
    }

    public static void main(String[] args) {
        launch(AsteroidsApplication.class);
    }
}
