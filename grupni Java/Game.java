package projekat2;

import java.util.ArrayList;

public class Game {
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<String> eventLog;

    public Game(Player player) {
        this.player = player;
        this.enemies = new ArrayList<>();
        this.eventLog = new ArrayList<>();
    }

    public boolean checkCollision(Player p, Enemy e) {
        boolean collisionX = Math.abs(p.getX() - e.getX()) < (p.getWidth() + e.getWidth()) / 2;
        boolean collisionY = Math.abs(p.getY() - e.getY()) < (p.getHeight() + e.getHeight()) / 2;
        return collisionX && collisionY;
    }

    public void decreaseHealth(Player p, Enemy e) {
        int oldHealth = p.getHealth();
        int newHealth = oldHealth - e.getDamage();
        if (newHealth < 0) newHealth = 0;
        p.setHealth(newHealth);
        eventLog.add("HIT: " + p.getName() + " by " + e.getType() + 
                     " for " + e.getDamage() + " -> HP " + oldHealth + " -> " + newHealth);
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
        eventLog.add("ADDED: " + e.getType());
    }

    public ArrayList<Enemy> findByType(String query) {
        ArrayList<Enemy> result = new ArrayList<>();
        for (Enemy e : enemies) {
            if (e.getType().toLowerCase().contains(query.toLowerCase())) {
                result.add(e);
            }
        }
        return result;
    }

    public ArrayList<Enemy> collidingWithPlayer() {
        ArrayList<Enemy> colliding = new ArrayList<>();
        for (Enemy e : enemies) {
            if (checkCollision(player, e)) {
                colliding.add(e);
            }
        }
        return colliding;
    }

    public void resolveCollisions() {
        for (Enemy e : collidingWithPlayer()) {
            decreaseHealth(player, e);
        }
    }

    public void printEnemies() {
        System.out.println("=== ENEMIES ===");
        for (Enemy e : enemies) {
            System.out.println(e);
        }
    }

    public void showEventLog() {
        System.out.println("=== EVENT LOG ===");
        for (String s : eventLog) {
            System.out.println(s);
        }
    }

    public Player getPlayer() {
        return player;
    }

}
