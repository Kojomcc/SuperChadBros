import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

public class Platform extends GameObject{

    private Rectangle hitbox;


    public Platform(double x, double y, int width, int height) {
        super(x, y, width, height, "assets/Kho-removebg-preview.png");
        this.hitbox = new Rectangle((int) x, (int) y, 200, 200);
    }



}
