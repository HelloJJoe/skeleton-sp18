package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    /**
     * 1. Initializing the tile rendering engine.
     * 2. Generating a two dimensional TETile[][] array.
     * 3. Using the tile rendering engine to display the TETile[][] array.
     */

    private static final int WIDTH = 60;
    private static final int HEIGHT = 40;
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);


    public class Position {
        public int x;
        public int y;

        public Position(){
            x = 0;
            y = 0;
        }
        public Position(int xOfLeftCorner, int yOfLeftCorner) {
            x = xOfLeftCorner;
            y = yOfLeftCorner;
        }
    }

    /**
     * Create a hexagon on the canvas. The default tileset is TREE
     * @param number The amount of tile in one side.
     * @param world The 2D array which represents the canvas.
     * @param p The coordinate of left bottom of the hexagon. It's a class with only Width and Height field, without any method.
     */

    public static void addHexagon(int number, TETile[][] world, Position p, int randomNum) {
        /**
         *  center = 30,15
         *  invariant :
         *  1. n - 1 increasing column / from 2 to 2i
         *  2. n rectangular
         *  3. n - 1 decreasing column / from 2i to 2
         *
         */

        // Draw the Left Triangle
        drawTriangleLeft(p, number, world, randomNum);

        // Draw the right Triangle
        drawTriangleRight(p, number, world, randomNum);

        // Draw the Rectangular
        drawRectangle(p, number, world, randomNum);
    }



    public static void addTesselationHexagon(int number, TETile[][] world, Position p) {
        // Create a new Position class
        HexWorld hexWorld = new HexWorld();
        HexWorld.Position newP = hexWorld.new Position();
        newP.x = p.x;
        newP.y = p.y;

        drawColumn(number, 3, world, newP);
        newP.x += 2 * number - 1;
        newP.y -= number;
        drawColumn(number,4, world, newP);
        newP.x += 2 * number - 1;
        newP.y -= number;
        drawColumn(number,5, world, newP);
        newP.x += 2 * number - 1;
        newP.y += number;
        drawColumn(number,4, world, newP);
        newP.x += 2 * number - 1;
        newP.y += number;
        drawColumn(number, 3, world, newP);

    }

    /**
     * Draw one column based on specific hexagon.
     * @param number The amount of tile in one side of one hexagon.
     * @param amount The number of the hexagon would like to be create in a column.
     * @param world The 2D array which represents the canvas.
     * @param p The coordinate of left bottom of the hexagon. The class has only two field: Width and Height.
     */

    public static void drawColumn(int number, int amount, TETile[][] world, Position p) {

        // Create a new Position class
        HexWorld hexWorld = new HexWorld();
        HexWorld.Position newP = hexWorld.new Position();
        newP.x = p.x;
        newP.y = p.y;


        for (int i = 0; i < amount; i += 1) {
            int tileNum = RANDOM.nextInt(5);
            addHexagon(number, world, newP, tileNum);
            newP.y += 2 * number;
        }
    }

    // Draw the Left Triangle
    public static void drawTriangleLeft(Position p, int number, TETile[][] world, int tileNum) {
        int counter = number - 1;
        int yIncrease = 0;
        for (int x = p.x - 1; x > p.x - number; x -= 1) {
            for (int y = p.y + 1; y < p.y + 1 + 2 * counter; y += 1) {
                world[x][y + yIncrease] = randomTile(tileNum);
            }
            counter -= 1;
            yIncrease += 1;
        }
    }

    // Draw the Right Triangle
    public static void drawTriangleRight(Position p, int number, TETile[][] world, int tileNum) {
        int counter = number - 1;
        int yIncrease = 0;
        for (int x = p.x + number; x < p.x + 2 * number - 1; x += 1) {
            for (int y = p.y + 1; y < p.y + 1 + 2 * counter; y += 1) {
                world[x][y + yIncrease] = randomTile(tileNum);
            }
            counter -= 1;
            yIncrease += 1;
        }
    }

    // Draw the Rectangle
    public static void drawRectangle(Position p, int number, TETile[][] world, int tileNum) {
        for (int x = p.x; x < p.x + number; x += 1) {
            for (int y = p.y; y < p.y + number * 2; y += 1) {
                world[x][y] = randomTile(tileNum);
            }
        }
    }

    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile randomTile(int tileNum) {
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.TREE;
            case 3: return Tileset.WATER;
            case 4: return Tileset.PLAYER;
            default: return Tileset.GRASS;
        }
    }


    public static void main(String[] args) {

        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);


        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }


        // set the hexagonal position
        HexWorld hexWorld = new HexWorld();
        HexWorld.Position p = hexWorld.new Position(7, 10 );

        addTesselationHexagon(3, world, p);


        // draws the world to the screen
        ter.renderFrame(world);

    }
}
