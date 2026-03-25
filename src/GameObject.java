import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameObject {

    private double x;
    private double y;
    private int width;
    private int height;
    private Texture image;
    private Rectangle hitbox;

    public GameObject(double x, double y, int width, int height, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = new Texture(imagePath);
        this.hitbox = new Rectangle((int) x, (int) y, width, height);
    }

    //Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public String getImagePath() {
        return image.toString();
    }

    // Setters
    public void setX(double newX) {
        x = newX;
        hitbox.setPosition((int)x, (int)y);
    }

    public void setY(double newY) {
        y = newY;
        hitbox.setPosition((int)x, (int)y);
    }


    public void draw(SpriteBatch batch) {
        batch.draw(image, (int) x, (int) y, width, height);
    }

    public void move(double deltaTime) {
        //overridden by subclasses
    }
}
