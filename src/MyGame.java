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
        Platform main = new Platform(20, 100, 700, 70);
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

    private void resolvePlayerCollision(Player player1, Player player2) {
        // Calculate the overlap on both axes
        double overlapX = calculateOverlapX(player1, player2);
        double overlapY = calculateOverlapY(player1, player2);
        
        // Push players apart based on smallest overlap (more natural)
        if (Math.abs(overlapX) < Math.abs(overlapY)) {
            // Resolve horizontally
            if (player1.getX() < player2.getX()) {
                player1.setX(player1.getX() - overlapX / 2);
                player2.setX(player2.getX() + overlapX / 2);
            } else {
                player1.setX(player1.getX() + overlapX / 2);
                player2.setX(player2.getX() - overlapX / 2);
            }
        } else {
            // Resolve vertically
            if (player1.getY() < player2.getY()) {
                player1.setY(player1.getY() - overlapY / 2);
                player2.setY(player2.getY() + overlapY / 2);
            } else {
                player1.setY(player1.getY() + overlapY / 2);
                player2.setY(player2.getY() - overlapY / 2);
            }
        }
    }
    
    private double calculateOverlapX(Player p1, Player p2) {
        double p1Right = p1.getX() + p1.getWidth();
        double p2Right = p2.getX() + p2.getWidth();
        
        if (p1.getX() < p2.getX()) {
            return p1Right - p2.getX();
        } else {
            return p2Right - p1.getX();
        }
    }
    
    private double calculateOverlapY(Player p1, Player p2) {
        double p1Top = p1.getY() + p1.getHeight();
        double p2Top = p2.getY() + p2.getHeight();
        
        if (p1.getY() < p2.getY()) {
            return p1Top - p2.getY();
        } else {
            return p2Top - p1.getY();
        }
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
        
        //supposed to stop you from going through the platform

        // Platform collisions
for (int i = activeObjects.size() - 1; i >= 0; i--) {
    GameObject current = activeObjects.get(i);
    
    // Check if current object is a Platform
    if (current instanceof Platform) {
        Platform platform = (Platform) current;
        
        // Check each player for collision
        for (int j = 0; j < activeObjects.size(); j++) {
            GameObject potentialPlayer = activeObjects.get(j);
            
            // Check if it's a Player
            if (potentialPlayer instanceof Player) {
                Player player = (Player) potentialPlayer;
                
                // Check if player overlaps with platform
                if (player.getHitbox().overlaps(platform.getHitbox())) {
                    // Calculate proper platform top position
                    double platformTop = platform.getY() + platform.getHeight();
                    double playerBottom = player.getY();
                    
                    // If player is falling from above
                    if (playerBottom <= platformTop + 5) { 
                        // Place player on top of platform
                        player.setY(platformTop);
                        
                    }
                }
            }
        }
    }
}

//player collisions
for (int i = 0; i < activeObjects.size(); i++) {
    GameObject obj1 = activeObjects.get(i);
    
    // Check if obj1 is a Player
    if (obj1 instanceof Player) {
        Player player1 = (Player) obj1;
        
        // Check against all other objects
        for (int j = i + 1; j < activeObjects.size(); j++) {
            GameObject obj2 = activeObjects.get(j);
            
            // Check if obj2 is also a Player
            if (obj2 instanceof Player) {
                Player player2 = (Player) obj2;
                
                // Check if players overlap
                if (player1.getHitbox().overlaps(player2.getHitbox())) {
                    resolvePlayerCollision(player1, player2);
                }
            }
        }
    }
}

//deathzone collisions should kill (delete player object)



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