import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import com.badlogic.gdx.Input;

public class MyGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private ArrayList<GameObject> activeObjects;
    //public ArrayList<Player> players = new ArrayList<Player>();

    @Override
    public void create() {
        batch = new SpriteBatch();
        activeObjects = new ArrayList<GameObject>();

        Player player1 = new Player(200, 300, Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S);
        activeObjects.add(player1);
        //players.add(player1);
        Player player2 = new Player(600, 300, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN);
        activeObjects.add(player2);
        Platform main = new Platform(50, 50, 200, 200);
        activeObjects.add(main);
        //players.add(player2);

        Deathzone left = new Deathzone(0, 0, 1, 1000);
        activeObjects.add(left);
        Deathzone right = new Deathzone(1000, 0, 1, 1000);
        activeObjects.add(right);
        Deathzone top = new Deathzone(0, 0, 1000, 1);
        activeObjects.add(top);
        Deathzone bottom = new Deathzone(0, 1000, 1000, 1);
        activeObjects.add(bottom);
        
    }

    //render() is the game loop, called approx 60 times per second
    @Override
    public void render() {
        // Boilerplate: Clear the screen to black each frame
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // --- AP REVIEW: CASTING ---
        // Gdx.graphics.getDeltaTime() returns a float. 
        // We cast it to a double to stay strictly within the AP CSA Java standards.
        double deltaTime = (double) Gdx.graphics.getDeltaTime();

        // --- AP REVIEW: POLYMORPHISM ---
        // TODO 5: Write a standard or enhanced for-loop to iterate through activeObjects.
        // For each object, call its move() method.
        for (GameObject a : activeObjects) {
            a.move(deltaTime);
        }
        
        //Note: Anything drawn must be between .begin() and .end()
        batch.begin();
        // TODO 6: Write a loop to iterate through activeObjects and call draw(batch).
        for (int i = 0; i < activeObjects.size(); i++) {
            activeObjects.get(i).draw(batch);
        }

        batch.end();

        // --- AP REVIEW: ARRAYLIST TRAVERSAL & REMOVAL ---
        // TODO 7: Write collision logic. 
        // You must iterate through the list to check if the player overlaps with enemies.
        // See the cheat sheet for the overlap method!
        // NOTE: If you are removing items from an ArrayList, how must you structure 
        // your for-loop to avoid skipping elements?

    }
    
    @Override
    public void dispose() {
        batch.dispose();
    }
}