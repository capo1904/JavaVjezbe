package projekat5.game;

public class Player extends GameObject {
    private String name;
    private int health; // 0..100

    public Player(String name, int health, int x, int y, Collidable collider) {
        super(x, y, collider);
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        name = name.trim();
        if (name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        // ensure starts with uppercase
        this.name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        setHealth(health);
    }

    public String getName() { return name; }

    public int getHealth() { return health; }

    public void setHealth(int health) {
        if (health < 0 || health > 100) throw new IllegalArgumentException("Health must be between 0 and 100");
        this.health = health;
    }

    @Override
    public String toString() {
        return String.format("Player %s (health=%d) at (%d,%d)", name, health, getX(), getY());
    }

    @Override
    public String getDisplayName() {
        return name;
    }
}
