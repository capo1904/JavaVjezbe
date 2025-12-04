package projekat5.game;

public class CircleCollider implements Collidable {
    private int centerX, centerY;
    private int radius;

    public CircleCollider(int centerX, int centerY, int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Circle radius must be > 0");
        }
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public int getCenterX() { return centerX; }
    public int getCenterY() { return centerY; }
    public int getRadius() { return radius; }

    @Override
    public boolean intersects(Collidable other) {
        if (other instanceof CircleCollider) {
            CircleCollider c = (CircleCollider) other;
            long dx = this.centerX - c.centerX;
            long dy = this.centerY - c.centerY;
            long distSq = dx*dx + dy*dy;
            long radSum = (long) this.radius + c.radius;
            return distSq <= radSum*radSum;
        } else if (other instanceof RectangleCollider) {
            RectangleCollider r = (RectangleCollider) other;
            int closestX = clamp(this.centerX, r.getX(), r.getX() + r.getWidth());
            int closestY = clamp(this.centerY, r.getY(), r.getY() + r.getHeight());
            long dx = this.centerX - closestX;
            long dy = this.centerY - closestY;
            return dx*dx + dy*dy <= (long) this.radius * this.radius;
        }
        return false;
    }

    private int clamp(int val, int min, int max) {
        if (val < min) return min;
        if (val > max) return max;
        return val;
    }
}
