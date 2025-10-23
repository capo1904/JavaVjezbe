//Elena Kankaraš 22/104 FIST
//Luka Čarapić 22/094 FIST
package projekat2;

public class Player {
    private String name;
    private int x;
    private int y;
    private int width;
    private int height;
    private int health;

    public Player(String name, int x, int y, int width, int height, int health) {
        setName(name);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setHealth(health);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            this.name = "Unknown";
        } else {
            name = name.trim().replaceAll("\\s+", " ");
            String[] words = name.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String w : words) {
                sb.append(Character.toUpperCase(w.charAt(0)))
                  .append(w.substring(1).toLowerCase())
                  .append(" ");
            }
            this.name = sb.toString().trim();
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
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        if (health < 0) this.health = 0;
        else if (health > 100) this.health = 100;
        else this.health = health;
    }

    @Override
    public String toString() {
        return "Player(" + name + ") @ (" + x + "," + y + ") " + width + "x" + height + " HP=" + health;
    }

}
