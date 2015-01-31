package surface;

import java.nio.FloatBuffer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;

//This import allows calls without the GL11.
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.BufferUtils.*;

public class Runner {
    
    private int width = 800;
    private int height = 800;
    
    private float x = 0;
    private float y = 0;
    private float z = 0;
    
    private float rotation = 0;
    
    public static void main(String[] args) {
        try {
            new Runner().run();
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
        glOrtho(-20, 20, -20, 20, -20, 20);
        glMatrixMode(GL_MODELVIEW);
        
        BezierCurve.init();
        //Surface.init();
        
        FloatBuffer ambient  = createFloatBuffer(4).put(new float[]{0.2f, 0.2f, 0.2f, 1.0f});
        ambient.flip();
        FloatBuffer position = createFloatBuffer(4).put(new float[]{0.0f, 0.0f, 2.0f, 1.0f});
        position.flip();
        FloatBuffer mat_diffuse = createFloatBuffer(4).put(new float[]{0.6f, 0.6f, 0.6f, 1.0f});
        mat_diffuse.flip();
        FloatBuffer mat_specular = createFloatBuffer(4).put(new float[]{1.0f, 1.0f, 1.0f, 1.0f});
        mat_specular.flip();
        FloatBuffer mat_shininess = createFloatBuffer(4).put(new float[]{50.0f, 0, 0, 0});
        mat_shininess.flip();

        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        
        glLight(GL_LIGHT0, GL_AMBIENT, ambient);
        glLight(GL_LIGHT0, GL_POSITION, position);
        
        glMaterial(GL_FRONT, GL_DIFFUSE, mat_diffuse);
        glMaterial(GL_FRONT, GL_SPECULAR, mat_specular);
        glMaterial(GL_FRONT, GL_SHININESS, mat_shininess);
    }
    
    private void loop() {
        while (!Display.isCloseRequested()) {
            glLoadIdentity();
            if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                y += .001f;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                y -= .001f;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                x += .001f;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
                x -= .001f;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                z += .001f;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_S)){
                z -= .001f;
            }
            draw();
        }
    }
    
    private void draw() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glTranslatef(x, y, z);
        
        BezierCurve.draw();
        
//        glRotatef(0.01f, 2, 1, 0);
        
//        glBegin(GL_POINTS);
////            for (int i = nu1; i <= nu2; i++)
////                for (int j = nv1; j <= nv2; j++)
////                    glEvalCoord2(u1 + i*(u2-u1)/nu, v1+j*(v2-v1)/nv);
//            glEvalCoord2f(1, 2);
//            glEvalCoord2f(2, 2);
//            glEvalCoord2f(3, 2);
//        glEnd();
        
//        drawCube();
        
        Display.update();
    }
    
    private void drawCube() {
        rotation += 0.01f;
        glRotatef(rotation, 2, 1, 0);
        
        glBegin(GL_QUADS);
            glColor3f(0.0f,1.0f,0.0f); // Set The Color To Green
            glVertex3f( 1.0f, 1.0f,-1.0f); // Top Right Of The Quad (Top)
            glVertex3f(-1.0f, 1.0f,-1.0f); // Top Left Of The Quad (Top)
            glVertex3f(-1.0f, 1.0f, 1.0f); // Bottom Left Of The Quad (Top)
            glVertex3f( 1.0f, 1.0f, 1.0f); // Bottom Right Of The Quad (Top)
            
            glColor3f(1.0f,0.5f,0.0f); // Set The Color To Orange
            glVertex3f( 1.0f,-1.0f, 1.0f); // Top Right Of The Quad (Bottom)
            glVertex3f(-1.0f,-1.0f, 1.0f); // Top Left Of The Quad (Bottom)
            glVertex3f(-1.0f,-1.0f,-1.0f); // Bottom Left Of The Quad (Bottom)
            glVertex3f( 1.0f,-1.0f,-1.0f); // Bottom Right Of The Quad (Bottom)
            
            glColor3f(1.0f,0.0f,0.0f); // Set The Color To Red
            glVertex3f( 1.0f, 1.0f, 1.0f); // Top Right Of The Quad (Front)
            glVertex3f(-1.0f, 1.0f, 1.0f); // Top Left Of The Quad (Front)
            glVertex3f(-1.0f,-1.0f, 1.0f); // Bottom Left Of The Quad (Front)
            glVertex3f( 1.0f,-1.0f, 1.0f); // Bottom Right Of The Quad (Front)
            
            glColor3f(1.0f,1.0f,0.0f); // Set The Color To Yellow
            glVertex3f( 1.0f,-1.0f,-1.0f); // Bottom Left Of The Quad (Back)
            glVertex3f(-1.0f,-1.0f,-1.0f); // Bottom Right Of The Quad (Back)
            glVertex3f(-1.0f, 1.0f,-1.0f); // Top Right Of The Quad (Back)
            glVertex3f( 1.0f, 1.0f,-1.0f); // Top Left Of The Quad (Back)
            
            glColor3f(0.0f,0.0f,1.0f); // Set The Color To Blue
            glVertex3f(-1.0f, 1.0f, 1.0f); // Top Right Of The Quad (Left)
            glVertex3f(-1.0f, 1.0f,-1.0f); // Top Left Of The Quad (Left)
            glVertex3f(-1.0f,-1.0f,-1.0f); // Bottom Left Of The Quad (Left)
            glVertex3f(-1.0f,-1.0f, 1.0f); // Bottom Right Of The Quad (Left)
            
            glColor3f(1.0f,0.0f,1.0f); // Set The Color To Violet
            glVertex3f( 1.0f, 1.0f,-1.0f); // Top Right Of The Quad (Right)
            glVertex3f( 1.0f, 1.0f, 1.0f); // Top Left Of The Quad (Right)
            glVertex3f( 1.0f,-1.0f, 1.0f); // Bottom Left Of The Quad (Right)
            glVertex3f( 1.0f,-1.0f,-1.0f); // Bottom Right Of The Quad (Right)
        glEnd();
    }
    
}