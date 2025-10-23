package projekat2;

public class Enemy {
    private String type;
    private int x;
    private int y;
    private int width;
    private int height;
    private int damage;

    public Enemy(String type, int x, int y, int width, int height, int damage) {
        setType(type);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setDamage(damage);
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            this.type = "Unknown";
        } else {
            this.type = type.trim();
        }
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        if (damage < 0) this.damage = 0;
        else if (damage > 100) this.damage = 100;
        else this.damage = damage;
    }
    
    @Override
    public String toString() {
        return "Enemy(" + type + ") @ (" + x + ", " + y + ") " + width + "x" + height + " DMG=" + damage;
    }

}
