package projekat5.game;

public class BossEnemy extends Enemy {
    public BossEnemy(String type, int damage, int health, int x, int y, Collidable collider) {
        super(type, damage, health, x, y, collider);
    }

    @Override
    public int getEffectiveDamage() {
        return super.getEffectiveDamage() * 2;
    }

    @Override
    public String toString() {
        return String.format("Boss %s (damage=%d, health=%d) at (%d,%d)", getType(), getDamage(), getHealth(), getX(), getY());
    }
}
