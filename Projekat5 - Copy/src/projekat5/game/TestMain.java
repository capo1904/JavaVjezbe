package projekat5.game;

import java.nio.file.Paths;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        try {
            String csv = Paths.get("src","projekat5","game","enemies.csv").toString();
            System.out.println("Loading enemies from: " + csv);
            // Use tolerant loader that clamps out-of-range damage/health values
            List<Enemy> enemies = Game.loadEnemiesFromCSV(csv, true);
            System.out.println("Loaded enemies:");
            for (Enemy e : enemies) System.out.println("  " + e);

            // create player at (50,50) rectangle 32x32
            Player p = new Player("Tester", 100, 50, 50, new RectangleCollider(50,50,32,32));
            Game g = new Game();
            g.setPlayer(p);
            for (Enemy e : enemies) g.addEnemy(e);

            System.out.println("\nBefore resolving collisions: " + g.getPlayer());
            g.resolveCollisions();
            System.out.println("After resolving collisions: " + g.getPlayer());

            System.out.println("Colliding with player:");
            for (Enemy e : g.collidingWithPlayer()) System.out.println("  " + e);

            System.out.println("Log:");
            for (String l : g.getLog()) System.out.println("  " + l);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}