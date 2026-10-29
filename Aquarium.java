import edu.cymbal.graphics.CanvasAPI;
import edu.cymbal.graphics.Graphics;
import edu.cymbal.graphics.PainterAPI;
import java.awt.Color;

public class Aquarium {

    public static void main(String[] args) {
        CanvasAPI canvas = Graphics.createCanvas(800, 600, "My First Drawing");

        PainterAPI p = canvas.getPainter();
        p.setSpeed(10); // Lower number means faster, zero means as fast as possible.

        // Checkpoint demo: just proves the project is set up to draw something.
        // Delete this when you start the full project.
        p.startFill(Color.BLUE);

        for (int i = 0; i < 4; i++) {
            p.forward(-100);
            p.turnRight(90);
        }

        p.finishFill();

        // Later, this will call: drawAlienWorld
    }

    /**
     * Draws the entire alien world scene, back to front.
     *
     * @param p the painter used to draw
     */
    public static void drawAlienWorld(PainterAPI p) {
        // Calls: drawSky, drawStars, drawMoon, drawGround, drawAlienPlant
    }

    /**
     * Fills the background with the alien sky color.
     *
     * @param p painter
     */
    public static void drawSky(PainterAPI p) {
    }

    /**
     * Draws stars scattered across the sky.
     *
     * @param p the painter used to draw
     * @param count how many stars to draw
     */
    public static void drawStars(PainterAPI p, int count) {
        // Calls: drawStar
    }

    /**
     * Draws a single star.
     *
     * @param p the painter used to draw
     * @param x horizontal pos of the star's center
     * @param y vertical pos of the star's center
     * @param size  size of the star
     */
    public static void drawStar(PainterAPI p, double x, double y, double size) {
    }

    /**
     * Draws a moon or planet in the sky.
     *
     * @param p the painter used to draw
     * @param x horizontal position of the moon's center
     * @param y vertical position of the moon's center
     * @param radius size of the moon
     * @param color the moon's color
     */
    public static void drawMoon(PainterAPI p, double x, double y, double radius, Color color) {
    }

    /**
     * Draws the alien terrain along the bottom of the canvas.
     *
     * @param p the painter used to draw
     */
    public static void drawGround(PainterAPI p) {
    }

    /**
     * Draws a strange alien plant standing on the ground.
     *
     * @param p the painter used to draw
     * @param x horizontal position of the plant's base
     * @param y vertical position of the plant's base
     * @param height how tall the plant is
     */
    public static void drawAlienPlant(PainterAPI p, double x, double y, double height) {
        // Calls: drawPlantBulb
    }

    /**
     * Draws the glowing bulb at the top of an alien plant.
     *
     * @param p the painter used to draw
     * @param x horizontal position of the bulb's center
     * @param y vertical position of the bulb's center
     * @param radius size of the bulb
     */
    public static void drawPlantBulb(PainterAPI p, double x, double y, double radius) {
    }
}