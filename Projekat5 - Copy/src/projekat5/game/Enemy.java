package projekat5.game;

public class Enemy extends GameObject implements Attacker {
    private String type;
    private int damage; // 0..100
    private int health; // 0..100

    public Enemy(String type, int damage, int health, int x, int y, Collidable collider) {
        super(x, y, collider);
        if (type == null) throw new IllegalArgumentException("Type cannot be null");
        type = type.trim();
        if (type.isEmpty()) throw new IllegalArgumentException("Type cannot be empty");
        this.type = type;
        setDamage(damage);
        setHealth(health);
    }

    public String getType() { return type; }

    public int getDamage() { return damage; }

    public void setDamage(int damage) {
        if (damage < 0 || damage > 100) throw new IllegalArgumentException("Damage must be between 0 and 100");
        this.damage = damage;
    }

    public int getHealth() { return health; }

    public void setHealth(int health) {
        if (health < 0 || health > 100) throw new IllegalArgumentException("Health must be between 0 and 100");
        this.health = health;
    }

    @Override
    public int getEffectiveDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return String.format("Enemy %s (damage=%d, health=%d) at (%d,%d)", type, damage, health, getX(), getY());
    }

    @Override
    public String getDisplayName() {
        return type;
    }
}
