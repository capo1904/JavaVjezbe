package projekat5.game;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Game {
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<String> log = new ArrayList<>();

    public Game() {}

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public List<Enemy> getEnemies() { return enemies; }
    public List<String> getLog() { return log; }

    public boolean checkCollision(Player p, Enemy e) {
        return p.intersects(e);
    }

    public void decreaseHealth(Player p, Enemy e) {
        int dmg = e.getEffectiveDamage();
        int before = p.getHealth();
        int after = Math.max(0, before - dmg);
        p.setHealth(after);
        log.add(String.format("%s hit by %s for %d damage: %d -> %d", p.getName(), e.getDisplayName(), dmg, before, after));
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
        log.add("Added enemy: " + e.toString());
    }

    public List<Enemy> findByType(String query) {
        if (query == null) return new ArrayList<>();
        String q = query.toLowerCase(Locale.ROOT);
        return enemies.stream().filter(en -> en.getType().toLowerCase(Locale.ROOT).contains(q)).collect(Collectors.toList());
    }

    public List<Enemy> collidingWithPlayer() {
        ArrayList<Enemy> list = new ArrayList<>();
        if (player == null) return list;
        for (Enemy e : enemies) {
            if (checkCollision(player, e)) list.add(e);
        }
        return list;
    }

    public void resolveCollisions() {
        if (player == null) return;
        for (Enemy e : enemies) {
            if (checkCollision(player, e)) {
                decreaseHealth(player, e);
            }
        }
    }

    // Original public API: strict parsing (throws on out-of-range values)
    public static List<Enemy> loadEnemiesFromCSV(String filePath) {
        return loadEnemiesFromCSV(filePath, false);
    }

    // New public API: allow caller to enable tolerant mode (clamp out-of-range damage/health)
    public static List<Enemy> loadEnemiesFromCSV(String filePath, boolean clampOutOfRange) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            return loadEnemiesFromCSVInternal(lines, clampOutOfRange);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Failed to load CSV: " + ex.getMessage(), ex);
        }
    }

    // Internal parser: lines already read; clampOutOfRange controls tolerant behavior
    private static List<Enemy> loadEnemiesFromCSVInternal(List<String> lines, boolean clampOutOfRange) {
        List<Enemy> result = new ArrayList<>();
        int lineNo = 0;
        String[] headers = null;
        java.util.Map<String, Integer> idx = new java.util.HashMap<>();
        for (String raw : lines) {
            lineNo++;
            String line = raw.trim();
            if (line.isEmpty()) continue;
            if (line.startsWith("#")) continue; // comment
            // If headers not read yet, read from this first non-empty non-comment line
            if (headers == null) {
                headers = line.split(",", -1);
                for (int i = 0; i < headers.length; i++) {
                    idx.put(headers[i].trim().toLowerCase(Locale.ROOT), i);
                }
                // Validate required headers
                String[] required = new String[]{"type","class","damage","health","x","y","shape"};
                for (String r : required) {
                    if (!idx.containsKey(r)) throw new IllegalArgumentException("Missing required CSV header: " + r);
                }
                continue;
            }

            String[] parts = line.split(",", -1);
            // helper to get column value by name
            java.util.function.Function<String, String> val = (name) -> {
                Integer i = idx.get(name);
                if (i == null || i < 0 || i >= parts.length) return "";
                return parts[i].trim();
            };

            String type = val.apply("type");
            String cls = val.apply("class");
            String shape = val.apply("shape").toLowerCase(Locale.ROOT);
            String damageStr = val.apply("damage");
            String healthStr = val.apply("health");
            String xStr = val.apply("x");
            String yStr = val.apply("y");

            if (type.isEmpty()) throw new IllegalArgumentException("Empty type at line " + lineNo);
            if (cls.isEmpty()) throw new IllegalArgumentException("Empty class at line " + lineNo);
            if (damageStr.isEmpty()) throw new IllegalArgumentException("Missing damage at line " + lineNo);
            if (healthStr.isEmpty()) throw new IllegalArgumentException("Missing health at line " + lineNo);
            if (xStr.isEmpty() || yStr.isEmpty()) throw new IllegalArgumentException("Missing coordinates at line " + lineNo);

            int damage = parseIntField(damageStr, "damage", lineNo);
            int health = parseIntField(healthStr, "health", lineNo);
            int x = parseIntField(xStr, "x", lineNo);
            int y = parseIntField(yStr, "y", lineNo);

            // Handle ranges: if clampOutOfRange is true, clamp values into 0..100 and warn, otherwise enforce
            if (damage < 0 || damage > 100) {
                if (clampOutOfRange) {
                    int orig = damage;
                    damage = Math.max(0, Math.min(100, damage));
                    System.err.printf("Warning: clamped damage %d -> %d for '%s' at line %d\n", orig, damage, type, lineNo);
                } else {
                    throw new IllegalArgumentException(String.format("Invalid damage %d for '%s' at line %d: must be 0..100", damage, type, lineNo));
                }
            }
            if (health < 0 || health > 100) {
                if (clampOutOfRange) {
                    int orig = health;
                    health = Math.max(0, Math.min(100, health));
                    System.err.printf("Warning: clamped health %d -> %d for '%s' at line %d\n", orig, health, type, lineNo);
                } else {
                    throw new IllegalArgumentException(String.format("Invalid health %d for '%s' at line %d: must be 0..100", health, type, lineNo));
                }
            }

            Collidable collider = null;
            if (shape.equals("rectangle") || shape.equals("rect")) {
                String wStr = val.apply("width");
                String hStr = val.apply("height");
                if (wStr.isEmpty() || hStr.isEmpty()) throw new IllegalArgumentException("Rectangle needs width and height at line " + lineNo);
                int w = parseIntField(wStr, "width", lineNo);
                int h = parseIntField(hStr, "height", lineNo);
                collider = new RectangleCollider(x, y, w, h);
            } else if (shape.equals("circle") || shape.equals("circ")) {
                String rStr = val.apply("radius");
                if (rStr.isEmpty()) throw new IllegalArgumentException("Circle needs radius at line " + lineNo);
                int r = parseIntField(rStr, "radius", lineNo);
                collider = new CircleCollider(x, y, r);
            } else {
                throw new IllegalArgumentException("Unknown shape '" + shape + "' at line " + lineNo);
            }

            Enemy e;
            String clsLower = cls.toLowerCase(Locale.ROOT);
            if (clsLower.equals("melee")) {
                e = new MeleeEnemy(type, damage, health, x, y, collider);
            } else if (clsLower.equals("boss")) {
                e = new BossEnemy(type, damage, health, x, y, collider);
            } else {
                throw new IllegalArgumentException("Unknown enemy class '" + cls + "' at line " + lineNo);
            }
            result.add(e);
        }
        return result;
    }

    private static int parseIntField(String s, String fieldName, int lineNo) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid integer for '" + fieldName + "' at line " + lineNo + ": " + s);
        }
    }
}