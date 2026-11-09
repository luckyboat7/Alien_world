import edu.cymbal.graphics.CanvasAPI;
import edu.cymbal.graphics.Graphics;
import edu.cymbal.graphics.PainterAPI;
import java.awt.Color;

public class Aquarium {

    // Dino colors: change these to restyle the whole dino
    private static final Color DINO_COLOR = new Color(0, 0, 0);  
    private static final Color DINO_LIGHT = new Color(128, 128, 128);  
    private static final Color DINO_SPIKE = new Color(255, 220, 80); // yellow back spikes

    public static void main(String[] args) {
        CanvasAPI canvas = Graphics.createCanvas(800, 600, "Alien World");

        PainterAPI p = canvas.getPainter();
        p.setSpeed(0); // 0 = as fast as possible

        drawAlienWorld(p);
    }

    /**
     * Draws the entire alien world scene, back to front.
     *
     * @param p the painter used to draw
     */
    public static void drawAlienWorld(PainterAPI p) {
        // Calls: drawSky, drawStars, drawMoon, drawGround, drawAlienPlant, drawAlienDino
        drawSky(p);
        drawStars(p, 25);
        drawMoon(p, -250, 200, 50, new Color(200, 255, 200)); // big pale-green moon
        drawMoon(p, 250, 150, 35, new Color(255, 180, 120));  // small orange moon
        drawGround(p);
        drawAlienPlant(p, -200, -150, 120);
        drawAlienPlant(p, 100, -190, 80);
        drawAlienPlant(p, 280, -160, 150);
        drawAlienPlant(p, -300, -160, 150);
        drawAlienPlant(p, 150, -160, 100);
        drawAlienDino(p); // drawn last so the dino stands in front of the plants
    }

    /**
     * Fills the background with the alien sky color.
     *
     * @param p the painter used to draw
     */
    public static void drawSky(PainterAPI p) {
        // Calls: drawRectangle
        drawRectangle(p, -400, 300, 800, 600, new Color(60, 20, 90)); // deep purple
    }

    /**
     * Draws stars scattered across the sky.
     *
     * @param p the painter used to draw
     * @param count how many stars to draw
     */
    public static void drawStars(PainterAPI p, int count) {
        // Calls: drawStar
        for (int i = 0; i < count; i++) {
            double x = -400 + Math.random() * 800; // anywhere across the width
            double y = Math.random() * 300;        // upper half only
            double size = 2 + Math.random() * 4;
            drawStar(p, x, y, size);
        }
    }

    /**
     * Draws a single star.
     *
     * @param p the painter used to draw
     * @param x horizontal position of the star's center
     * @param y vertical position of the star's center
     * @param size overall size of the star
     */
    public static void drawStar(PainterAPI p, double x, double y, double size) {
        // Calls: jumpTo
        jumpTo(p, x, y);
        p.startFill(Color.YELLOW);
        p.circle(size);
        p.finishFill();
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
        // Calls: jumpTo
        jumpTo(p, x, y);
        p.startFill(color);
        p.circle(radius);
        p.finishFill();
    }

    /**
     * Draws the alien terrain along the bottom of the canvas.
     *
     * @param p the painter used to draw
     */
    public static void drawGround(PainterAPI p) {
        // Calls: drawRectangle
        drawRectangle(p, -400, -150, 800, 150, new Color(20, 110, 90)); // teal ground
    }


    public static void drawTriangle(PainterAPI p, double x1, double y1, double x2, double y2,
                             double x3, double y3, Color color) {
            
                    jumpTo(p, x1, y1);   
                    p.startFill(color);
                    p.moveTo(x2, y2);   
                    p.moveTo(x3, y3);   
                    p.moveTo(x1, y1);    
                    p.finishFill();
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
        // Calls: drawRectangle, drawPlantBulb
        // Stem: a thin rectangle standing on the base point
        drawRectangle(p, x, y + height, 10, height, new Color(90, 200, 60));

        // Bulb sits on top of the stem
        drawPlantBulb(p, x + 5, y + height, height / 6);
        drawPlantBulb(p, x - 10, y + height * 0.6, height / 10);
        drawPlantBulb(p, x + 15, y + height * 0.3, height / 20);
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
        // Calls: jumpTo
        jumpTo(p, x, y);
        p.startFill(Color.PINK);
        p.circle(radius);
        p.finishFill();
    }

    /**
     * Draws a filled rectangle given its top-left corner.
     *
     * @param p the painter used to draw
     * @param x x of the top-left corner
     * @param y y of the top-left corner
     * @param width rectangle width
     * @param height rectangle height
     * @param color fill color
     */
    public static void drawRectangle(PainterAPI p, double x, double y, double width, double height, Color color) {
        // Calls: jumpTo
        jumpTo(p, x, y);
        p.startFill(color);
        for (int i = 0; i < 2; i++) {
            p.forward(width);
            p.turnRight(90);
            p.forward(height);
            p.turnRight(90);
        }
        p.finishFill();
    }

    /**
     * Draws the blocky alien T-rex, facing right, using only rectangles.
     * Every rectangle uses real canvas coordinates: (x, y) is its top-left
     * corner, so its bottom edge is at y minus its height. The ground is at
     * y = -150. Later rectangles cover earlier ones.
     *
     * @param p the painter used to draw
     */
    public static void drawAlienDino(PainterAPI p) {
        // Calls: drawTail, drawLegs, drawBackSpikes, drawBody, drawArm, drawHead
        drawTail(p);
        drawLegs(p);
        drawBackSpikes(p);
        drawBody(p);
        drawArm(p);
        drawHead(p);
        
    }

    /**
     * Draws the tail as two rectangles that narrow toward the tip.
     *
     * @param p the painter used to draw
     */
    public static void drawTail(PainterAPI p) {
        // Calls: drawRectangle
        drawRectangle(p, -380, -10, 30, 15, DINO_COLOR);
        drawRectangle(p, -380, -15, 30, 10, DINO_LIGHT);
        drawRectangle(p, -350, -5, 50, 20, DINO_COLOR);
        drawRectangle(p, -350, -15, 50, 15, DINO_LIGHT);
        drawRectangle(p, -300, -0, 70, 25, DINO_COLOR);
        drawRectangle(p, -300, -15, 70, 15, DINO_LIGHT);
        drawRectangle(p, -230, 0, 70, 35, DINO_COLOR); 
        drawRectangle(p, -230, -20, 70, 15, DINO_LIGHT);    // thin tip
        drawRectangle(p, -170, 0, 100, 50, DINO_COLOR);
        drawRectangle(p, -170, -25, 120, 25, DINO_LIGHT);
        drawRectangle(p, -10, -25, 30, 25, DINO_LIGHT);  // thick part by the body
        
        
    }

    /**
     * Draws both legs, each a tall rectangle with a wider foot.
     *
     * @param p the painter used to draw
     */
    public static void drawLegs(PainterAPI p) {
        // Calls: drawRectangle
        // Back leg
        drawRectangle(p, -60, -50, 35, 40, DINO_LIGHT);   // leg
        drawRectangle(p, -50, -80, 35, 40, DINO_LIGHT);
        drawRectangle(p, -70, -110, 35, 30, DINO_LIGHT);
        drawRectangle(p, -60, -130, 60, 20, DINO_LIGHT);  // foot, sticks forward
        // Front leg
        drawRectangle(p, 10, -60, 35, 40, DINO_LIGHT);
        drawRectangle(p, 30, -100, 35, 40, DINO_LIGHT);     // leg
        drawRectangle(p, 50, -130, 60, 20, DINO_LIGHT);   // foot, sticks forward
    }

    /**
     * Draws a row of square spikes sitting on top of the body.
     *
     * @param p the painter used to draw
     */
    public static void drawBackSpikes(PainterAPI p) {
        // Calls: drawRectangle
        drawRectangle(p, -70, 25, 20, 25, DINO_SPIKE);
        drawRectangle(p, -30, 25, 20, 25, DINO_SPIKE);
        drawRectangle(p, 10, 25, 20, 25, DINO_SPIKE);
    }

    /**
     * Draws the main body as one big rectangle.
     *
     * @param p the painter used to draw
     */
    public static void drawBody(PainterAPI p) {
        // Calls: drawRectangle
        drawRectangle(p, -90, 0, 160, 60, DINO_COLOR);
        drawRectangle(p, -90, 0, 160, 60, DINO_LIGHT);
        drawRectangle(p, -110, 0, 130, 50, DINO_COLOR);
        

    }

    /**
     * Draws the tiny T-rex arm on the front of the body.
     *
     * @param p the painter used to draw
     */
    public static void drawArm(PainterAPI p) {
        // Calls: drawRectangle
        drawRectangle(p, 70, -30, 30, 12, DINO_LIGHT);
        drawRectangle(p, 90, -40, 30, 12, DINO_LIGHT);
    }

    /**
     * Draws the neck, head, lower jaw, teeth, and the two alien eyes.
     *
     * @param p the painter used to draw
     */
    public static void drawHead(PainterAPI p) {
        // Calls: drawRectangle
        drawRectangle(p, 30, 70, 40, 80, DINO_COLOR);      // neck
        drawRectangle(p, 30, 120, 110, 55, DINO_COLOR);    // head
        drawRectangle(p, 60, 75, 80, 12, DINO_LIGHT);       // lower jaw
        drawRectangle(p, 100, 76, 8, 10, Color.WHITE);     // tooth
        drawRectangle(p, 115, 76, 8, 10, Color.WHITE);     // tooth
        drawRectangle(p, 130, 76, 8, 10, Color.WHITE);     // tooth
        drawRectangle(p, 105, 110, 12, 12, Color.CYAN);  // glowing eye
        drawRectangle(p, 80, 100, 30, 10, Color.CYAN);     // second alien eye
    }

    /**
     * Moves the painter to a position without drawing a line,
     * then leaves the pen down, ready to draw.
     *
     * @param p the painter used to draw
     * @param x the x position to move to
     * @param y the y position to move to
     */
    public static void jumpTo(PainterAPI p, double x, double y) {
        p.liftPen();
        p.moveTo(x, y);
        p.lowerPen();
    }
}