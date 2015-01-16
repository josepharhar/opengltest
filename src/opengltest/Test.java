package opengltest;

import org.lwjgl.opengl.*;
//This import allows calls without the GL11.
import static org.lwjgl.opengl.GL11.*;

public class Test {
    
    private int width = 800;
    private int height = 800;
    
    public static void main(String[] args) {
        try {
            new Test().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() throws Exception {
        init();
        loop();
    }
    
    private void init() throws Exception {
        //LWJGL 2 setup
        Display.setDisplayMode(new DisplayMode(width, height));
        Display.create();
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-2, 2, -2, 2, -2, 2);
        glMatrixMode(GL_MODELVIEW);
    }
    
    private void loop() {
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            draw();
            Display.update();
        }
    }
    
    private void draw() {
        drawSquare();
    }
    
    private void drawSquare() {
        glRotatef(0.01f, 2, 1, 0);
        glColor3f(0.5f, 0.5f, 1.0f);
        glBegin(GL_QUADS);
            glVertex2f(-1, -1);
            glVertex2f(1, -1);
            glVertex2f(1, 1);
            glVertex2f(-1, 1);
        glEnd();
    }
    
}