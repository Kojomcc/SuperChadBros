import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

public class Player extends GameObject {

    private double percentage;
    private double speed;
    private Rectangle hitbox;
    private int leftKey;
    private int rightKey;
    private int upKey;
    private int downKey;

    public Player(double x, double y, int leftKey, int rightKey, int upKey, int downKey) {
        super(x, y, 50, 100, "assets/Kho-removebg-preview.png");
        percentage = 0.0;
        speed = 200;
        this.hitbox = new Rectangle((int) x, (int) y, 50, 100);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    public double getPercentage() {
        return percentage;
    }

    public int getLeft(){
        return leftKey;
    }

    public int getRight(){
        return rightKey;
    }

    public int getUp(){
        return upKey;
    }

    public int getDown(){
        return downKey;
    }

    public void setPercentage(double newPercentage) {
        percentage = newPercentage;
    }

    public void move(double deltaTime) {
        if (Gdx.input.isKeyPressed(leftKey)) {
            setX(getX() - (speed * deltaTime));
        }
        if (Gdx.input.isKeyPressed(rightKey)) {
            setX(getX() + (speed * deltaTime));
        }
        if (Gdx.input.isKeyPressed(upKey)) {
            setY(getY() + (speed * deltaTime));
        }
        if (Gdx.input.isKeyPressed(downKey)) {
            setY(getY() - (speed * deltaTime));
        }
    }
}