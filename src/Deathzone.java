import com.badlogic.gdx.math.Rectangle;

public class Deathzone extends GameObject {
    
    private Rectangle hitbox;

    public Deathzone(double x, double y, int width, int length) {
        super(x, y, width, length, "assets/Kho-removebg-preview.png");
        this.hitbox = new Rectangle((int) x, (int) y, width, length);
    }
}
